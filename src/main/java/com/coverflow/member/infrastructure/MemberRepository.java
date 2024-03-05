package com.coverflow.member.infrastructure;

import com.coverflow.member.domain.Member;
import com.coverflow.member.domain.MemberStatus;
import com.coverflow.member.domain.SocialType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query(value = "SELECT m " +
            "FROM Member m " +
            "WHERE m.id = :id " +
            "AND m.memberStatus= :memberStatus " +
            "ORDER BY m.createdAt ASC")
    Optional<Member> findByIdAndMemberStatus(
            @Param("id") final UUID id,
            @Param("memberStatus") final MemberStatus memberStatus
    );

    Optional<Member> findByEmail(final String email);

    Optional<Member> findByNickname(final String nickname);

    Optional<Member> findByRefreshToken(final String refreshToken);

    /**
     * 소셜 타입과 소셜의 식별값으로 회원 찾는 메소드
     * 정보 제공을 동의한 순간 DB에 저장해야하지만, 아직 추가 정보(태그, 연령대, 성별 등)를 입력받지 않았으므로
     * 유저 객체는 DB에 있지만, 추가 정보가 빠진 상태이다.
     * 따라서 추가 정보를 입력받아 진행할 때 소셜 타입, 식별자로 해당 회원을 찾기 위한 메소드
     */
    Optional<Member> findBySocialTypeAndSocialIdAndMemberStatus(
            final SocialType socialType,
            final String socialId,
            final MemberStatus memberStatus
    );

    @Query(value = "SELECT m " +
            "FROM Member m")
    Optional<Page<Member>> findAllMembers(final Pageable pageable);

    @Query(value = "SELECT m " +
            "FROM Member m " +
            "WHERE m.memberStatus = :memberStatus")
    Optional<Page<Member>> findAllByMemberStatus(
            final Pageable pageable,
            @Param("memberStatus") final MemberStatus memberStatus
    );
}
