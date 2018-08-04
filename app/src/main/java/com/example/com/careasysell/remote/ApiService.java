package com.example.com.careasysell.remote;

import com.example.com.careasysell.main.login.LoginResponse;
import com.example.com.careasysell.options.model.response.CarBrandResponse;
import com.example.com.careasysell.options.model.response.CarsModelResponse;
import com.example.com.careasysell.options.model.response.CarsResponse;
import com.example.com.careasysell.options.model.response.CommonResponse;
import com.example.com.careasysell.options.model.response.OptionTypeResponse;
import com.example.com.careasysell.options.model.response.SalesAreaResponse;
import com.example.com.careasysell.usercenter.model.UserInforModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 71033 on 2018/7/13.
 */
public interface ApiService {

    //登录
    @FormUrlEncoded
    @POST("login/userLogin")
    Observable<LoginResponse> login(@Field("account") String account, @Field("password") String password);

    //内饰颜色
    @FormUrlEncoded
    @POST("dict/getWithinColorList")
    Observable<CommonResponse> getInteriorColor(@Field("token") String token);

    //外观颜色
    @FormUrlEncoded
    @POST("dict/getOutsideColorList")
    Observable<CommonResponse> getAppearanceColor(@Field("token") String token);

    //车源类型
    @FormUrlEncoded
    @POST("carType/findCarTypeList")
    Observable<OptionTypeResponse> getOptionTypes(@Field("token") String token);

    //手续类型
    @FormUrlEncoded
    @POST("dict/getFormalityList")
    Observable<CommonResponse> getFormalityTypes(@Field("token") String token);

    //销售区域
    @FormUrlEncoded
    @POST("area/findUserAreaList")
    Observable<SalesAreaResponse> getSalesAreaTypes(@Field("token") String token);

    //优惠政策
    @FormUrlEncoded
    @POST("dict/getCarDiscountList")
    Observable<CommonResponse> getCarDiscountList(@Field("token") String token);

    //汽车品牌
    @FormUrlEncoded
    @POST("carBrand/findCarBrandList")
    Observable<CarBrandResponse> getCarBrand(@Field("token") String token, @Field("carType") String carType);

    //获取个人信息
    @FormUrlEncoded
    @POST("user/findMyInfo")
    Observable<UserInforModel> getUserInfo(@Field("token") String token);

    //汽车系列
    @FormUrlEncoded
    @POST("ucmsCarBrandAudi/findUcmsCarBrandAudiListByBrandId")
    Observable<CarsResponse> getCars(@Field("token") String token, @Field("brandId") String brandId);

    //汽车型号
    @FormUrlEncoded
    @POST("ucmsCarBrandVersion/findAllUcmsCarBrandVersionListByAudiId")
    Observable<CarsModelResponse> getCarsModel(@Field("token") String token, @Field("audiId") String audiId);

}
