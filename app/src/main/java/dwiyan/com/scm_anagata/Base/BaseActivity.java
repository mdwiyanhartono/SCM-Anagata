package dwiyan.com.scm_anagata.Base;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.DataBase.DBAdapter2;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserIdToken;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.Login;
import dwiyan.com.scm_anagata.MainActivity;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.SplashScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {


    public EditText username, password, confirmpassword;
    public Button submit, login, register;
    public String Kode, Message;
    public ProgressDialog pdLoading;
    public TextView message, title;
    public CardView ok, close;
    public Button oke, closed;
    public LottieAnimationView anim;
    public DBAdapter2 db;

    public ApiRequestData api;

    private static final String TAG = "Test-SCM";
    private static final String TOPIC = "Test-SCM-Anagata";
    public DecimalFormat df;
    public String pattern = "#,###,###,###,###.00", price = "";
    public NumberFormat nf = NumberFormat.getInstance(Locale.US);
    public void SetTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d(TAG, msg);
                    }
                });
    }
    public void GetTokenFCM() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        API();
                        Call<ResponseModelGlobal> setToken = api.SetTokenDevice(new RequestBodyUserIdToken(GlobalVar.ID, token) );
                        setToken.enqueue(new Callback<ResponseModelGlobal>() {
                            @Override
                            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                                Kode = response.body().getKode();
                                Message = response.body().getMessage();
                                if(Integer.parseInt(Kode) > 0){
                                    Log.d(TAG, "SetToket Gagal " + token);
                                } else {
                                    Log.d(TAG, "SetToket Berhasil : " + Message);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                                Log.d(TAG, "SetToket Gagal " + t.getMessage());
                            }
                        });

                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, token);
