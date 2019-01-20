package com.bwie.likuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.MainActivity;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.persenter.RegisterPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText register_edittext_phone;
    private EditText register_edittext_verification;
    private EditText register_edittext_pwd;
    private ImageView main_imageview_click;
    private TextView register_textview_login;
    private Button register_button_register;
    private IRetrofitRx iRetrofitRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button_register:
                register();
                break;
            case R.id.register_textview_login:
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    private void register() {
        String phone = register_edittext_phone.getText().toString().trim();
        String pwd = register_edittext_pwd.getText().toString().trim();
        /*iRetrofitRx.ShowRxregister(phone,pwd)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if (result.getStatus().equals("0000")){
                            Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
        RegisterPresenter registerPresenter = new RegisterPresenter(new RegisterCall());
        registerPresenter.reqeust(phone,pwd);
    }


    private void initView() {

        register_edittext_phone = (EditText) findViewById(R.id.register_edittext_phone);
        register_edittext_phone.setOnClickListener(this);
        register_edittext_verification = (EditText) findViewById(R.id.register_edittext_verification);
        register_edittext_verification.setOnClickListener(this);
        register_edittext_pwd = (EditText) findViewById(R.id.register_edittext_pwd);
        register_edittext_pwd.setOnClickListener(this);
        main_imageview_click = (ImageView) findViewById(R.id.main_imageview_click);
        main_imageview_click.setOnClickListener(this);
        register_textview_login = (TextView) findViewById(R.id.register_textview_login);
        register_textview_login.setOnClickListener(this);
        register_button_register = (Button) findViewById(R.id.register_button_register);
        register_button_register.setOnClickListener(this);
    }


    private class RegisterCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(RegisterActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(RegisterActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
