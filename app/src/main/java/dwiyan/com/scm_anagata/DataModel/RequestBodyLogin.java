package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyLogin {
    private String Password;
    private String UserName;

    public RequestBodyLogin(String password, String email) {
        Password = password;
        UserName = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return UserName;
    }

    public void setEmail(String email) {
        UserName = email;
    }
}
