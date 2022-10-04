package dwiyan.com.scm_anagata.ItemDetail.ResponseModel;

import java.util.List;

import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItemDetail;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelItemDetail {

    String kode;
    List<ModelItemDetail> result;
    String message;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<ModelItemDetail> getResult() {
        return result;
    }

    public void setResult(List<ModelItemDetail> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
