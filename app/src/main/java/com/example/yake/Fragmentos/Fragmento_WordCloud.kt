package com.example.yake.Fragmentos


import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.Auxiliares.RetrofitWordCloudInstance
import com.example.yake.Auxiliares.ServiceAPI
import com.example.yake.Models.Wordcloud
import com.example.yake.R
import com.example.yake.SecondActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_wordcloud.*
import kotlinx.android.synthetic.main.fragment_wordcloud.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class Fragmento_WordCloud : androidx.fragment.app.Fragment() {

    lateinit var call: Call<Wordcloud>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_wordcloud, container, false)
        view.textowordcloud.visibility = View.VISIBLE
        view.wordCloud.visibility = View.INVISIBLE

        var jsonarray = arguments?.getString("jsonYake")




        thread(start = true) {

                    val service3 = RetrofitWordCloudInstance.retrofitInstance?.create(ServiceAPI::class.java)

                    var width = resources.displayMetrics.widthPixels
                    var height = resources.displayMetrics.heightPixels
                    height = height - 300
                    jsonarray?.let {
                        call = service3!!.search_cloud(width.toString(), height.toString(),it)
                        //println(call3.toString())
                        call!!.enqueue(object : Callback<Wordcloud> {


                            override fun onResponse(call: Call<Wordcloud>, response: Response<Wordcloud>) {
                                val outronome = response.body()

                                if(response.message().equals("Service Unavailable")){
                                    view.textowordcloud.text = getString(R.string.key_error_wordcloud)
                                }
                                else{
                                    val decodedstring =
                                        android.util.Base64.decode(outronome?.wordcloudb64, android.util.Base64.DEFAULT)
                                    val decodedByte = BitmapFactory.decodeByteArray(decodedstring, 0, decodedstring.size)
                                    val atividade = activity as SecondActivity
                                    //atividade.imagemtestar.setImageBitmap(decodedByte)
                                    view.wordCloud.setImageBitmap(decodedByte)
                                    view.textowordcloud.visibility = View.GONE
                                    view.wordCloud.visibility = View.VISIBLE
                                }
                                //val decodedstring = Base64.getDecoder().decode(outronome?.wordcloudb64)


                            }

                            override fun onFailure(call: Call<Wordcloud>, t: Throwable) {
                                view.textowordcloud.text = "Error a carregar a wordcloud"
                            }
                        })
                    }

                }



        //val activity = activity as SecondActivity


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = activity as SecondActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::call.isInitialized) {

            call.cancel()
        }
    }

    companion object {
        fun newInstance(jsonString: String): Fragmento_WordCloud {
            val args = Bundle()
            args.putString("jsonYake", jsonString)
            val fragment = Fragmento_WordCloud()
            fragment.arguments = args
            return fragment
        }
    }
}
