package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sin.sin.config.auth.PrincipalDetails;
import sin.sin.config.auth.CurrentUser;
import sin.sin.dto.MemberResponse;
import sin.sin.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> findMember(@CurrentUser PrincipalDetails userDetails){
        MemberResponse memberResponse = memberService.findMember(userDetails.getMember());

        return ResponseEntity.ok()
            .body(memberResponse);
    }
}
