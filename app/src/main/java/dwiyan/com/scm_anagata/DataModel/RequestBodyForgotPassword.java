package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyForgotPassword {

    private String Email;

    public RequestBodyForgotPassword(String email) {

        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
