package greengram2.user;

import greengram2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignupProcDto dto); //회원가입
    UserSigninProcVo selUserByUid(UserSigninDto dto); //로그인
    UserInfoVo selUserInfo(int targetIuser); //유저 정보
    int updPicByIuser(UserPatchPicDto dto); //프로필 사진 변경
}
