package com.bwie.likuo.core.http;

import com.bwie.likuo.bean.Cart_List_Bean;
import com.bwie.likuo.bean.CircleFragment_ShowCircle;
import com.bwie.likuo.bean.DetailsBean;
import com.bwie.likuo.bean.DetailsCommentsBeans;
import com.bwie.likuo.bean.HomeFragment_BannerBean;
import com.bwie.likuo.bean.HomeFragment_HotBean;
import com.bwie.likuo.bean.HomeFragment_SearchBean;
import com.bwie.likuo.bean.Indent_All_Order_Bean;
import com.bwie.likuo.bean.LoginActivity_LoginBean;
import com.bwie.likuo.bean.Mine_Address_RecyclerView_Bean;
import com.bwie.likuo.bean.MyFootPrint_Bean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.bean.UsergerenBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * date:2018/12/28
 * author:李阔(淡意衬优柔)
 * function:
 */

public interface IRetrofitRx {
    /**
     *登陆
     */
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Observable<Result<LoginActivity_LoginBean>> ShowRxlogin(@Field("phone") String phone, @Field("pwd") String pwd);
    /**
     *注册
     */
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Observable<Result> ShowRxregister(@Field("phone") String phone, @Field("pwd") String pwd);
    /**
     *首页Banner
     */
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<HomeFragment_BannerBean>>> BanNer();
    /**
     *首页列表展示
     */
    @GET("small/commodity/v1/commodityList")
    Observable<Result<HomeFragment_HotBean>> showhome();
    /**
     *搜索
     */
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<HomeFragment_SearchBean>>> ShowSearch(@Query("keyword") String keyword,@Query("count") int count,@Query("page")int page);
    /**
     *圈子展示
     */
    @GET("small/circle/v1/findCircleList")
    Observable<Result<List<CircleFragment_ShowCircle>>> ShowCircle(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("count") int count,@Query("page") int page);
    /**
     *购物车查询
     */
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<Result<List<Cart_List_Bean>>> ShowCart(@Header("userId") int userId,@Header("sessionId") String sessionId);
    /**
     *详情页展示
     */
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<Result<DetailsBean>> ShowDetails(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("commodityId") String commodityId);
    /**
     *详情页评论
     */
    @GET("small/commodity/v1/CommodityCommentList")
    Observable<Result<List<DetailsCommentsBeans>>> ShowDetailsComments(@Query("commodityId") String commodityId,@Query("count") int count, @Query("page") int page);
    //添加到购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<Result> ShowInsertCart(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("data") String data);
    /**
     *Mine我的收获地址
     */
    @GET("small/user/verify/v1/receiveAddressList")
    Observable<Result<List<Mine_Address_RecyclerView_Bean>>> ShowAddress(@Header("userId") int userId,@Header("sessionId") String sessionId);
    /**
     *Mine我的添加收获地址
     */
    @POST("small/user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<Result> AddAddress(@Header("userId") int userId,@Header("sessionId") String sessionId,@Field("realName") String realName,@Field("phone") String phone,@Field("address") String address,@Field("zipCode") String zipCode);
    /**
     *Mine我的足迹
     */
    @GET("small/commodity/verify/v1/browseList")
    Observable<Result<List<MyFootPrint_Bean>>> ShowFoot(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("count") int count,@Query("page") int page);
    /**
     *Mine修改昵称
     */
    @PUT("small/user/verify/v1/modifyUserNick")
    @FormUrlEncoded
    Observable<Result> ShowUpdatename(@Header("userId") int userId,@Header("sessionId") String sessionId,@Field("nickName") String nickName);
    /**
     *订单查询
     */
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<Result<List<Indent_All_Order_Bean>>> ShowAllorder(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("status") int status,@Query("count") int count,@Query("page") int page);
    /**
     * 发布圈子
     */
    @POST("small/circle/verify/v1/releaseCircle")
    Observable<Result> PublishCircle(@Header("userId") int userId,
                                     @Header("sessionId")String sessionId,
                                     @Body MultipartBody body);

    /**
     * 我发布的圈子
     */
    @GET("small/circle/verify/v1/findMyCircleById")
    Observable<Result<List<CircleFragment_ShowCircle>>> ShowMyCircle(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("count") int count,@Query("page") int page);
    /**
     * 上传头像
     */
    @POST("small/user/verify/v1/modifyHeadPic")
    Observable<RequestBody> showUpdateHead(@Header("userId") int userId , @Header("sessionId") String sessionId, @Body MultipartBody body );

    /**
     *  查询用户
     */
    @GET("small/user/verify/v1/getUserById")
    Observable<Result<UsergerenBean>> getUser(@Header("userId") int userId , @Header("sessionId") String sessionId);

}
