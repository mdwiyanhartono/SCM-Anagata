package dwiyan.com.scm_anagata.Base;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.DataBase.DBAdapter2;
import dwiyan.com.scm_anagata.MainActivity;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.SplashScreen;

public class BaseFragment extends Fragment {


    public TextView message, title;
    public Button ok, close;
    public LottieAnimationView anim;

    public String Kode, Message;
    public ProgressDialog pdLoading;

    public DBAdapter2 db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public ApiRequestData api;
    public void API(){
        api = Retroserver.getClient(getActivity().getApplicationContext()).create(ApiRequestData.class);
    }
    public String datenow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    public void DataBase(){
        db = new DBAdapter2(getActivity());
    }
    public void BackActivity(Context ctx, Class actv) {
        Intent i = new Intent(ctx, actv);
        startActivity(i);
        getActivity().finish();
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
//            GlobalVar.NOHP = c.getString(4);
//            GlobalVar.Level = c.getString(5);
        }
        db.close();
    }

    public void PdLoading() {
        pdLoading = new ProgressDialog(getActivity());
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        Window window = pdLoading.getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        pdLoading.show();
    }

    public void DialogNotifFailed(String Title, String Message) {
        final Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_failed);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        ok = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
        ok.setOnClickListener(new View.OnClickListener() {
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

    public void DialogNotifError(String Title, String Message) {
        final Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_failed);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        ok = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
        ok.setOnClickListener(new View.OnClickListener() {
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

    public void DialogNotifSuccess(String Title, String Message) {
        final Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
//        ok = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                d.dismiss();
//            }
//        });
        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }


    public void BackToActivity(Context ctx, Class actv) {
        Intent i = new Intent(ctx, actv);
        startActivity(i);
        getActivity().finish();
    }

    public void NextToActivityNoneFinish(Context ctx, Class actv) {
        Intent i = new Intent(ctx, actv);
        startActivity(i);
//        getActivity().finish();
    }

    public void DialogNotifSuccessAutoClose(String Title, String Message) {
        final Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
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
        }, 3000);

        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void DialogNotifSuccessNextActivity(String Title, String Message, Context ctx, Class actv) {
        final Dialog d = new Dialog(getActivity());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
//                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
//                int wait = 0;
//                try {
//                    while (wait < welcomeScreenDisplay) {
//                        sleep(100);
//                        wait += 100;
//                    }
//                } catch (Exception e) {
//                    System.out.println("EXc=" + e);
//
//                } finally {
//                    d.dismiss();
//                    BackToActivity(ctx, actv);
//                }
                BackToActivity(ctx, actv);
            }
        }, 2800);

        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    public void CekLogin(){
        getuser();
        if(Integer.valueOf(GlobalVar.Level) == 1){
            DialogNotifSuccessNextActivity("Berhasil !", Message, getActivity(), MainActivity.class);
        } else {

        }
    }
    public void logout() {
        DBAdapter2 db2 = new DBAdapter2(getActivity());
        //OPEN
        db2.openDB();
        //SELECT
        Long a = db2.Deleteuser();
        db2.close();
        GlobalVar.ID = "0";
        Intent i = new Intent(getActivity(), SplashScreen.class);
        startActivity(i);
        getActivity().finish();

        GlobalVar globalVar = new GlobalVar();
    }
}
