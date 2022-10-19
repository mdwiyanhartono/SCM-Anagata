package dwiyan.com.scm_anagata.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelAllTransaksi {

    String kode;
    String message;
    private List<ModelAllTrans> result;


    public List<ModelAllTrans> getResult() {
        return result;
    }

    public void setResult(List<ModelAllTrans> result) {
        this.result = result;
    }

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

}
