package com.example.app_giay.retrofit;

import com.example.app_giay.model.DonHangModel;
import com.example.app_giay.model.LoaiSpModel;
import com.example.app_giay.model.MessageModel;
import com.example.app_giay.model.SanPhamMoiModel;
import com.example.app_giay.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("getLoaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
        @Field("page") int page,
        @Field("loai") int loai
    );

    @POST("dangky.php")
    @FormUrlEncoded
    Observable<UserModel> dangky(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phone_number") String phone,
            @Field("password") String pass,
            @Field("uid") String uid

    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("password") String pass
    );


    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> createOrder(
            @Field("user_id") int user_id,
            @Field("address") String address,
            @Field("phone_number") String phone,
            @Field("email") String email,
            @Field("num") int num,
            @Field("total_money") String total_money,
            @Field("chitiet") String chitiet
    );
    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("user_id") int id
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );

    @POST("gettoken.php")
    @FormUrlEncoded
    Observable<UserModel> gettoken(
            @Field("role") int role
    );
}
