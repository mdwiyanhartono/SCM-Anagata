package dwiyan.com.scm_anagata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUpdatePassword;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends BaseActivity {

    TextInputEditText PasswordLama,PasswordBaru,PasswordKonfirmasi;
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        PasswordLama = findViewById(R.id.passwordlama);
        PasswordBaru = findViewById(R.id.passwordbaru);
        PasswordKonfirmasi = findViewById(R.id.password2);
        btnsubmit = findViewById(R.id.btn_submit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword(null,PasswordBaru)) {
                    return;
                } else if (!Objects.requireNonNull(PasswordBaru.getText()).toString().trim().equals(Objects.requireNonNull(PasswordKonfirmasi.getText()).toString().trim())){
                    PasswordKonfirmasi.setError("Konfirmas kata sandi anda tidak sesuai");
                    return;
                } else {
                    SendUpdateKataSandi();
//                    Toast.makeText(ActivityUbahKataSandi.this, "Kirim update sandi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SendUpdateKataSandi() {
        getuser();
        Toast.makeText(this, GlobalVar.ID, Toast.LENGTH_SHORT).show();
        PdLoading();
        API();
        Call<ResponseModelGlobal> UpdatePassword = api.UpdatePassword(new RequestBodyUpdatePassword(PasswordLama.getText().toString().trim(),PasswordBaru.getText().toString().trim(),GlobalVar.ID));
        UpdatePassword.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                if(Integer.parseInt(Kode) == 1){
                    DialogSuccessFinish("Berhasil!",Message);
                    logout();
                } else {
                    DialogNotifFailed("Gagal !" , Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Kesalahan" , t.getMessage().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}