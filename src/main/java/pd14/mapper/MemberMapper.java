package pd14.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd14.dto.MemberDto;
import pd14.entity.Member;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberMapper {
    public static MemberDto toDto(Member member) {
        Objects.requireNonNull(member, "Member cannot be null");
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}
