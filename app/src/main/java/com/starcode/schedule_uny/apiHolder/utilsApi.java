package com.starcode.schedule_uny.apiHolder;

import com.starcode.schedule_uny.BuildConfig;

public class utilsApi {

    public static final String BASE_URL_API= BuildConfig.SERVER_URL;
    public  static baseApiService getApiServices(){
        return retrofitClient.getClient(BASE_URL_API).create(baseApiService.class);
    }
}
