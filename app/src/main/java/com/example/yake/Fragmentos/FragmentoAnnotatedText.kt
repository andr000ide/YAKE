package com.example.yake.Fragmentos

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.Auxiliares.RetrofitWordCloudInstance
import com.example.yake.Auxiliares.ServiceAPI
import com.example.yake.Models.Wordcloud
import com.example.yake.R
import com.example.yake.SecondActivity
import kotlinx.android.synthetic.main.fragment_wordcloud.view.*
import kotlinx.android.synthetic.main.fragmento_annotatedtext.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class FragmentoAnnotatedText : androidx.fragment.app.Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragmento_annotatedtext, container, false)


        var jsonarray = arguments?.getString("jsonYake")
        var texto = arguments?.getString("texto")

        view.texto_anotado.text = texto

        //val activity = activity as SecondActivity


        return view
    }

    companion object {
        fun newInstance(jsonString: String,texto : String): FragmentoAnnotatedText {
            val args = Bundle()
            args.putString("jsonYake", jsonString)
            args.putString("texto",texto)
            val fragment = FragmentoAnnotatedText()
            fragment.arguments = args
            return fragment
        }
    }
}
