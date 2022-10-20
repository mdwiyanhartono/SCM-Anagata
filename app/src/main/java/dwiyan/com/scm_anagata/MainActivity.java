package dwiyan.com.scm_anagata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.FragmentMainMenu.Account;
import dwiyan.com.scm_anagata.FragmentMainMenu.Chat;
import dwiyan.com.scm_anagata.FragmentMainMenu.Home;
import dwiyan.com.scm_anagata.FragmentMainMenu.Notification;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnav);

        GetTokenFCM();
        SetTopic();
        getSupportFragmentManager().beginTransaction().replace(R.id.lyFragment, new Home()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.setNavigationBarColor(getResources().getColor(R.color.basic));
//            w.setStatusBarColor(getResources().getColor(R.color.transparent));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new Home();
                        getSupportFragmentManager().beginTransaction().replace(R.id.lyFragment, fragment).commit();
                        break;
                    case R.id.feed:
                        fragment = new Chat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.lyFragment, fragment).commit();
                        break;
//                    case R.id.notif:
//                        fragment = new Notification();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.lyFragment, fragment).commit();
//                        break;
                    case R.id.account:
                        fragment = new Account();
                        getSupportFragmentManager().beginTransaction().replace(R.id.lyFragment, fragment).commit();
                        break;
                }

                return true;
            }
        });
    }


}