//                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void API() {
        api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
    }


    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    public static final Pattern EMAIL_ADDRESS =
            Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");

    public boolean validateEmail(EditText EditTextInputEmail, TextInputEditText TextInputEditTextInputEmail) {
        String emailInput;

        if (EditTextInputEmail != null) {
            emailInput = EditTextInputEmail.getText().toString().trim();
            if (emailInput.isEmpty()) {
                EditTextInputEmail.setError("Tidak boleh kosong");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                EditTextInputEmail.setError("Silahkan input email yang benar");
                return false;
            } else {
                EditTextInputEmail.setError(null);
                return true;
            }
        } else {
            emailInput = TextInputEditTextInputEmail.getText().toString().trim();
            if (emailInput.isEmpty()) {
                TextInputEditTextInputEmail.setError("Tidak boleh kosong");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                TextInputEditTextInputEmail.setError("Silahkan input email yang benar");
                return false;
            } else {
                TextInputEditTextInputEmail.setError(null);
                return true;
            }
        }

    }

    public boolean validateInput(EditText EditTextInput, TextInputEditText TextInputEditTextInput) {
        String input;

        if (EditTextInput != null) {
            input = EditTextInput.getText().toString().trim();
            if (input.isEmpty()) {
                EditTextInput.setError("Tidak boleh kosong");
                return false;
            } else {
                EditTextInput.setError(null);
                return true;
            }
        } else {
            input = TextInputEditTextInput.getText().toString().trim();
            if (input.isEmpty()) {
                TextInputEditTextInput.setError("Tidak boleh kosong");
                return false;
            } else {
                TextInputEditTextInput.setError(null);
                return true;
            }
        }
    }

    public boolean validatePassword(EditText EditTextPassword, TextInputEditText TextInputEditTextPassword) {
        String passwordInput;

        if (EditTextPassword != null) {
            passwordInput = EditTextPassword.getText().toString().trim();
            if (passwordInput.isEmpty()) {
                EditTextPassword.setError("Tidak boleh kosong");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                EditTextPassword.setError("Kata sandi terlalu lemah, Kata sandi terdiri dari maksimal 8 (krakter angka dan simbol)");
                return false;
            } else {
                EditTextPassword.setError(null);
                return true;
            }
        } else {
            passwordInput = TextInputEditTextPassword.getText().toString().trim();
            if (passwordInput.isEmpty()) {
                TextInputEditTextPassword.setError("Tidak boleh kosong");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                TextInputEditTextPassword.setError("Kata sandi terlalu lemah, Kata sandi terdiri dari maksimal 8 (krakter angka dan simbol)");
                return false;
            } else {
                TextInputEditTextPassword.setError(null);
                return true;
            }
        }

    }

    public void BackToActivity(Context ctx, Class actv) {
        Intent i = new Intent(ctx, actv);
        startActivity(i);
        finish();
    }

    public void NextToActivityNoneFinish(Context ctx, Class actv) {
        Intent i = new Intent(ctx, actv);
        startActivity(i);
//        finish();
    }

    public void PdLoading() {
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }

    public void DialogLogOut(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_question);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        oke = d.findViewById(R.id.ok);
        closed = d.findViewById(R.id.cancel);
        anim = d.findViewById(R.id.anim);
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
                logout();
            }
        });
        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void logout() {
        getuser();
        DataBase();
        ApiRequestData api = Retroserver.getClient(getApplicationContext().getApplicationContext()).create(ApiRequestData.class);
        Call<ResponseModelGlobal> LogOut = api.LogOut(new RequestBodyUserId(GlobalVar.ID));
        LogOut.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                if (Integer.parseInt(Kode) == 1) {
                    DialogSuccessAutoHide("Berhasil !", Message);
                    new Handler().postDelayed(new Runnable() {
                        @SuppressLint("NewApi")
                        @Override
                        public void run() {
                            final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                            int wait = 0;
                            try {
                                while (wait < welcomeScreenDisplay) {
                                    sleep(100);
                                    wait += 100;
                                }
                            } catch (Exception e) {
                                System.out.println("EXc=" + e);

                            } finally {
                                Intent i = new Intent(getApplicationContext(), SplashScreen.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }, 4000);
                    //OPEN
                    db.openDB();
                    //SELECT
                    Long a = db.Deleteuser();
                    db.close();
                } else {
                    DialogNotifFailed("Gagal", "Gagal Login, Silahkan coba kembali");
                }
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });

    }

    public void DataBase() {
        db = new DBAdapter2(this);
    }

    public void getuser() {
        DataBase();
        //OPEN
        db.openDB();
        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            GlobalVar.NAMA = c.getString(0);
            GlobalVar.EMAIL = c.getString(2);
            GlobalVar.ID = c.getString(3);
        } else {
            GlobalVar.ID = "0";
            GlobalVar.PasswordTemp = "0";
        }
        db.close();
    }

    public void CekLogin() {
        getuser();

        if (Integer.parseInt(GlobalVar.ID) > 0) {
//            setAlarm();
            BackToActivity(this, MainActivity.class);
        } else {
//            cancelAlarm();
            BackToActivity(this, Login.class);
        }
    }

    public void DialogSuccessNextActivity(String Title, String Message, Context ctx, Class actv) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        title.setText(Title);
        message.setText(Message);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                int wait = 0;
                try {
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {
                    d.dismiss();
                    BackToActivity(ctx, actv);
                }
            }
        }, 1000);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogSuccessFinish(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        title.setText(Title);
        message.setText(Message);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                int wait = 0;
                try {
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {
                    d.dismiss();
                    finish();
//                    BackToActivity(ctx, actv);
                }
            }
        }, 1000);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogSuccessBackActivity(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        title.setText(Title);
        message.setText(Message);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                int wait = 0;
                try {
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {
                    d.dismiss();
                    finish();
//                    BackToActivity(ctx,actv);
                }
            }
        }, 1000);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }


    public void DialogSuccessBackActivityManual(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        oke = d.findViewById(R.id.ok);
        title.setText(Title);
        message.setText(Message);
//        new Handler().postDelayed(new Runnable() {
//            @SuppressLint("NewApi")
//            @Override
//            public void run() {
//                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
//                int wait = 0;
//                try {
//                    while (wait < welcomeScreenDisplay) {
//                        sleep(100);
//                        wait += 100;
//                    }
//                } catch (Exception e) {
//                    System.out.println("EXc=" + e);
//                } finally {
//                    d.dismiss();
//                    finish();
////                    BackToActivity(ctx,actv);
//                }
//            }
//        }, 1000);

        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                finish();
            }
        });
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogSuccessAutoHide(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        title.setText(Title);
        message.setText(Message);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                int wait = 0;
                try {
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {
                    d.dismiss();
                }
            }
        }, 1000);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogFailedNextActivity(String Title, String Message, Context ctx, Class actv) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_failed);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        oke = d.findViewById(R.id.ok);
        title.setText(Title);
        message.setText(Message);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                BackToActivity(ctx, actv);
            }
        });
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogNotifError(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_failed_error);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        oke = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogNotifFailed(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_failed);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        oke = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

