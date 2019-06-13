package com.travel.svago.Remote;

/**
 * Created by Omnia on 11/11/2018.
 */


public class ApiUtlis {

    public static final String Base_Url="https://turvist.com/api/";



    public static UserService getUserService()
    {
        return RetrofitClient.getClient(Base_Url).create(UserService.class);
    }

    public static UserService_POST getUserServices_Post()
    {
        return RetrofitClient.getClient(Base_Url).create(UserService_POST.class);
    }


}
