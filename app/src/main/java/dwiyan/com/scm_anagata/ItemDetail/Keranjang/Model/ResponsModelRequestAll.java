package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelRequestAll {

    String AlamataCompany;
    String NamaCompany;
    String UserAddress;
    String UserKota;
    String UserProvinsi;
    String VisitDate;
    String ContactPerson;
    String Note;
    String Amount;
    String OrderFee;
    List<ModelRequestItem> Item;

    public String getAlamataCompany() {
        return AlamataCompany;
    }

    public void setAlamataCompany(String alamataCompany) {
        AlamataCompany = alamataCompany;
    }

    public String getNamaCompany() {
        return NamaCompany;
    }

    public void setNamaCompany(String namaCompany) {
        NamaCompany = namaCompany;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserKota() {
        return UserKota;
    }

    public void setUserKota(String userKota) {
        UserKota = userKota;
    }

    public String getUserProvinsi() {
        return UserProvinsi;
    }

    public void setUserProvinsi(String userProvinsi) {
        UserProvinsi = userProvinsi;
    }

    public String getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(String visitDate) {
        VisitDate = visitDate;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
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

    public String getOrderFee() {
        return OrderFee;
    }

    public void setOrderFee(String orderFee) {
        OrderFee = orderFee;
    }

    public List<ModelRequestItem> getItem() {
        return Item;
    }

    public void setItem(List<ModelRequestItem> item) {
        Item = item;
    }
}