//    public void setAlarm() {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, ServicesSystem.class);
//        Toast.makeText(this, "TestAlarm", Toast.LENGTH_SHORT).show();
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000, pendingIntent);
//    }
//
//    //
//    public void cancelAlarm() {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        //Toast.makeText(this, "alarm close", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, ServicesSystem.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.cancel(pendingIntent);
//    }


    TextView namaorang;
    EditText note;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3;
    CircleImageView imageprofilel;
    RatingBar ratingBar;
    CardView cancel;
    Button btnSubmit;
    ImageView imageProfile;
    String tip, star;

//    public void NotifDialogRating(Context ctx, String Namaorang, String IdComplaint, String IdHPC, String IdUser, String Photo) {
//        final Dialog d = new Dialog(ctx);
//        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        d.setContentView(R.layout.rating);
//        namaorang = d.findViewById(R.id.namaorang);
//        imageProfile = d.findViewById(R.id.imageProfile);
//        namaorang.setText(Namaorang);
//        byte[] decodedString1 = Base64.decode(Photo, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
//        imageProfile.setImageBitmap(decodedByte);
//        note = d.findViewById(R.id.TextNote);
//        ratingBar = d.findViewById(R.id.rating);
//        btnSubmit = d.findViewById(R.id.btnsubmit);
//        cancel = d.findViewById(R.id.cancel);
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                star = String.valueOf(ratingBar.getRating());
////                Toast.makeText(ctx,  star, Toast.LENGTH_SHORT).show();
//                getuser();
//                PdLoading();
//                sendRating(IdComplaint, IdUser, IdHPC, note.getText().toString(), star, GlobalVar.ID);
//                d.dismiss();
//            }
//        });
//
//        Window window = d.getWindow();
//        window.setGravity(Gravity.CENTER_VERTICAL);
//        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
//        d.show();
//    }


//    public void CekNotif(Context context) {
////        Toast.makeText(ctx, "berhasil Jalan", Toast.LENGTH_SHORT).show();
//        ApiRequestData api = Retroserver.getClient(context).create(ApiRequestData.class);
//        Call<ResponseModelRating> cekNotif = api.ceknotif(new ReqBodyUserId(GlobalVar.ID));
//        cekNotif.enqueue(new Callback<ResponseModelRating>() {
//            @Override
//            public void onResponse(Call<ResponseModelRating> call, Response<ResponseModelRating> response) {
//                String Kode = response.body().getKode();
//                String Message = response.body().getMessage();
//                if (Kode.equals("1")) {
//                    String NamaOrang = response.body().getName();
//                    String IdUser = response.body().getUserId();
//                    String IdComplaint = response.body().getIDComplaint();
//                    String IdHPC = response.body().getIDHPC();
//                    String Photo = response.body().getUserPhoto();
////                    Toast.makeText(context, IdHPC, Toast.LENGTH_SHORT).show();
//                    NotifDialogRating(context, NamaOrang, IdComplaint, IdHPC, IdUser, Photo);
//                } else {
////                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModelRating> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Rating" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private void sendRating(String IdComplaint, String IdUserPetugas, String IdHPC, String note, String star, String IdUser) {
//        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//        Call<ResponseModelGlobal> sendRating = api.rating(new ReqBodyRating(IdComplaint, IdUser, IdUserPetugas, note, star, IdHPC));
//        sendRating.enqueue(new Callback<ResponseModelGlobal>() {
//            @Override
//            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
//                pdLoading.dismiss();
//                String Kode = response.body().getKode();
//                String Message = response.body().getMessage();
//                if (Kode.equals("1")) {
//                    DialogSuccessAutoHide("Berhsail !", Message);
//                } else {
//                    DialogNotifFailed("Failed !", Message);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
//                DialogNotifError("Error !", t.getMessage().toString());
//            }
//        });
//    }


}
