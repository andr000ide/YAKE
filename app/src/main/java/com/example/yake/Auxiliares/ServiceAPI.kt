package com.example.yake.Auxiliares

import com.example.yake.Models.Example_Yake
import com.example.yake.Models.Wordcloud
import retrofit2.Call
import retrofit2.http.*


interface ServiceAPI {
    @GET("extract_keywords?")
    fun search_algo(@Query("width") width : String, @Query("height") height : String, @Query("json") json : String) : Call<Example_Yake>

    @GET("extract_keywords_by_url?")
    fun search_keywords_url(@Query("url") url : String, @Query("max_ngram_size") max_ngram_size : Int, @Query("number_of_keywords") number_of_keywords : Int) : Call<Example_Yake>

    @GET("?")
    fun search_cloud(@Query("width") width : String,@Query("height") height : String,@Query("json") json : String) : Call<Wordcloud>


    @FormUrlEncoded
    @POST("extract_keywords?")
    fun search_keywords_texto(@Field("content") content : String,@Query("title") title : String, @Query("max_ngram_size") max_ngram_size : Int, @Query("number_of_keywords") number_of_keywords : Int) : Call<Example_Yake>
}