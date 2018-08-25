package pmp.tianxundai.com.notepaddemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import pmp.tianxundai.com.notepaddemo.greendao.DaoMaster;
import pmp.tianxundai.com.notepaddemo.greendao.DaoSession;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;

/**
 *
 * @author dell-pc
 * @date 2018/8/20
 */

public class MyApplication extends Application {
    private static MyApplication mApp;
    private SQLiteDatabase database;
    private DaoSession daoSession;
    private StudentsBeanDao studentsBeanDao;
    public StudentsBeanDao getStudentsBeanDao(){
        return studentsBeanDao;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mApp, "student-db", null);
        database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        studentsBeanDao = daoSession.getStudentsBeanDao();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApplication getInstance() {
        return mApp;
    }
}
