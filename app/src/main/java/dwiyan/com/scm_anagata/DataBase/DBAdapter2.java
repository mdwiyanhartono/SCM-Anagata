package dwiyan.com.scm_anagata.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import dwiyan.com.scm_anagata.API.Retroserver;

public class DBAdapter2 {
    Context c;
    Retroserver d;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter2(Context ctx) {
        this.c = ctx;
        helper = new DBHelper(c);
    }

    //OPEN DB
    public void openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CLOSE
    public void close() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //INSERT DATA TO DB
    public long adduser(String nama, String email, String UserId) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.nama, nama);
            cv.put(Constant.email, email);
            cv.put(Constant.UserID, UserId);
            cv.put(Constant.status, 1);
//            db.delete(Constant.TB_NAME1, null, null);
            return db.insert(Constant.TB_NAME1, null, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addimage(String userId, String codeimage, String filepath, String keterangan, String tanggal, String typefile, String statuskirim) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.UserID, userId);
            cv.put(Constant.code_image, codeimage);
            cv.put(Constant.file_path, filepath);
            cv.put(Constant.keterangan, keterangan);
            cv.put(Constant.tanggal, tanggal);
            cv.put(Constant.typefile, typefile);
            cv.put(Constant.status_kirim, statuskirim);

            return db.insert(Constant.TB_NAME2, Constant.id, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GET DATA

    public Cursor getimage(String UserId, String codeimg, String type) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME2 + " where " + Constant.UserID + " = ? AND " + Constant.code_image + "=? AND " + Constant.typefile + "=?", new String[]{UserId, codeimg, type});

        // return db.query(Constant.TB_NAME2,null ,Constant.anggota_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }
    public Cursor getimage(String codeimg) {
        return db.rawQuery("SELECT * FROM " + Constant.TB_NAME2 + " where " +  Constant.code_image + " =?", new String[]{codeimg});

        // return db.query(Constant.TB_NAME2,null ,Constant.anggota_BAYAR+" = ?",new String[]{"Full"},null,null,null);
    }
    public Cursor getUser() {
        String[] columns = {Constant.nama, Constant.status, Constant.email, Constant.UserID};

        return db.query(Constant.TB_NAME1, columns, Constant.status + " = ?", new String[]{"1"}, null, null, null);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETE


    public long Deleteuser() {
        try {

            return db.delete(Constant.TB_NAME1, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long DeleteImage(String Id) {
        try {

            return db.delete(Constant.TB_NAME2, Constant.id + " =?", new String[]{Id});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public long DeleteImageAll() {
        try {

            return db.delete(Constant.TB_NAME2, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}