package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelCreateVA {

    String kode;
    String status;
    String external_id;
    String message;


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

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
