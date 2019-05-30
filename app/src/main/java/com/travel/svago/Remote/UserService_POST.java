package com.travel.svago.Remote;


import com.travel.svago.Models.AffiliateResponse.AffiliateResponse;
import com.travel.svago.Models.CarDetailsResponses.CarDetailsResponse;
import com.travel.svago.Models.LoginResponses.AuthResponse;
import com.travel.svago.Models.OrderCarResponses.OrderCarResponse;
import com.travel.svago.Models.ResponseCarsOrder.ResponseCars;
import com.travel.svago.Models.OrderTripResponses.OrderTripResponse;
import com.travel.svago.Models.ResponseGuideOrders.ResponseGuideOrders;
import com.travel.svago.Models.ResponseOffers.ResponseOffers;
import com.travel.svago.Models.ResponseSimple.ResponseSimple;
import com.travel.svago.Models.ResponseSocialLogin.ResponseSocialLogin;
import com.travel.svago.Models.ResponseStatus.ResponseStatus;
import com.travel.svago.Models.ResponseTripOrders.ResponseTripOrders;
import com.travel.svago.Models.SimpleResponse.SimpleResponse;
import com.travel.svago.Models.SvagoResponses.SvagoResponse;
import com.travel.svago.Models.TripDetailsResponses.TripDetailsResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService_POST {

    @Multipart
    @POST("auth/register")
    Call<AuthResponse> Register(
            @Query("first_name") String first_name,
            @Query("last_name") String last_name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("device_token") String device_token,
            @Query("language") String language,
            @Query("country") int country,
            @Query("phone") String phone ,
            @Query("code") String code ,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("auth/profile")
    Call<AuthResponse> EditProfile(
            @Header("Authorization") String Authorization,
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone ,
            @Query("code") String code ,
            @Query("country") Integer countryId ,
            @Query("password") String password,
            @Part MultipartBody.Part part
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/profile")
    Call<AuthResponse> EditProfileWithoutPhoto(
            @Header("Authorization") String Authorization,
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone ,
            @Query("code") String code ,
            @Query("country") Integer countryId ,
            @Query("password") String password
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/profile")
    Call<AuthResponse> getUser(
            @Header("Authorization") String Authorization
    );

    @POST("auth/login")
    Call<AuthResponse> Login(
            @Query("email") String email,
            @Query("password") String password,
            @Query("language") String language,
            @Query("device_token") String device_token
    );

    @POST("auth/reset")
    Call<SimpleResponse> resetPass(
            @Query("email") String email
    );


    @POST("svago/list")
    Call<SvagoResponse> svagoList(
            @Query("currency_id") int currency_id
    );

    @POST("svago/offers")
    Call<ResponseOffers> svagoOffers(
            @Query("currency_id") int currency_id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("cars/{id}")
    Call<CarDetailsResponse> carDetails(
            @Path("id") int carID,
            @Query("currency_id") int currency_id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("trips/{id}")
    Call<TripDetailsResponse> TripDetails(
            @Path("id") int tripID,
            @Query("currency_id") int currency_id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("payments/car")
    Call<OrderCarResponse> orderCar(
            @Header("Authorization") String Authorization,
            @Query("car_id") int car_id,
            @Query("from") String from,
            @Query("to") String to,
            @Query("lat") double lat,
            @Query("lng") double lng
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("payments/trip")
    Call<OrderTripResponse> orderTrip(
            @Header("Authorization") String Authorization,
            @Query("trip_id") int trip_id,
            @Query("tickets") int tickets
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("guides/order")
    Call<ResponseStatus> requestGuide(
            @Header("Authorization") String Authorization,
            @Query("location") String location,
            @Query("lat") Double lat ,
            @Query("lng") Double lng ,
            @Query("from") String from ,
            @Query("to") String to ,
            @Query("languages") String languages ,
            @Query("description") String description ,
            @Query("budget") String budget
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("messages/create")
    Call<ResponseSimple> contactUs(
            @Query("name") String name,
            @Query("email") String email,
            @Query("description") String description
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/orders/cars")
    Call<ResponseCars> callCarsOrder(
            @Header("Authorization") String Authorization,
            @Query("page") int page
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/orders/trips")
    Call<ResponseTripOrders> callTripsOrder(
            @Header("Authorization") String Authorization,
            @Query("page") int page
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/orders/guides")
    Call<ResponseGuideOrders> callGuidesOrder(
            @Header("Authorization") String Authorization,
            @Query("page") int page
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("payments/car/cancel")
    Call<SimpleResponse> cancelCarOrder(
            @Header("Authorization") String Authorization,
            @Query("order_id") String order_id
    );
 @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("payments/trip/cancel")
    Call<SimpleResponse> cancelTripOrder(
            @Header("Authorization") String Authorization,
            @Query("order_id") String order_id
    );

 @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("cars/link")
    Call<AffiliateResponse> getCarLink(
            @Header("Authorization") String Authorization,
            @Query("car_id") int car_id
    );


 @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("trips/link")
    Call<AffiliateResponse> getTripLink(
            @Header("Authorization") String Authorization,
            @Query("car_id") int car_id
    );


 @Headers({ "Content-Type: application/json;charset=UTF-8"})
 @POST("auth/social")
    Call<ResponseSocialLogin> socialLogin(
            @Query("email") String email  ,
            @Query("name") String name  ,
            @Query("driver") String driver  ,
            @Query("id") String id
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/vip-trips/order")
    Call<SimpleResponse> orderVipTrip(
            @Header("Authorization") String Authorization,
            @Query("phone") String phone ,
            @Query("email") String email ,
            @Query("location") String location ,
            @Query("nationality") String nationality ,
            @Query("type") String type ,
            @Query("from") String from ,
            @Query("to") String to ,
            @Query("from_budget") String from_budget ,
            @Query("to_budget") String to_budget ,
            @Query("description") String description
    );

}

