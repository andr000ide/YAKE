package com.example.yake.Fragmentos


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.KeywordAdapter
import com.example.yake.Models.Example_Yake
import com.example.yake.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.layout_keywords.view.*


class FragmentoKeywords : androidx.fragment.app.Fragment() {
    private lateinit var langHelper: LangHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.layout_keywords, container, false)
        val jsonString = arguments?.getString("jsonString")
        val gson = Gson()
        val turnsType = object : TypeToken<Example_Yake>() {}.type
        var testModel = gson.fromJson<Example_Yake>(jsonString, turnsType)

        view.rec_view.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            adapter = KeywordAdapter(testModel.keywords, context!!)
        }


        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    companion object {
        fun newInstance(texto: String): FragmentoKeywords {
            val args = Bundle()
            args.putString("jsonString", texto)
            val fragment = FragmentoKeywords()
            fragment.arguments = args
            return fragment
        }
    }


}