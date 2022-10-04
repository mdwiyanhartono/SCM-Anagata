package dwiyan.com.scm_anagata.DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, Environment.getExternalStoragePublicDirectory("Android/data/").getAbsolutePath() + "/dwiyan.com.scm_anagata/files/SCM/" + Constant.DB_NAME, null, Constant.DB_VERSION);
    }


    //WHEN TB IS CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("createdb", "New ");
        try {
            db.execSQL(Constant.CREATE_TB1);
            db.execSQL(Constant.CREATE_TB2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("versionluar", "Updating table from " + oldVersion + " to " + newVersion);
//        if (oldVersion < 2) {
//            db.execSQL("DROP TABLE IF EXISTS " + Constant.TB_NAME4);
//            db.execSQL(Constant.CREATE_TB4);
//        }
        Log.d("versionluar", String.valueOf(newVersion) + String.valueOf(oldVersion));
    }

}