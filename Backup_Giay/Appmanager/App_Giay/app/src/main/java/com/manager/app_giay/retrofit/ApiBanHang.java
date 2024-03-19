package com.manager.app_giay.retrofit;

import com.manager.app_giay.model.DonHangModel;
import com.manager.app_giay.model.LoaiSpModel;
import com.manager.app_giay.model.MessageModel;
import com.manager.app_giay.model.SanPhamMoiModel;
import com.manager.app_giay.model.ThongKeModel;
import com.manager.app_giay.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiBanHang {
    @GET("getLoaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    @GET("thongke.php")
    Observable<ThongKeModel> getThongKe();

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

    @POST("xoa.php")
    @FormUrlEncoded
    Observable<MessageModel> xoaSanPham(
            @Field("id") int id
    );

    @POST("insertsp.php")
    @FormUrlEncoded
    Observable<MessageModel> insertSp(
            @Field("title") String title,
            @Field("price") String price,
            @Field("thumbnail") String thumbnail,
            @Field("description") String description,
            @Field("category_id") int category_id
    );
    @POST("updatesp.php")
    @FormUrlEncoded
    Observable<MessageModel> updateSp(
            @Field("title") String title,
            @Field("price") String price,
            @Field("thumbnail") String thumbnail,
            @Field("description") String description,
            @Field("category_id") int category_id,
            @Field("id") int id
    );

    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("updateorder.php")
    @FormUrlEncoded
    Observable<MessageModel> updateOrder(
            @Field("id") int id,
            @Field("status") int status
    );
    @POST("gettoken.php")
    @FormUrlEncoded
    Observable<UserModel> gettoken(
            @Field("role") int role,
            @Field("user_id") int user_id
    );

    @Multipart
    @POST("upload.php")
    Call<MessageModel> uploadFile(@Part MultipartBody.Part file);
}
