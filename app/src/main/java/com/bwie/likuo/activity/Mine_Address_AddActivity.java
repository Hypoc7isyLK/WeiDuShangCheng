package com.bwie.likuo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.MineAddAddressPresenter;
import com.greendao.gen.UserDaoDao;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

public class Mine_Address_AddActivity extends BaseActivity {


    private EditText mine_address_add_name;
    private CityPicker mBuild;
    private EditText mine_address_add_phone;
    private TextView mine_address_add_diqu;
    private EditText mine_address_add_xiangxi;
    private EditText mine_address_add_youzheng;
    private Button mine_address_add_cun;
    private MineAddAddressPresenter mMineAddAddressPresenter;

    @Override
    protected int contentView() {
        return R.layout.activity_mine__address__add;
    }

    @Override
    protected void initView() {
        mine_address_add_diqu = findViewById(R.id.mine_address_add_diqu);
        mine_address_add_name = findViewById(R.id.mine_address_add_name);
        mine_address_add_phone = findViewById(R.id.mine_address_add_phone);
        mine_address_add_xiangxi = findViewById(R.id.mine_address_add_xiangxi);
        mine_address_add_youzheng = findViewById(R.id.mine_address_add_youzheng);
        mine_address_add_cun = findViewById(R.id.mine_address_add_cun);
    }

    @Override
    protected void initData() {
        //三级联动
        initSan();
        mine_address_add_cun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
                List<UserDao> userDaos = userDaoDao.loadAll();
                UserDao userDao = userDaos.get(0);
                int userId = userDao.getUserId();
                String sessionId = userDao.getSessionId();
                String name = mine_address_add_name.getText().toString().trim();
                String phone = mine_address_add_phone.getText().toString().trim();
                String xiangxi = mine_address_add_xiangxi.getText().toString().trim();
                String diqu = mine_address_add_diqu.getText().toString().trim();
                String youzheng = mine_address_add_youzheng.getText().toString().trim();
                /**
                 * qingqiu
                 */

                mMineAddAddressPresenter = new MineAddAddressPresenter(new AddAdressCall());
                mMineAddAddressPresenter.reqeust(userId,sessionId,name,phone,diqu+""+xiangxi,youzheng);


            }
        });


    }

    //三级联动
    private void initSan() {
        mine_address_add_diqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPiker();
                mBuild.show();

            }
        });
    }
    //三级联动
    public void initPiker() {

        mBuild = new CityPicker.Builder(Mine_Address_AddActivity.this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
        mBuild.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                //省份
                String province = strings[0];
                String city = strings[1];
                String district = strings[2];
                String code = strings[3];
                mine_address_add_diqu.setText(province + city + district);
                Log.e("aaaaaaaaaaaaaa", mine_address_add_diqu.getText().toString());
            }

            @Override
            public void onCancel() {

            }
        });

    }


    private class AddAdressCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            String status = data.getStatus();
            if (status.equals("0000")){
                Toast.makeText(Mine_Address_AddActivity.this, "亲 添加成功了呢！", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Mine_Address_AddActivity.this,FragMainAddressActivity.class));

                finish();
            }else{
                Toast.makeText(Mine_Address_AddActivity.this, "亲 添加失败了呢！", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
