package pd14.repository;

import pd14.entity.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public Member save(Member member) {
        if (member.getId() == null) {
            member.assignId(idSequence.incrementAndGet());
        }
        members.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    public List<Member> findAll() {
        return List.copyOf(members.values());
    }

    public Optional<Member> findByEmail(String email) {
        return members.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }
}
