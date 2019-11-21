package own.stu.mysql.masterSlave.test.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

    public User() {
        super();
    }

    public User(String userName, String passWord, UserSexEnum userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }

    public enum UserSexEnum {
        MAN, WOMAN
    }
}
