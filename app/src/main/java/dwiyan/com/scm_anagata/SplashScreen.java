package dwiyan.com.scm_anagata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import java.io.File;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.BaseFragment;
import dwiyan.com.scm_anagata.DataBase.DBAdapter2;
import dwiyan.com.scm_anagata.permision.PermissionHelper;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends BaseActivity {

    PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        permissionHelper = new PermissionHelper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkAndRequestPermissions();
//                Intent i = new Intent(SplashScreen.this,Login.class);
//                startActivity(i);
//                finish();
            }
        }, 5000);
    }


    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {

                try {
                    File file1 = new File(getExternalFilesDir("SCM").getPath());
                    if (!file1.exists())
                        file1.mkdirs();
                    CekLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        permissionHelper.checkAndRequestPermissions();
        return true;
    }


    String statususer;

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        //OPEN
        db.openDB();
        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString(1);
        }
        getuser();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestCallBack(requestCode, permissions, grantResults);
    }

}