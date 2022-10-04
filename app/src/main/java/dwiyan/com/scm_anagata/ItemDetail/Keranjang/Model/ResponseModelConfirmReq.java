package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelConfirmReq {

    String kode;
    String message;
    String typeTrans;


    public String getTypeTrans() {
        return typeTrans;
    }

    public void setTypeTrans(String typeTrans) {
        this.typeTrans = typeTrans;
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
