package pmp.tianxundai.com.notepaddemo.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STUDENTS_BEAN".
*/
public class StudentsBeanDao extends AbstractDao<StudentsBean, Long> {

    public static final String TABLENAME = "STUDENTS_BEAN";

    /**
     * Properties of entity StudentsBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Data = new Property(2, String.class, "data", false, "DATA");
        public final static Property Time = new Property(3, String.class, "time", false, "TIME");
        public final static Property Week = new Property(4, String.class, "week", false, "WEEK");
        public final static Property ABoolean = new Property(5, Boolean.class, "aBoolean", false, "A_BOOLEAN");
        public final static Property TheEventContent = new Property(6, String.class, "theEventContent", false, "THE_EVENT_CONTENT");
        public final static Property DataContent = new Property(7, String.class, "dataContent", false, "DATA_CONTENT");
    }


    public StudentsBeanDao(DaoConfig config) {
        super(config);
    }
    
    public StudentsBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENTS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"DATA\" TEXT," + // 2: data
                "\"TIME\" TEXT," + // 3: time
                "\"WEEK\" TEXT," + // 4: week
                "\"A_BOOLEAN\" INTEGER," + // 5: aBoolean
                "\"THE_EVENT_CONTENT\" TEXT," + // 6: theEventContent
                "\"DATA_CONTENT\" TEXT);"); // 7: dataContent
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STUDENTS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StudentsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(3, data);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String week = entity.getWeek();
        if (week != null) {
            stmt.bindString(5, week);
        }
 
        Boolean aBoolean = entity.getABoolean();
        if (aBoolean != null) {
            stmt.bindLong(6, aBoolean ? 1L: 0L);
        }
 
        String theEventContent = entity.getTheEventContent();
        if (theEventContent != null) {
            stmt.bindString(7, theEventContent);
        }
 
        String dataContent = entity.getDataContent();
        if (dataContent != null) {
            stmt.bindString(8, dataContent);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StudentsBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(3, data);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String week = entity.getWeek();
        if (week != null) {
            stmt.bindString(5, week);
        }
 
        Boolean aBoolean = entity.getABoolean();
        if (aBoolean != null) {
            stmt.bindLong(6, aBoolean ? 1L: 0L);
        }
 
        String theEventContent = entity.getTheEventContent();
        if (theEventContent != null) {
            stmt.bindString(7, theEventContent);
        }
 
        String dataContent = entity.getDataContent();
        if (dataContent != null) {
            stmt.bindString(8, dataContent);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public StudentsBean readEntity(Cursor cursor, int offset) {
        StudentsBean entity = new StudentsBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // data
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // week
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // aBoolean
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // theEventContent
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // dataContent
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StudentsBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setData(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWeek(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setABoolean(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setTheEventContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDataContent(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StudentsBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StudentsBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StudentsBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
