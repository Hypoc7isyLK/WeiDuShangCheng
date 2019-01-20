package com.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bwie.likuo.core.dao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_DAO".
*/
public class UserDaoDao extends AbstractDao<UserDao, Void> {

    public static final String TABLENAME = "USER_DAO";

    /**
     * Properties of entity UserDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property HeadPic = new Property(0, String.class, "headPic", false, "HEADPIC");
        public final static Property NickName = new Property(1, String.class, "nickName", false, "NICKNAME");
        public final static Property Phone = new Property(2, String.class, "phone", false, "PHONE");
        public final static Property SessionId = new Property(3, String.class, "sessionId", false, "SESSIONID");
        public final static Property Sex = new Property(4, String.class, "sex", false, "SEX");
        public final static Property UserId = new Property(5, int.class, "UserId", false, "USERID");
        public final static Property Userphone = new Property(6, String.class, "userphone", false, "USERPHONE");
        public final static Property Userpwd = new Property(7, String.class, "userpwd", false, "USERPWD");
    }


    public UserDaoDao(DaoConfig config) {
        super(config);
    }
    
    public UserDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_DAO\" (" + //
                "\"HEADPIC\" TEXT," + // 0: headPic
                "\"NICKNAME\" TEXT," + // 1: nickName
                "\"PHONE\" TEXT," + // 2: phone
                "\"SESSIONID\" TEXT," + // 3: sessionId
                "\"SEX\" TEXT," + // 4: sex
                "\"USERID\" INTEGER NOT NULL ," + // 5: UserId
                "\"USERPHONE\" TEXT," + // 6: userphone
                "\"USERPWD\" TEXT);"); // 7: userpwd
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserDao entity) {
        stmt.clearBindings();
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(1, headPic);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(2, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(4, sessionId);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(5, sex);
        }
        stmt.bindLong(6, entity.getUserId());
 
        String userphone = entity.getUserphone();
        if (userphone != null) {
            stmt.bindString(7, userphone);
        }
 
        String userpwd = entity.getUserpwd();
        if (userpwd != null) {
            stmt.bindString(8, userpwd);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserDao entity) {
        stmt.clearBindings();
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(1, headPic);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(2, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(4, sessionId);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(5, sex);
        }
        stmt.bindLong(6, entity.getUserId());
 
        String userphone = entity.getUserphone();
        if (userphone != null) {
            stmt.bindString(7, userphone);
        }
 
        String userpwd = entity.getUserpwd();
        if (userpwd != null) {
            stmt.bindString(8, userpwd);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public UserDao readEntity(Cursor cursor, int offset) {
        UserDao entity = new UserDao( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // headPic
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nickName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sessionId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sex
            cursor.getInt(offset + 5), // UserId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userphone
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // userpwd
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserDao entity, int offset) {
        entity.setHeadPic(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNickName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSessionId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSex(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserId(cursor.getInt(offset + 5));
        entity.setUserphone(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserpwd(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(UserDao entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(UserDao entity) {
        return null;
    }

    @Override
    public boolean hasKey(UserDao entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}