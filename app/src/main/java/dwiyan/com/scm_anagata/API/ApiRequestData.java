package dwiyan.com.scm_anagata.API;

import dwiyan.com.scm_anagata.DataModel.RequestBodyChat;
import dwiyan.com.scm_anagata.DataModel.RequestBodyConfirmtrans;
import dwiyan.com.scm_anagata.DataModel.RequestBodyForgotPassword;
import dwiyan.com.scm_anagata.DataModel.RequestBodyIdItem;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItem;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItemPagination;
import dwiyan.com.scm_anagata.DataModel.RequestBodyLogin;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUpdatePassword;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserIdToken;
import dwiyan.com.scm_anagata.DataModel.ResponseModelAllTransaksi;
import dwiyan.com.scm_anagata.DataModel.ResponseModelBanner;
import dwiyan.com.scm_anagata.DataModel.ResponseModelCategory;
import dwiyan.com.scm_anagata.DataModel.ResponseModelChat;
import dwiyan.com.scm_anagata.DataModel.ResponseModelConfirmasiTrans;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.DataModel.ResponseModelLogin;
import dwiyan.com.scm_anagata.DataModel.ResponseModelLupaPass;
import dwiyan.com.scm_anagata.DataModel.ResponseModelProduct;
import dwiyan.com.scm_anagata.DataModel.ResponseModelUserImage;
import dwiyan.com.scm_anagata.History.Model.ResponsModelHistory;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyExternalID;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyIDTransIDUser;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotal;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotalCodeEwallet;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotalOVONumber;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.RequestBodyInputTransaksi;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelKeranjangAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelRequestAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelConfirmReq;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelCreateVA;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelGetVA;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModeleWallet;
import dwiyan.com.scm_anagata.ItemDetail.Model.ReqBodyKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItem;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelKeranjang;
import dwiyan.com.scm_anagata.Order.Model.ResponsModelOrder;
import dwiyan.com.scm_anagata.Payment.RequestBodyInputTransferManual;
import dwiyan.com.scm_anagata.Payment.ResponseModelBank;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public interface ApiRequestData {

    // done
    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<ResponseModelLogin> Login(@Body RequestBodyLogin requestBodyLogin);
    // done
    @Headers("Content-Type: application/json")
    @POST("Lupa-Password")
    Call<ResponseModelLupaPass> Lupapass(@Body RequestBodyForgotPassword requestBodyForgotPassword);
    // done
    @Headers("Content-Type: application/json")
    @POST("LogOut")
    Call<ResponseModelGlobal> LogOut(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetUserDetail")
    Call<ResponseModelUserImage> GetUserImage(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("SetTokenDevice")
    Call<ResponseModelGlobal> SetTokenDevice (@Body RequestBodyUserIdToken requestBodyUserIdToken);

    // done
    @Headers("Content-Type: application/json")
    @POST("ForgotPassword")
    Call<ResponseModelGlobal> ForgotPassword(@Body RequestBodyForgotPassword requestBodyForgotPassword);
    // done
    @Headers("Content-Type: application/json")
    @POST("UpdatePassword")
    Call<ResponseModelGlobal> UpdatePassword(@Body RequestBodyUpdatePassword requestBodyUpdatePassword);
    // done
    @GET("GetProduct")
    Call<ResponseModelProduct> getProduct();

    // done
    @GET("GetBanner")
    Call<ResponseModelBanner> GetBanner();

    // done
    @GET("GetCategory")
    Call<ResponseModelCategory> GetCategory();

    // done
    @GET("GetBank")
    Call<ResponseModelBank> GetBank();

    // done
    @GET("GetCategory_withALL")
    Call<ResponseModelCategory> GetCategoryALL();

    // done
    @Headers("Content-Type: application/json")
    @POST("GetItem")
    Call<ResponsModelItem> GetItem(@Body RequestBodyItem requestBodyItem);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetItemPagination")
    Call<ResponsModelItem> GetItemPagination(@Body RequestBodyItemPagination requestBodyItemPagination);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetKeranjang")
    Call<ResponsModelKeranjang> getkeranjang(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetItemDetail")
    Call<ResponsModelItemDetail> getItemDetail(@Body RequestBodyIdItem requestBodyIdItem);

    // done
    @Headers("Content-Type: application/json")
    @POST("InputCart")
    Call<ResponseModelGlobal> inputkeranjang(@Body ReqBodyKeranjang reqBodyKeranjang);

    // done
    @Headers("Content-Type: application/json")
    @POST("UpdateCart")
    Call<ResponseModelGlobal> updatekeranjang(@Body ReqBodyKeranjang reqBodyUpdateKeranjang);

    // done
    @Headers("Content-Type: application/json")
    @POST("RemoveCart")
    Call<ResponseModelGlobal> removekeranjang(@Body ReqBodyKeranjang reqBodyIDUserMenu);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetKeranjangAll")
    Call<ResponsModelKeranjangAll> getkeranjangall(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetHistory")
    Call<ResponsModelHistory> GetHistory(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetListOrder")
    Call<ResponsModelOrder> GetListOrder(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetListInvoice")
    Call<ResponsModelOrder> GetListInvoice(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("InputTransferManual")
    Call<ResponseModelGlobal> InputTransferManual(@Body RequestBodyInputTransferManual requestBodyInputTransferManual);

    // done
    @Headers("Content-Type: application/json")
    @POST("InputTransaksi")
    Call<ResponseModelGlobal> InputTransaksi(@Body RequestBodyInputTransaksi requestBodyInputTransaksi);

    // done
    @Headers("Content-Type: application/json")
    @POST("SendChat")
    Call<ResponseModelGlobal> SendChat(@Body RequestBodyChat requestBodyChat);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetChat")
    Call<ResponseModelChat> GetChat(@Body RequestBodyUserId requestBodyUserId);

    // done
    @Headers("Content-Type: application/json")
    @POST("GetConfirmTransaksi")
    Call<ResponseModelConfirmasiTrans> GetConfirmTransaksi(@Body RequestBodyConfirmtrans requestBodyConfirmtrans);

    // done
    @Headers("Content-Type: application/json")
    @POST("getAllTransaction")
    Call<ResponseModelAllTransaksi> getAllTransaction(@Body RequestBodyConfirmtrans requestBodyConfirmtrans);

    // done
    @Headers("Content-Type: application/json")
    @POST("ConfirmTrans")
    Call<ResponseModelGlobal> ConfirmTrans(@Body RequestBodyConfirmtrans requestBodyConfirmtrans);

    // done
    @Headers("Content-Type: application/json")
    @POST("ApproveTerimaBarang")
    Call<ResponseModelGlobal> ApproveTerimaBarang(@Body RequestBodyConfirmtrans requestBodyConfirmtrans);

    // done
    @Headers("Content-Type: application/json")
    @POST("CancelTrans")
    Call<ResponseModelGlobal> CancelTrans(@Body RequestBodyConfirmtrans requestBodyConfirmtrans);

    @Headers("Content-Type: application/json")
    @POST("ConfirmReq")
    Call<ResponseModelConfirmReq> ConfirmReq(@Body ReqBodyIDTransIDUser reqBodyIDTransIDUser);

    @Headers("Content-Type: application/json")
    @POST("CreateVA")
    Call<ResponseModelCreateVA> CreateVA(@Body ReqBodyMasterTransIdGrandTotal reqBodyMasterTransIdGrandTotal);

    @Headers("Content-Type: application/json")
    @POST("eWallet")
    Call<ResponseModeleWallet> eWallet(@Body ReqBodyMasterTransIdGrandTotalCodeEwallet reqBodyMasterTransIdGrandTotalCodeEwallet);

    @Headers("Content-Type: application/json")
    @POST("eWalletOVO")
    Call<ResponseModelCreateVA> OVO(@Body ReqBodyMasterTransIdGrandTotalOVONumber reqBodyMasterTransIdGrandTotalOVONumber);


    @Headers("Content-Type: application/json")
    @POST("CancelReq")
    Call<ResponseModelConfirmReq> CancelReq(@Body ReqBodyIDTransIDUser reqBodyIDTransIDUser);

    @Headers("Content-Type: application/json")
    @POST("getRequestALL")
    Call<ResponsModelRequestAll> getRequestAll(@Body ReqBodyIDTransIDUser reqBodyIDTransIDUser);

    @Headers("Content-Type: application/json")
    @POST("CekPaymentPaid")
    Call<ResponseModelGlobal> CekPaymentOVO(@Body ReqBodyExternalID reqBodyExternalID);

    @Headers("Content-Type: application/json")
    @POST("GetVA")
    Call<ResponseModelGetVA> GetVA(@Body ReqBodyExternalID reqBodyExternalID);




}
