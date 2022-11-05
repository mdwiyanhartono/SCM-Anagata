package dwiyan.com.scm_anagata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.io.File;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.BaseFragment;
import dwiyan.com.scm_anagata.DataBase.DBAdapter2;
import dwiyan.com.scm_anagata.permision.PermissionHelper;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends BaseActivity {

    PermissionHelper permissionHelper;
    int UNINSTALL_REQUEST_CODE = 1;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        permissionHelper = new PermissionHelper(this);
        cekupdate();
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

    private void cekupdate() {
//        Toast.makeText(this, "Cek Update", Toast.LENGTH_SHORT).show();
        AppUpdateManager updateManager = AppUpdateManagerFactory.create(SplashScreen.this);
        Task<AppUpdateInfo> appUpdateInfoTask = updateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                Toast.makeText(this, "Ada Update", Toast.LENGTH_SHORT).show();
                try {
                    updateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, SplashScreen.this, REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else {
//                cekupdate();
//                Toast.makeText(this, "Tidak Ada Update", Toast.LENGTH_SHORT).show();
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//                CheckRootTask checkRootTask = new CheckRootTask(SplashScreen.this, SplashScreen.this);
//                checkRootTask.execute(true);
            }
        });
    }


}