package pd14.service;

import entity.Loan;
import entity.Member;

import java.util.List;

public interface MemberService {
    void register(Member member);

    Member findById(long id);

    List<Loan> getActiveLoans(long memberId);

}
