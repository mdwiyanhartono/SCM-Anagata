package dwiyan.com.scm_anagata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.DataModel.RequestBodyForgotPassword;
import dwiyan.com.scm_anagata.DataModel.RequestBodyLogin;
import dwiyan.com.scm_anagata.DataModel.ResponseModelLogin;
import dwiyan.com.scm_anagata.DataModel.ResponseModelLupaPass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity {

    TextInputEditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.UserName);
        password = findViewById(R.id.password);
    }

    public void Login(View view) {
        if(username.getText().toString().isEmpty()){
            username.setError("Tidak Boleh Kosong");
        } else if (password.getText().toString().isEmpty()){
            password.setError("Tidak Boleh Kosong");
        } else {
            PdLoading();
            API();
            Call<ResponseModelLogin> login = api.Login(new RequestBodyLogin(password.getText().toString(),username.getText().toString()));
            login.enqueue(new Callback<ResponseModelLogin>() {
                @Override
                public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                    pdLoading.dismiss();
                    Kode = response.body().getKode();
                    Message = response.body().getMessage();
                    if(Integer.parseInt(Kode) == 1){
                        DataBase();
//                        Toast.makeText(Login.this, "Data:" + response.body().getUserID()+" nama: "+ response.body().getNama()+" Email : "+ response.body().getEmail(), Toast.LENGTH_SHORT).show();
                        db.openDB();
                        long adduser = db.adduser(response.body().getNama().toString(),response.body().getEmail().toString(),response.body().getUserID().toString());
                        db.close();
                        if(adduser > 0 ){
                            DialogSuccessNextActivity("Berhasil",Message,Login.this,MainActivity.class);
                        } else {
                            DialogSuccessNextActivity("Berhasil",Message + "\n trdapat kesalahan kecil, silahkan menghubungi admin.",Login.this,MainActivity.class);
                        }
                    } else {
                        DialogNotifFailed("Gagal" , Message);
                    }
                }

                @Override
                public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                    pdLoading.dismiss();
                    DialogNotifError("Error",t.getMessage());
                }
            });
        }
    }


    TextInputEditText email;
    public void Forget(View view) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_forgotpass);
        email = d.findViewById(R.id.email);
        oke = d.findViewById(R.id.ok);
        closed = d.findViewById(R.id.cancel);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                forgotpass(email.getText().toString());
            }
        });
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    private void forgotpass(String email) {
        PdLoading();
        API();
        Call<ResponseModelLupaPass> lupas = api.Lupapass(new RequestBodyForgotPassword(email));
        lupas.enqueue(new Callback<ResponseModelLupaPass>() {
            @Override
            public void onResponse(Call<ResponseModelLupaPass> call, Response<ResponseModelLupaPass> response) {
                String Status = response.body().getStatus();
                Message = response.body().getMessage();
                pdLoading.dismiss();
                if(Status.equals("success")){
                    DialogSuccessAutoHide("Lupa Password" , Message);
                } else {
                    DialogNotifFailed("Lupa Password" , Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelLupaPass> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("error",t.getMessage().toString());
            }
        });
    }
}