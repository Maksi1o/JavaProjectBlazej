package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookRequest;
import pd14.entity.Book;
import pd14.entity.Loan;
import pd14.entity.Member;
import pd14.exceptions.BookNotFoundException;
import pd14.exceptions.LoanLimitExceededException;
import pd14.exceptions.LoanNotFoundException;
import pd14.exceptions.MemberNotFoundException;
import pd14.mapper.LoanMapper;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.LoanService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Override
    public LoanDto borrow(CreateLoanRequest request) {
        Member member = requireMember(request.memberId());
        Book book = requireBook(request.bookId());
        requireCanBorrow(member);
        book.borrow();
        Loan loan = new Loan(book, member, LocalDate.now());
        member.addLoan(loan);
        return LoanMapper.toDto(loanRepository.save(loan));
    }

    @Override
    public LoanDto returnBook(ReturnBookRequest request) {
        Loan loan = requireLoan(request.loanId());
        loan.markReturned();
        loan.getBook().returnBack();
        return LoanMapper.toDto(loan);
    }

    @Override
    public List<LoanDto> findOverdue() {
        return loanRepository.findAll().stream()
                .filter(Loan::isOverdue)
                .map(LoanMapper::toDto)
                .toList();
    }

    private Loan requireLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    private Member requireMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    private Book requireBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    private void requireCanBorrow(Member member) {
        if (!member.canBorrow()) {
            throw new LoanLimitExceededException(member.getId());
        }
    }
}
