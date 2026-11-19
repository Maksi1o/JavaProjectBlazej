package pd14;

import entity.Book;
import entity.Loan;
import entity.Member;
import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.LoanLimitExceededException;
import service.BookService;
import service.LoanService;
import service.MemberService;
import service.impl.BookServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.MemberServiceImpl;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        LoanService loanService = new LoanServiceImpl(bookService, memberService);
        bookService.addBook(new Book(1L, "978-83-01-00001-1", "The Witcher", "Andrzej Sapkowski", 1993, 3));
        bookService.addBook(new Book(2L, "978-83-01-00002-8", "Quo Vadis", "Henryk Sienkiewicz", 1896, 0));
        bookService.addBook(new Book(3L, "978-83-01-00003-5", "Jabol", "Zygmunt III Waza", 1314, 5));
        memberService.register(new Member(20L, "Rosomak", "rosomak@example.com"));
        memberService.register(new Member(21L, "Anna Nowak", "anna.nowak@example.com"));
        memberService.register(new Member(22L, "Jan Kowalski", "jan.kowalski@example.com"));

        try {
            loanService.borrow(20L, 2L);
        } catch (BookNotAvailableException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(loanService.borrow(21L, 3L));
        try {
            loanService.borrow(22L, 5L);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Loan firstLoan = loanService.borrow(21L, 3L);
        System.out.println(bookService.findById(3L).getAvailableCopies());
        loanService.returnBook(firstLoan.getId());
        System.out.println(bookService.findById(3L).getAvailableCopies());
        System.out.println(memberService.getActiveLoans(21L).size());

        loanService.borrow(20L, 1L);
        loanService.borrow(20L, 1L);
        loanService.borrow(20L, 3L);
        System.out.println(memberService.getActiveLoans(20L).size());
        try {
            loanService.borrow(20L, 3L);
        } catch (LoanLimitExceededException e) {
            System.out.println(e.getMessage());
        }
    }
}
