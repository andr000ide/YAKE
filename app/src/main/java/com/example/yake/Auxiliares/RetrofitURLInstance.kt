package com.example.yake.Auxiliares
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitURLInstance {
    private var retrofit: Retrofit? = null


    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(300, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    val retrofitInstance: Retrofit?
        get() {
            if(retrofit ==null){
                retrofit = retrofit2.Retrofit.Builder()
                    //.baseUrl("https://conta-https.herokuapp.com/")
                    .baseUrl("https://text-url.herokuapp.com/")
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}