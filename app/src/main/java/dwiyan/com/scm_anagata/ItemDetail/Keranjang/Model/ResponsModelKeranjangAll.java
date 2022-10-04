package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelKeranjangAll {

    String kode;
    String Count;
    List<ModelKeranjangAll> result;
    String Total;
    String TotalDisplay;
    String PPN;
    String message;


    public String getTotalDisplay() {
        return TotalDisplay;
    }

    public void setTotalDisplay(String totalDisplay) {
        TotalDisplay = totalDisplay;
    }

    public String getPPN() {
        return PPN;
    }

    public void setPPN(String PPN) {
        this.PPN = PPN;
    }

    public List<ModelKeranjangAll> getResult() {
        return result;
    }

    public void setResult(List<ModelKeranjangAll> result) {
        this.result = result;
    }

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
