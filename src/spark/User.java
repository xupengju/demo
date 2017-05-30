package spark;

/**
 * Created by Milo on 2017/5/1.
 */
public class User implements java.io.Serializable {
    public String userName;
    public String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public User() {
    }
}
