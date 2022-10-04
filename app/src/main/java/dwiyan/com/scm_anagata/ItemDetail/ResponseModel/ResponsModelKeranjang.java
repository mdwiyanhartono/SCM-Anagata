package dwiyan.com.scm_anagata.ItemDetail.ResponseModel;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelKeranjang {

    String kode;
    String Count;
    String Total;
    String message;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
