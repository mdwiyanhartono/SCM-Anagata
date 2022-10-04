package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyUpdatePassword {
    private String UserId;
    private String PasswordLama;
    private String PasswordBaru;


    public RequestBodyUpdatePassword(String passwordLama, String passwordBaru, String userId) {
        PasswordLama = passwordLama;
        PasswordBaru = passwordBaru;
        UserId = userId;
    }

    public String getPasswordLama() {
        return PasswordLama;
    }

    public void setPasswordLama(String passwordLama) {
        PasswordLama = passwordLama;
    }

    public String getPasswordBaru() {
        return PasswordBaru;
    }

    public void setPasswordBaru(String passwordBaru) {
        PasswordBaru = passwordBaru;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
