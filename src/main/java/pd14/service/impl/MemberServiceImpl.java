package pd14.service.impl;

import lombok.RequiredArgsConstructor;
import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.dto.RegisterMemberRequest;
import pd14.entity.Loan;
import pd14.entity.Member;
import pd14.exceptions.MemberAlreadyExistsException;
import pd14.exceptions.MemberNotFoundException;
import pd14.mapper.LoanMapper;
import pd14.mapper.MemberMapper;
import pd14.repository.MemberRepository;
import pd14.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberDto register(RegisterMemberRequest request) {
        memberRepository.findByEmail(request.email())
                .ifPresent(member -> {
                    throw new MemberAlreadyExistsException(request.email());
                });
        return MemberMapper.toDto(memberRepository.save(
                new Member(request.name(), request.email())));
    }

    @Override
    public MemberDto findById(Long id) {
        return MemberMapper.toDto(requireMember(id));
    }

    @Override
    public List<LoanDto> getActiveLoans(Long memberId) {
        return requireMember(memberId).getLoans()
                .stream()
                .filter(Loan::isActive)
                .map(LoanMapper::toDto)
                .toList();
    }

    private Member requireMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }
}
