package com.cheeshou.cheeshou.remote;

import com.cheeshou.cheeshou.dealer.ui.activity.AllOptionResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerInfoResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerStatusResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.MyReportResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.MySaleCountResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.NearDaySaleResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.ReportCarResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.SalersListResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.StoreManagerResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.ToShopResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.XsUserDetailResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.XsUserResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.XsUserStatResponse;
import com.cheeshou.cheeshou.main.login.LoginResponse;
import com.cheeshou.cheeshou.options.model.response.AreaProvinceResponse;
import com.cheeshou.cheeshou.options.model.response.CarBrandResponse;
import com.cheeshou.cheeshou.options.model.response.CarDetailResponse;
import com.cheeshou.cheeshou.options.model.response.CarsModelResponse;
import com.cheeshou.cheeshou.options.model.response.CarsResponse;
import com.cheeshou.cheeshou.options.model.response.CommonResponse;
import com.cheeshou.cheeshou.options.model.response.HotCarCountResponse;
import com.cheeshou.cheeshou.options.model.response.HotCarListResponse;
import com.cheeshou.cheeshou.options.model.response.ModifyCarInforResponse;
import com.cheeshou.cheeshou.options.model.response.OptionTypeResponse;
import com.cheeshou.cheeshou.options.model.response.RegisonNameResponse;
import com.cheeshou.cheeshou.options.model.response.SalesAreaResponse;
import com.cheeshou.cheeshou.order.response.OrderDetailResponse;
import com.cheeshou.cheeshou.order.response.OrderListResponse;
import com.cheeshou.cheeshou.usercenter.model.DealerShipResponse;
import com.cheeshou.cheeshou.usercenter.model.UserInforModel;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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

    //查询车辆所在地
    @FormUrlEncoded
    @POST("region/findRegionListByPid")
    Observable<AreaProvinceResponse> getRegionList(@Field("token") String token, @Field("pid") String pid);

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

    //库存列表
    @FormUrlEncoded
    @POST("car/findCarCount")
    Observable<StoreManagerResponse> getInventoryList(@Field("token") String token);

    //全国车源
    @FormUrlEncoded
    @POST("car/findCarList")
    Observable<AllOptionResponse> getCarList(@Header("token") String token, @Field("pageSize") String pageSize, @Field("page") String page, @Field("scopeType") String scopeType,
                                             @Field("carType") String carType, @Field("brandId") String brandId, @Field("versionId") String versionId, @Field("carYear") String carYear, @Field("outsiteColor") String outsiteColor,
                                             @Field("withinColor") String withinColor, @Field("minCarPrice") String minCarPrice, @Field("maxCarPrice") String maxCarPrice, @Field("startDate") String startDate,
                                             @Field("endDate") String endDate, @Field("queryKey") String queryKey, @Field("carStatus") String carStatus, @Field("orderType") String orderType);

    //全国车源
    @FormUrlEncoded
    @POST("car/findCarDetailByCarId")
    Observable<CarDetailResponse> getCarDetail(@Field("token") String token, @Field("carId") String carId);


    //销售人员列表
    @FormUrlEncoded
    @POST("user/findXsUserList")
    Observable<XsUserResponse> getClientList(@Field("token") String token, @Field("queryKey") String queryKey, @Field("pageSize") String pageSize, @Field("page") String page);


    //新增销售人员
    @Multipart
    @POST("user/saveXsUserInfo")
    Observable<EasyResponse> saveXsUserInfo(@Header("token") String token, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> params);

    //新增销售人员 无File
    @Multipart
    @POST("user/saveXsUserInfo")
    Observable<EasyResponse> saveXsUserInfo2(@Header("token") String token, @PartMap Map<String, RequestBody> params);


    //销售人员详情
    @FormUrlEncoded
    @POST("user/getXsUserInfoByUserId")
    Observable<XsUserDetailResponse> getXsUserInfoByUserId(@Field("token") String token, @Field("userId") String userId);


    //重置密码
    @FormUrlEncoded
    @POST("user/resetXsUserPass")
    Observable<EasyResponse> resetXsUserPass(@Header("token") String token, @Field("userId") String userId, @Field("password") String password);

    //修改密码
    @FormUrlEncoded
    @POST("user/updateMyPassword")
    Observable<EasyResponse> updateMyPassword(@Header("token") String token, @Field("password") String password, @Field("repwd") String repwd, @Field("oldPass") String oldPass);

    //新增客户
    @Multipart
    @POST("customer/saveCustomerInfo")
    Observable<EasyResponse> saveCustomerInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    //获取客户基本信息
    @Multipart
    @POST("customer/findCustomerBaseInfo")
    Observable<CustomerInfoResponse> getCustomerInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    //获取客户详细信息
