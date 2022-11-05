package dwiyan.com.scm_anagata.Payment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelBank {

    @SerializedName("bankaccountid")
    private String bankaccountid;
    @SerializedName("accountbank")
    private String accountbank;
    @SerializedName("accountnumber")
    private String accountnumber;
    @SerializedName("accountname")
    private String accountname;
    @SerializedName("description")
    private String description;

    public String getBankaccountid() {
        return bankaccountid;
    }

    public void setBankaccountid(String bankaccountid) {
        this.bankaccountid = bankaccountid;
    }

    public String getAccountbank() {
        return accountbank;
    }

    public void setAccountbank(String accountbank) {
        this.accountbank = accountbank;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
