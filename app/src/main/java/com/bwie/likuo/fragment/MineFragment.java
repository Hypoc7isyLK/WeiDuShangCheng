package com.bwie.likuo.fragment;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.activity.FragMainAddressActivity;
import com.bwie.likuo.activity.FragMainFootprintActivity;
import com.bwie.likuo.activity.FragMainInformationActivity;
import com.bwie.likuo.activity.MineSetActivity;
import com.bwie.likuo.activity.MyCircleActivity;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.bean.UsergerenBean;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.core.http.StringUtil;
import com.bwie.likuo.persenter.TouPresenter;
import com.bwie.likuo.persenter.UsergerenPresenter;
import com.bwie.likuo.utils.SelectPicPopupWindow;
import com.facebook.drawee.view.SimpleDraweeView;
import com.greendao.gen.UserDaoDao;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.me_bg)
    SimpleDraweeView meBg;
    @BindView(R.id.mine_text_name)
    TextView mineTextName;
    @BindView(R.id.mine_layout_Information)
    RelativeLayout mineLayoutInformation;
    @BindView(R.id.mine_layout_Circle)
    RelativeLayout mineLayoutCircle;
    @BindView(R.id.mine_layout_Footprint)
    RelativeLayout mineLayoutFootprint;
    @BindView(R.id.mine_layout_Wallet)
    RelativeLayout mineLayoutWallet;
    @BindView(R.id.mine_layout_Address)
    RelativeLayout mineLayoutAddress;
    @BindView(R.id.mine_setting)
    RelativeLayout minesetting;

    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.mine_image_head)
    SimpleDraweeView mineImageHead;
    Unbinder unbinder;
    private UserDaoDao mUserDaoDao;
    private String mHeadPic;
    private String mNickName;
    SelectPicPopupWindow menuWindow;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int PERMISSIONS_REQUEST_OPEN_ALBUM=1;
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    // 裁剪后图片的宽(X)和高(Y),100 X 100的正方形。
    private static int output_X = 100;
    private static int output_Y = 100;
    TouPresenter touPresenter;
    UsergerenPresenter usergerenPresenter;
    private List<UserDao> userDaos;

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void initData(View view) {
        touPresenter = new TouPresenter(new TouHuan());
        usergerenPresenter = new UsergerenPresenter(new Usergeren());
        mUserDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        userDaos = mUserDaoDao.loadAll();
        mHeadPic = userDaos.get(0).getHeadPic();
        mNickName = userDaos.get(0).getNickName();

        mineTextName.setText(mNickName);
        meBg.setImageURI(mHeadPic);
        mineImageHead.setImageURI(mHeadPic);

        /*Intent intent = getActivity().getIntent();
        String nikeName = intent.getStringExtra("nikeName");
        mineTextName.setText(nikeName);
        Log.e("lk","名"+nikeName);*/
    }

    @Override
    public int getContent() {
        return R.layout.fragment_mine;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_layout_Information, R.id.mine_layout_Circle, R.id.mine_layout_Footprint, R.id.mine_layout_Wallet, R.id.mine_layout_Address, R.id.mine_image_head, R.id.mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_layout_Information:
                startActivity(new Intent(getActivity(),FragMainInformationActivity.class));
                break;
            case R.id.mine_layout_Circle:
                startActivity(new Intent(getActivity(),MyCircleActivity.class));
                break;
            case R.id.mine_layout_Footprint:
                startActivity(new Intent(getActivity(),FragMainFootprintActivity.class));
                break;
            case R.id.mine_layout_Wallet:

                break;
            case R.id.mine_layout_Address:
                startActivity(new Intent(getActivity(), FragMainAddressActivity.class));

                break;
            case R.id.mine_image_head:
                /*UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
                List<UserDao> userDaos = userDaoDao.loadAll();*/

                if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_OPEN_ALBUM);
                }else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    menuWindow = new SelectPicPopupWindow(getActivity(), itemsOnClick);
                    //显示窗口
                    //设置layout在PopupWindow中显示的位置
                    menuWindow.showAtLocation(getActivity().findViewById(R.id.rela_my_info), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    usergerenPresenter.reqeust(userDaos.get(0).getUserId(),userDaos.get(0).getSessionId());
                }
                break;
            case R.id.mine_setting:
                startActivity(new Intent(getActivity(), MineSetActivity.class));
                break;
        }


    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                //通过拍照获取照片
                case R.id.btn_take_photo:
                    choseHeadImageFromCameraCapture();
                    break;
                //从相册获取照片
                case R.id.btn_pick_photo:
                    choseHeadImageFromGallery();
                    break;
                default:
                    break;
            }
        }
    };
    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型


        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_PICK);

        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }
    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));


        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
//                if (intent != null) {// 得到图片的全路径
//                    Uri uri = intent.getData();
//                    Haha(uri);
//                }


                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);

                    cropRawPhoto(Uri.fromFile(tempFile));
//                    File file = new File(Uri.fromFile(tempFile));
//                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
//                    MultipartBody.Part body = MultipartBody.Part.createFormData("app_user_header", fileNameByTimeStamp, requestFile);

                } else {
                    Toast.makeText(getActivity(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        String path = StringUtil.getRealPathFromUri(getContext(),uri);
        touPresenter.reqeust(userDaos.get(0).getUserId(),userDaos.get(0).getSessionId(),path);
        Bitmap map = null;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        //  intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(LOGO_ZOOM_FILE_PATH));
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }
    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView (Intent intent){
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");

            meBg.setImageBitmap(photo);
            mineImageHead.setImageBitmap(photo);

        }
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard () {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
    private class TouHuan implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(getActivity(),"更换头像成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(),data.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Usergeren implements DataCall<Result<UsergerenBean>> {
        @Override
        public void success(Result<UsergerenBean> data) {
            mineTextName.setText(data.getResult().getNickName());
            meBg.setImageURI(data.getResult().getHeadPic());
            mineImageHead.setImageURI(data.getResult().getHeadPic());
            userDaos.get(0).setHeadPic(data.getResult().getHeadPic());
            userDaos.get(0).setNickName(data.getResult().getNickName());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
