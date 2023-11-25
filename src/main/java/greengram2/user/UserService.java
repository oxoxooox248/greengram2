package greengram2.user;

import greengram2.ResVo;
import greengram2.user.model.*;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    //로그인
    public UserSigninVo userSignin(UserSigninDto dto){
        UserSigninProcVo pVo= mapper.selUserByUid(dto);
        UserSigninVo vo= new UserSigninVo();
        if(pVo==null){
            vo.setResult(2);
            return vo;
        } else if(!BCrypt.checkpw(dto.getUpw(),pVo.getUpw())){
            vo.setResult(3);
            return vo;
        }
        vo.setResult(1);
        vo.setIuser(pVo.getIuser());
        vo.setNm(pVo.getNm());
        vo.setPic(pVo.getPic());
        return vo;
    }
    //회원가입
    public ResVo userSignup(UserSignupDto dto){
        String hashedPw= BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt());
        UserSignupProcDto pDto=UserSignupProcDto.builder().
                uid(dto.getUid()).upw(hashedPw).nm(dto.getNm()).
                pic(dto.getPic()).build();
        int affectedRows= mapper.insUser(pDto);
        if(affectedRows==0) {return new ResVo(0);}
        return new ResVo(pDto.getIuser());
    }
    //유저 정보
    public UserInfoVo getUserInfo(int targetIuser){
        return mapper.selUserInfo(targetIuser);
    }
    //프로필 사진 변경
    public ResVo patchUserPic(UserPatchPicDto dto){
        return new ResVo(mapper.updPicByIuser(dto));
    }
}
