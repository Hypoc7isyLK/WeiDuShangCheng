package com.bwie.likuo.core.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2019/1/5
 * author:李阔(淡意衬优柔)
 * function:
 */
@Entity
public class UserDao {
    @Property(nameInDb = "HEADPIC")
    private String headPic;

    @Property(nameInDb = "NICKNAME")
    private String nickName;

    @Property(nameInDb = "PHONE")
    private String phone;

    @Property(nameInDb = "SESSIONID")
    private String sessionId;

    @Property(nameInDb = "SEX")
    private String sex;

    @Property(nameInDb = "USERID")
    private int UserId;

    @Property(nameInDb = "USERPHONE")
    private String userphone;

    @Property(nameInDb = "USERPWD")
    private String userpwd;


    @Keep
    public UserDao(String headPic, String nickName, String phone, String sessionId,
            String sex, int UserId,String userphone,String userpwd) {
        this.headPic = headPic;
        this.nickName = nickName;
        this.phone = phone;
        this.sessionId = sessionId;
        this.sex = sex;
        this.UserId = UserId;
        this.userphone = userphone;
        this.userpwd = userpwd;
    }

    @Keep
    public UserDao() {
    }

    public String getHeadPic() {
        return this.headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return this.UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}
