package dwiyan.com.scm_anagata.DataBase;

public class Constant {

    static final int DB_VERSION = 1;
    static final String DB_NAME = "SCM-DB";

    static final String id = "id";
    static final String nama = "nama";
    static final String email = "email";
    static final String UserID = "userId";
    static final String NoHp = "NoHp";
    static final String LevelId = "LevelId";
    static final String PasswordTemp = "PasswordTemp";
    static final String status = "status";

    static final String TB_NAME1 = "t_user";
    static final String code_image = "code_image";
    static final String file_path = "file_path";
    static final String status_kirim = "status_kirim";
    static final String keterangan = "keterangan";
    static final String tanggal = "tanggal";
    static final String typefile = "typefile";

    static final String TB_NAME2 = "t_image";

    static final String CREATE_TB2 = "CREATE TABLE " + TB_NAME2 +
            " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UserID + " TEXT,"
            + code_image + " TEXT ,"
            + file_path + " TEXT ,"
            + keterangan + " TEXT ,"
            + tanggal + " TEXT ,"
            + typefile + " TEXT ,"
            + status_kirim + " TEXT );";

    static final String CREATE_TB1 = "CREATE TABLE " + TB_NAME1 + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + nama + " TEXT,"
            + UserID + " TEXT,"
            + email + " TEXT ,"
            + status + " INTEGER );";
}