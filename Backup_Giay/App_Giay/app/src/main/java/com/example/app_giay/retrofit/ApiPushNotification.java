package com.example.app_giay.retrofit;

import com.example.app_giay.model.NotiResponse;
import com.example.app_giay.model.NotiSendData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPushNotification {
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key=AAAAbUH3uGI:APA91bHM6cNSpx5aTrV4jjhP56NC0S_JpdjL06i0kaXo2b5pXoDSrGTiYacGNT0uddcUM-cNbE9M8yPXXkzzwOAJLU_hX5TqDuXbUycju4B9jRNAYFJCQD4jC-7J9IFGrBpd0eTdoC_l"
            }
    )
    @POST("fcm/send")
    Observable<NotiResponse> sendNotification(@Body NotiSendData data);
}
