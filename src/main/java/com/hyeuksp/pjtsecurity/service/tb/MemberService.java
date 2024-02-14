package com.hyeuksp.pjtsecurity.service.tb;

import com.hyeuksp.pjtsecurity.domain.tb.MemberMapper;
import com.hyeuksp.pjtsecurity.domain.tb.MemberRequest;
import com.hyeuksp.pjtsecurity.domain.tb.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     * @return PK
     */
    @Transactional
    public Long saveMember(final MemberRequest params) {
        params.encodingPassword(passwordEncoder);
        memberMapper.save(params);
        return params.getId();
    }

    /**
     * 회원 상세정보 조회
     * @param loginId - UK
     * @return 회원 상세정보
     */
    public MemberResponse findMemberByLoginId(final String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     * @return PK
     */
    @Transactional
    public Long updateMember(final MemberRequest params) {
        params.encodingPassword(passwordEncoder);
        memberMapper.update(params);
        return params.getId();
    }

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param id - PK
     * @return PK
     */
    @Transactional
    public Long deleteMemberById(final Long id) {
        memberMapper.deleteById(id);
        return id;
    }
    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param loginId - UK
     * @return 회원 수
     */
    public int countMemberByLoginId(final String loginId) {
        return memberMapper.countByLoginId(loginId);
    }
    /**
     * 로그인
     * @param loginId - 로그인 ID
     * @param password - 비밀번호
     * @return 회원 상세정보
     */

    public MemberResponse login(final String loginId, final String password) {

        // 1. 회원 정보 및 비밀번호 조회
        MemberResponse member = findMemberByLoginId(loginId);
        String encodedPassword = (member == null) ? "" : member.getPassword();

        // 2. 회원 정보 및 비밀번호 체크
        if (member == null || passwordEncoder.matches(password, encodedPassword) == false) {
            return null;
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        member.clearPassword();
        return member;
    }
}


/**
 * passwordEncoder
 * SecurityConfig에 선언한 PasswordEncoder 빈(Bean)입니다.
 * saveMember( )
 * 회원 정보를 저장합니다. MemberRequest의 encodingPassword( )를 호출해서 비밀번호를 암호화 하는데요.
 * PasswordEncoder의 encode( )는 파라미터로 전달한 값을 60 자리의 난수로 리턴해 줍니다.
 * 회원 테이블의 password를 varchar(60) 자리로 선언한 이유도 이와 같습니다.
 *
 * findMemberByLoginId( )
 * 로그인 ID를 기준으로 회원 상세정보를 조회합니다.
 *
 * updateMember( )
 * 회원 정보를 수정합니다. saveMember( )와 실행되는 쿼리에만 차이가 있습니다.
 *
 * deleteMemberById( )
 * PK를 기준으로 회원 정보를 삭제합니다. 게시글, 댓글과 마찬가지로 논리 삭제 방식을 이용합니다.
 *
 * countMemberByLoginId( )
 * 로그인 ID를 기준으로 회원 수를 카운팅합니다. 아이디 중복을 체크하는 용도로 사용됩니다.
 *
 */