//    CustomerDetailResponse
    @Multipart
    @POST("customer/findCustomerDetailInfo")
    Observable<JSONObject> getCustomerDetailInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    @Multipart
    @POST("customer/updateCustomerBaseInfo")
    Observable<EasyResponse> updateCustomerInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);


    //获取客户列表
    @Multipart
    @POST("customer/findAllCustomerInfoList")
    Observable<CustomerResponse> getCustomerList(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    //销售人员列表(无分页)
    @FormUrlEncoded
    @POST("user/findAllXsUserList")
    Observable<SalersListResponse> findAllXsUserList(@Field("token") String token);

    //保存车源信息
    @Multipart
    @POST("car/saveCarInfo")
    Observable<EasyResponse> saveCarInfo(@Header("token") String token, @Part List<MultipartBody.Part> files, @PartMap Map<String, RequestBody> params);


    //保存车源信息
    @Multipart
    @POST("car/saveCarInfo")
    Observable<EasyResponse> saveCarInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);


    //订单列表
    @FormUrlEncoded
    @POST("order/findMyOrderList")
    Observable<OrderListResponse> findMyOrderList(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize,
                                                  @Field("xsUserId") String xsUserId, @Field("startDate") String startDate, @Field("endDate") String endDate,
                                                  @Field("minPrice") String minPrice, @Field("maxPrice") String maxPrice, @Field("queryKey") String queryKey,
                                                  @Field("orderType") String orderType);


    //订单详情
    @FormUrlEncoded
    @POST("order/findOrderDetail")
    Observable<OrderDetailResponse> findOrderDetail(@Header("token") String token, @Field("orderItemId") String orderItemId);


    //本月进店列表
    @FormUrlEncoded
    @POST("customer/findMonthEnterCustomerInfo")
    Observable<ToShopResponse> findMonthEnterCustomerInfo(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);

    //本日进店列表
    @FormUrlEncoded
    @POST("customer/findDayEnterCustomerInfo")
    Observable<ToShopResponse> findDayEnterCustomerInfo(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);

    //本日新增列表
    @FormUrlEncoded
    @POST("customer/findDayCustomerInfo")
    Observable<ToShopResponse> findDayCustomerInfo(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);

    //本月新增列表
    @FormUrlEncoded
    @POST("customer/findMonthCustomerInfo")
    Observable<ToShopResponse> findMonthCustomerInfo(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);


    //本日成交列表
    @FormUrlEncoded
    @POST("order/findDayOrderList")
    Observable<ReportCarResponse> findDayOrderList(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);

    //本月成交列表
    @FormUrlEncoded
    @POST("order/findMonthOrderList")
    Observable<ReportCarResponse> findMonthOrderList(@Header("token") String token, @Field("page") String page, @Field("pageSize") String pageSize);

    //统计报表数量列表
    @FormUrlEncoded
    @POST("customer/findCustomerCount")
    Observable<MyReportResponse> findCustomerCount(@Field("token") String token);

    //预定车辆
    @FormUrlEncoded
    @POST("sale/reserveSaleCarInfo")
    Observable<EasyResponse> reserveSaleCarInfo(@Header("token") String token, @Field("saleId") String saleId);

    //取消预定
    @FormUrlEncoded
    @POST("sale/unReserveSaleCarInfo")
    Observable<EasyResponse> unReserveSaleCarInfo(@Header("token") String token, @Field("saleId") String saleId);


    //获取车源热度数量
    @FormUrlEncoded
    @POST("car/findHotCarCount")
    Observable<HotCarCountResponse> findHotCarCount(@Field("token") String token, @Field("startDate") String startDate, @Field("endDate") String endDate);

    //获取车源热度列表
    @FormUrlEncoded
    @POST("car/findHotCarList")
    Observable<HotCarListResponse> findHotCarList(@Field("token") String token, @Field("startDate") String startDate, @Field("endDate") String endDate,
                                                  @Field("pageSize") String pageSize, @Field("page") String page);

    //删除销售
    @FormUrlEncoded
    @POST("user/updateXsUserInfo")
    Observable<EasyResponse> updateXsUserInfo(@Field("token") String token, @Field("userId") String userId);


    //车行信息
    @FormUrlEncoded
    @POST("company/findMyCompanyInfo")
    Observable<DealerShipResponse> findMyCompanyInfo(@Field("token") String token);

