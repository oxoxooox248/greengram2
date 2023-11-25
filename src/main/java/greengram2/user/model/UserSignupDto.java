package greengram2.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {
    private String uid;
    private String upw;
    private String nm;
    private String pic;
}
