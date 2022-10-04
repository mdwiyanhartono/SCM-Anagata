package dwiyan.com.scm_anagata.Order.Model;

import java.util.List;

import dwiyan.com.scm_anagata.History.Model.ModelHistory;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelOrder {

    String kode;
    List<ModelOrder> result;
    String message;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<ModelOrder> getResult() {
        return result;
    }

    public void setResult(List<ModelOrder> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
