package pd14.service.impl;

import entity.Loan;
import entity.Member;
import exceptions.MemberNotFoundException;
import service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberServiceImpl implements MemberService {
    List<Member> members = new ArrayList<>();

    @Override
    public void register(Member member) {
        Objects.requireNonNull(member, "Member cannot be null");
        members.add(member);
    }

    @Override
    public Member findById(long id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Member with Id: " + id + " was not found."));
    }

    @Override
    public List<Loan> getActiveLoans(long memberId) {
        return findById(memberId).getLoans().stream()
                .filter(Loan::isActive)
                .toList();
    }
}
