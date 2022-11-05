package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

public class RequestBodyInputTransaksi {
    private String UserId;
    private String TotalPrice;
    private String PPN;
    private String PPNValue;
    private String TglDeliv;

    public RequestBodyInputTransaksi(String userId, String totalPrice, String PPN, String PPNValue, String tglDeliv) {
        UserId = userId;
        TotalPrice = totalPrice;
        this.PPN = PPN;
        this.PPNValue = PPNValue;
        TglDeliv = tglDeliv;
    }

    public String getTglDeliv() {
        return TglDeliv;
    }

    public void setTglDeliv(String tglDeliv) {
        TglDeliv = tglDeliv;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getPPN() {
        return PPN;
    }

    public void setPPN(String PPN) {
        this.PPN = PPN;
    }

    public String getPPNValue() {
        return PPNValue;
    }

    public void setPPNValue(String PPNValue) {
        this.PPNValue = PPNValue;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
