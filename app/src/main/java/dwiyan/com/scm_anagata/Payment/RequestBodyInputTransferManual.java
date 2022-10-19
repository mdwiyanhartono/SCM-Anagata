package dwiyan.com.scm_anagata.Payment;

public class RequestBodyInputTransferManual {
    private String UserId;
    private String MasterTransId;
    private String Bank1;
    private String Bank2;
    private String Noref;
    private String Note;
    private String Amount;

    public RequestBodyInputTransferManual(String userId, String masterTransId, String bank1, String bank2, String noref, String note, String amount) {
        UserId = userId;
        MasterTransId = masterTransId;
        Bank1 = bank1;
        Bank2 = bank2;
        Noref = noref;
        Note = note;
        Amount = amount;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMasterTransId() {
        return MasterTransId;
    }

    public void setMasterTransId(String masterTransId) {
        MasterTransId = masterTransId;
    }

    public String getBank1() {
        return Bank1;
    }

    public void setBank1(String bank1) {
        Bank1 = bank1;
    }

    public String getBank2() {
        return Bank2;
    }

    public void setBank2(String bank2) {
        Bank2 = bank2;
    }

    public String getNoref() {
        return Noref;
    }

    public void setNoref(String noref) {
        Noref = noref;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
