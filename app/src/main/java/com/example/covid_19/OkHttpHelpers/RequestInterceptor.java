package com.example.covid_19.OkHttpHelpers;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.covid_19.Util.Constanst.API_KEY;
import static com.example.covid_19.Util.Constanst.HOST;

public class RequestInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .addHeader("x-rapidapi-host",HOST)
                .addHeader("x-rapidapi-key",API_KEY);


        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
