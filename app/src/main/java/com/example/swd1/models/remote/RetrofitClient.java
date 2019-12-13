package com.example.swd1.models.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.swd1.models.remote.RetrofitConstants.BASE_URL;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(final String token) {
        if (retrofit == null) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor()           {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", " Bearer " + token)
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();


            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
