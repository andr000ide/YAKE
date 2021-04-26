package com.example.yake.Fragmentos

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.Models.Example_Yake
import com.example.yake.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragmento_annotatedtext.view.*


class FragmentoAnnotatedText : androidx.fragment.app.Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragmento_annotatedtext, container, false)


        var jsonarray = arguments?.getString("jsonYake")
        var texto = arguments?.getString("texto")
        var titulo = arguments?.getString("titulo")


        if (titulo!!.isEmpty()) {
            view.titulo.visibility = View.GONE
        } else {
            view.titulo.text = titulo
            view.titulo.visibility = View.VISIBLE
        }


        val gson = Gson()
        val turnsType = object : TypeToken<Example_Yake>() {}.type
        var testModel = gson.fromJson<Example_Yake>(jsonarray, turnsType)

        println(testModel)

        var listaPalavras = mutableListOf<String>()
        for (item in testModel.keywords) {
            listaPalavras.add(item.ngram)
        }

        if (titulo.isNotEmpty()) {
            val mainTitulo = view.titulo.text.toString().toLowerCase()
            val spannableString2 = SpannableString(titulo)
            var i = 0

            for (item in listaPalavras) {
                val mutableList = mutableListOf<Int>()
                var index = 0;
                while (index != -1) {
                    index = mainTitulo!!.indexOf(item, index);
                    if (index != -1) {
                        mutableList.add(index);
                        index++;
                    }
                }

                if (mainTitulo!!.contains(item)) {
                    for (item2 in mutableList) {
                        val endIndex = item2 + item.length
                        spannableString2.setSpan(
                            BackgroundColorSpan(Color.parseColor("#ffffff")), item2, endIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        spannableString2.setSpan(
                            ForegroundColorSpan(Color.parseColor("#000000")), item2, endIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }

                }
            }
            view.titulo.setText(spannableString2)

        }


        val mainString = texto!!.toLowerCase()
        //val subString = "Soon"


        val spannableString = SpannableString(texto)

        for (item in listaPalavras) {
            val mutableList = mutableListOf<Int>()
            var index = 0;
            while (index != -1) {
                index = spannableString!!.indexOf(item, index);
                if (index != -1) {
                    mutableList.add(index);
                    index++;
                }
            }




            if (spannableString!!.contains(item)) {
                for (item2 in mutableList) {
                    val endIndex = item2 + item.length
                    spannableString.setSpan(
                        BackgroundColorSpan(Color.parseColor("#ffffff")), item2, endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableString.setSpan(
                        ForegroundColorSpan(Color.parseColor("#000000")), item2, endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

            }


        }
        view.texto_anotado.setText(spannableString)


        //view.texto_anotado.text = texto

        //val activity = activity as SecondActivity


        return view
    }

    companion object {
        fun newInstance(jsonString: String, texto: String, titulo: String): FragmentoAnnotatedText {
            val args = Bundle()
            args.putString("jsonYake", jsonString)
            args.putString("texto", texto)
            args.putString("titulo", titulo)
            val fragment = FragmentoAnnotatedText()
            fragment.arguments = args
            return fragment
        }
    }
}
