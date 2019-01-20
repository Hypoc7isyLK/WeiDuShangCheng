package com.bwie.likuo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bwie.likuo.activity.BaseActivity;
import com.bwie.likuo.activity.GuideActivity;
import com.bwie.likuo.activity.HomeActivity;
import com.bwie.likuo.activity.RegisterActivity;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.LoginActivity_LoginBean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.fragment.CircleFragment;
import com.bwie.likuo.persenter.LoginPresenter;
import com.greendao.gen.UserDaoDao;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText main_edittext_phone;
    private EditText main_edittext_pwd;
    private ImageView main_imageview_click;
    private CheckBox main_check_remember;
    private TextView main_textview_register;
    private Button main_button_login;
    private String loginphone;
    private String loginpwd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEdit;
    private boolean isHideFirst;
    private LoginPresenter loginPresenter;




    @Override
    protected int contentView() {
        /*LoginPresenter loginPresenter = new LoginPresenter(new LoginCall());
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        if (userDaos.size()==0){

        }else {

            String lphone = userDaos.get(0).getUserphone();
            String  lpwd = userDaos.get(0).getUserpwd();
            loginPresenter.reqeust(lphone,lpwd);
        }*/
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        if(userDaos.size()>0) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
        return R.layout.activity_main;

    }

    @Override
    protected void initView() {

        main_edittext_phone = (EditText) findViewById(R.id.main_edittext_phone);
        main_edittext_pwd = (EditText) findViewById(R.id.main_edittext_pwd);
        main_imageview_click = (ImageView) findViewById(R.id.main_imageview_click);
        main_check_remember = (CheckBox) findViewById(R.id.main_check_remember);
        main_textview_register = (TextView) findViewById(R.id.main_textview_register);
        main_button_login = (Button) findViewById(R.id.main_button_login);
        main_button_login.setOnClickListener(this);
        main_textview_register.setOnClickListener(this);
        main_imageview_click.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        sharedPreferences = getSharedPreferences("lk", MODE_PRIVATE);
        mEdit = sharedPreferences.edit();
        boolean ismain_check_remember = sharedPreferences.getBoolean("isjzmm", false);
        String username = sharedPreferences.getString("phone", "");
        String password = sharedPreferences.getString("pwd", "");
        if (ismain_check_remember) {
            main_check_remember.setChecked(true);
            main_edittext_phone.setText(username);
            main_edittext_pwd.setText(password);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_login:
                login();
                break;
            case R.id.main_textview_register:
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

                break;
            case R.id.main_imageview_click:
                eyes();
                break;
        }
    }

    private void eyes() {
        main_imageview_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHideFirst == true) {
                    //密文
                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    main_edittext_pwd.setTransformationMethod(method1);
                    isHideFirst = false;
                    main_imageview_click.setImageResource(R.mipmap.biyan);
                } else {
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    main_edittext_pwd.setTransformationMethod(method);
                    isHideFirst = true;
                    main_imageview_click.setImageResource(R.mipmap.login_icon_eye_n_hdpi);
                }
                // 光标的位置
                int index = main_edittext_pwd.getText().toString().length();
                main_edittext_pwd.setSelection(index);

            }
        });
    }

    public void login(){
        loginphone = main_edittext_phone.getText().toString().trim();
        loginpwd = main_edittext_pwd.getText().toString().trim();
        if (main_check_remember.isChecked()) {
            mEdit.putString("phone", loginphone);
            mEdit.putString("pwd", loginpwd);
            mEdit.putBoolean("isjzmm", true);
            mEdit.commit();
        }else {
            mEdit.putString("phone", "");
            mEdit.putString("pwd", "");
            mEdit.putBoolean("isjzmm", false);
            mEdit.commit();
        }

        loginPresenter = new LoginPresenter(new LoginCall());
        loginPresenter.reqeust(loginphone,loginpwd);
    }


    private class LoginCall implements DataCall<Result<LoginActivity_LoginBean>> {

        @Override
        public void success(Result<LoginActivity_LoginBean> data) {
            if (data.getStatus().equals("0000")){

                UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
                UserDao userDao = new UserDao(data.getResult().getHeadPic(), data.getResult().getNickName(), data.getResult().getPhone(), data.getResult().getSessionId(), data.getResult().getSex() + "", data.getResult().getUserId(),loginphone,loginpwd);
                List<UserDao> userDaos = userDaoDao.loadAll();
                if (userDaos.size()!= 0){
                    //userDaoDao.deleteAll();
                }else {

                }
                //添加到数据库
                userDaoDao.insert(userDao);
                Log.e("lk","phone"+loginphone+"pwd"+loginpwd);

                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }else {
                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }


}
