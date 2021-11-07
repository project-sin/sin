package sin.sin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sin.sin.dto.auth.JoinRequest;
import sin.sin.dto.auth.JoinResponse;
import sin.sin.dto.auth.LoginRequest;
import sin.sin.dto.auth.TokenDto;
import sin.sin.service.AuthService;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity signup(@RequestBody JoinRequest joinRequest) throws IOException {
        log.info(String.valueOf(joinRequest));

        JoinResponse join = authService.join(joinRequest);

        return ResponseEntity.ok(join.getName() + "님 회원가입이 완료됐습니다");
    }

    @PostMapping("/login")
    public ResponseEntity signin(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);

        return ResponseEntity.ok(new TokenDto(token));
    }

    @GetMapping("/check/id")
    public ResponseEntity isDuplicateId(@RequestParam String id) {
        authService.isDuplicateId(id);

        return ResponseEntity.ok("중복되지 않은 아이디입니다");

    }

    @GetMapping("/check/email")
    public ResponseEntity isDuplicateEmail(@RequestParam String email) {
        authService.isDuplicateEmail(email);

        return ResponseEntity.ok("중복되지 않은 이메일입니다");
    }

}