//    @Multipart
//    @POST("sale/batchShelvesCarInfo")
//    Observable<EasyResponse> batchShelvesCarInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    // 上架车辆
    @FormUrlEncoded
    @POST("sale/batchShelvesCarInfo")
    Observable<EasyResponse> batchShelvesCarInfo(@Field("token") String token, @Field("insuranceRebates") String insuranceRebates, @Field("loanRebates") String loanRebates, @Field("carJson") String carJson);

    //
    @FormUrlEncoded
    @POST("dict/getCustomerStatusList")
    Observable<CustomerStatusResponse> getCustomerStatusList(@Field("token") String token);


    @FormUrlEncoded
    @POST("user/findXsUserStatList")
    Observable<XsUserStatResponse> findXsUserStatList(@Field("token") String token, @Field("startDate") String startDate, @Field("endDate") String endDate,@Field("orderType") String orderType);


    //获得30天销售量
    @FormUrlEncoded
    @POST("order/getNearDaySale")
    Observable<NearDaySaleResponse> getNearDaySale(@Field("token") String token, @Field("startDate") String startDate, @Field("endDate") String endDate);


    //获得今日销售量
    @FormUrlEncoded
    @POST("order/getMySaleCount")
    Observable<MySaleCountResponse> getMySaleCount(@Field("token") String token, @Field("startDate") String startDate, @Field("endDate") String endDate);

    //车源下架
    @FormUrlEncoded
    @POST("sale/soldOutCarInfo")
    Observable<EasyResponse> soldOutCarInfo(@Field("token") String token, @Field("saleId") String saleId);

    //编辑销售车辆信息
    @FormUrlEncoded
    @POST("sale/findSaleCarInfoBySaleId")
    Observable<ModifyCarInforResponse> findSaleCarInfoBySaleId(@Field("token") String token, @Field("saleId") String saleId);

    //保存销售车辆信息
    @FormUrlEncoded
    @POST("sale/updateSaleCarInfo")
    Observable<EasyResponse> updateSaleCarInfo(@Field("token") String token, @Field("saleId") String saleId, @Field("saleCarPrice") String saleCarPrice,
                                               @Field("insuranceRebates") String insuranceRebates, @Field("loanRebates") String loanRebates);

    //用户跟进保存

    @Multipart
    @POST("customer/saveCustomerProgressInfo")
    Observable<EasyResponse> saveCustomerProgressInfo(@Header("token") String token, @Part List<MultipartBody.Part> files, @PartMap Map<String, RequestBody> params);

    //通过区域名称获得区域信息
    @FormUrlEncoded
    @POST("region/findRegionByName")
    Observable<RegisonNameResponse> findRegionByName(@Field("token") String token, @Field("cityName") String cityName);

    //修改客户需求
    @Multipart
    @POST("customer/updateCustomerNeedInfo")
    Observable<EasyResponse> updateCustomerNeedInfo(@Header("token") String token, @PartMap Map<String, RequestBody> params);


    //生成分享URL
    @Multipart
    @FormUrlEncoded
    @POST("/share/saveShareInfo")
    Observable<EasyResponse> saveShareInfo(@Header("token") String token, @Field("shareType") String shareType, @Field("shareDirect") String shareDirect, @Field("shareDirect") String shareDirect1, @Field("shareAtt") String shareAtt);
}
