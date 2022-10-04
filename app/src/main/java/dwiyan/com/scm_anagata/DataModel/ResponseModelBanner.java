package dwiyan.com.scm_anagata.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelBanner {

    String kode;
    String message;
    private List<ModelBanner> result;


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

    public List<ModelBanner> getResult() {
        return result;
    }

    public void setResult(List<ModelBanner> result) {
        this.result = result;
    }
}
