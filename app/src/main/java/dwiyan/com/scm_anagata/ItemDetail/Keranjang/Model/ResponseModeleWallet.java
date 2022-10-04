package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModeleWallet {

    String kode;
    String status;
    String external_id;
    String desktop_web_checkout_url;
    String mobile_web_checkout_url;
    String mobile_deeplink_checkout_url;
    String qr_checkout_string;
    String message;


    public String getDesktop_web_checkout_url() {
        return desktop_web_checkout_url;
    }

    public void setDesktop_web_checkout_url(String desktop_web_checkout_url) {
        this.desktop_web_checkout_url = desktop_web_checkout_url;
    }

    public String getMobile_web_checkout_url() {
        return mobile_web_checkout_url;
    }

    public void setMobile_web_checkout_url(String mobile_web_checkout_url) {
        this.mobile_web_checkout_url = mobile_web_checkout_url;
    }

    public String getMobile_deeplink_checkout_url() {
        return mobile_deeplink_checkout_url;
    }

    public void setMobile_deeplink_checkout_url(String mobile_deeplink_checkout_url) {
        this.mobile_deeplink_checkout_url = mobile_deeplink_checkout_url;
    }

    public String getQr_checkout_string() {
        return qr_checkout_string;
    }

    public void setQr_checkout_string(String qr_checkout_string) {
        this.qr_checkout_string = qr_checkout_string;
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
