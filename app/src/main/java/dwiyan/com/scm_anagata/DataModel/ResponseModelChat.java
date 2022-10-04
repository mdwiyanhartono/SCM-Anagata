package dwiyan.com.scm_anagata.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelChat {

    String kode;
    String message;
    private List<ModelChat> result;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ModelChat> getResult() {
        return result;
    }

    public void setResult(List<ModelChat> result) {
        this.result = result;
    }
}
