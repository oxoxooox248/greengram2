package greengram2.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoVo {
    private String nm;
    private String pic;
    private String createdAt;
    private int feedCnt;
    private int favCnt;
}
