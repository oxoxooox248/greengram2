package greengram2.user;

import greengram2.ResVo;
import greengram2.user.model.UserPatchPicDto;
import greengram2.user.model.UserInfoVo;
import greengram2.user.model.UserSigninDto;
import greengram2.user.model.UserSigninVo;
import greengram2.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/signin")
    @Operation(summary = "인증", description = "아이디/비번을 활용한 인증처리")
    @Parameters(value = {
            @Parameter(name="uid", description = "아이디")
            , @Parameter(name="upw", description = "비밀번호")
    })
    public UserSigninVo postUserSignin(@RequestBody UserSigninDto dto) {
        log.info("signin - dto: {}", dto);
        return service.userSignin(dto);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 처리")
    @Parameters(value = {
            @Parameter(name="uid", description = "아이디" )
            ,  @Parameter(name="upw", description = "비밀번호")
            ,  @Parameter(name="nm", description = "이름")
            ,  @Parameter(name="pic", description = "프로필 사진")
    })
    public ResVo postUserSignup(@RequestBody UserSignupDto dto) {
        log.info("signup - dto: {}", dto);
        return service.userSignup(dto); //ResVo객체에 insert한 레코드 pk값을 담아서 응답처리
    }

    @GetMapping
    @Operation(summary = "유저 정보", description = "프로필 화면에서 사용할 프로필 유저 정보")
    @Parameters(value = {
            @Parameter(name="targetIuser", description = "프로필 주인 유저 pk" )
    })
    public UserInfoVo getUserInfo(@RequestParam("target_iuser") int targetIuser) {
        log.info("targetIuser: {}", targetIuser);
        return service.getUserInfo(targetIuser);
    }

    //수정 성공 result:1, 수정 실패 result: 0
    @PatchMapping("/pic")
    @Operation(summary = "프로필 사진 변경", description = "로그인한 유저가 본인 프로필 사진 클릭 시 사진 변경")
    @Parameters(value = {
            @Parameter(name = "iuser", description = "로그인한 유저 pk"),
            @Parameter(name= "pic", description = "변경할 프로필 사진")
    })
    public ResVo patchUserPic(@RequestBody UserPatchPicDto dto) {
        log.info("dto : {}", dto);
        return service.patchUserPic(dto);
    }
}
