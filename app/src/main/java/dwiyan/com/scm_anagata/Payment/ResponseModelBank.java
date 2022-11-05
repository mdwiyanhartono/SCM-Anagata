package dwiyan.com.scm_anagata.Payment;

import java.util.List;

import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelBank {

    String kode;
    String message;
    private List<ModelBank> result;

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

    public List<ModelBank> getResult() {
        return result;
    }

    public void setResult(List<ModelBank> result) {
        this.result = result;
    }
}
