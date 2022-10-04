package dwiyan.com.scm_anagata.History.Model;

import java.util.List;

import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelHistory {

    String kode;
    List<ModelHistory> result;
    String message;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<ModelHistory> getResult() {
        return result;
    }

    public void setResult(List<ModelHistory> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
