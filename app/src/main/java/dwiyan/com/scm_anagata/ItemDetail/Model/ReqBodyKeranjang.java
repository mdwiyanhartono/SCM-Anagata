package dwiyan.com.scm_anagata.ItemDetail.Model;

public class ReqBodyKeranjang {
    private String UserId;
    private String ItemId;
    private String Count;
    private String Idtrans;

    public ReqBodyKeranjang(String userId, String itemId, String count, String idtrans) {
        UserId = userId;
        ItemId = itemId;
        Count = count;
        Idtrans = idtrans;
    }

    public String getIdtrans() {
        return Idtrans;
    }

    public void setIdtrans(String idtrans) {
        Idtrans = idtrans;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
}