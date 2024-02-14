package com.hyeuksp.pjtsecurity.controller.tb;

import com.hyeuksp.pjtsecurity.controller.UserController;
import com.hyeuksp.pjtsecurity.domain.tb.MemberRequest;
import com.hyeuksp.pjtsecurity.domain.tb.MemberResponse;
import com.hyeuksp.pjtsecurity.service.tb.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login.do")
    public String openLogin() {
        return "member/login";
    }

    // 회원 정보 저장 (회원가입)
    @PostMapping("/members")
    @ResponseBody
    public Long saveMember(@RequestBody final MemberRequest params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
        return memberService.updateMember(params);
    }

    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteMemberById(final Long id) {
        return memberService.deleteMemberById(id);
    }

    // 회원 수 카운팅 (ID 중복 체크)
    @GetMapping("/member-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam final String loginId) {
        logger.info("loginId -->"+loginId);
        return memberService.countMemberByLoginId(loginId);
    }
    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberResponse login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        MemberResponse member = memberService.login(loginId, password);

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
        }

        return member;
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.do";
    }
}
/****
 * 메서드
 * 설명
 *
 *
 * login( )
 * 1. request 객체의 getParameter( )로 사용자가 로그인 페이지에 입력한 아이디와 비밀번호를 변수에 담아 회원 정보를 조회합니다.2. MemberService의 login( )은 회원 정보가 알맞게 입력된 경우에만 member 객체를 리턴합니다. 로그인에 성공하면 member 객체를 loginMember라는 이름으로 세션에 저장한 후 로그인 유지 시간을 30분으로 설정합니다. session.setMaxInactiveInterval( )은 세션 타임아웃을 설정하는 메서드로 초를 기준으로 값을 전달해 주어야 합니다. (1800초 == 30분)
 *
 *
 * logout( )
 * session.invalidate( )로 세션을 무효화(초기화) 한 후 사용자를 로그인 페이지로 이동시킵니다.
 * 출처: https://congsong.tistory.com/38 [Let's develop:티스토리]
 *
 */