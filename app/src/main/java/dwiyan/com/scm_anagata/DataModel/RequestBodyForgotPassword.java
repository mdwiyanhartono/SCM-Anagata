package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyForgotPassword {

    private String email;

    public RequestBodyForgotPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
