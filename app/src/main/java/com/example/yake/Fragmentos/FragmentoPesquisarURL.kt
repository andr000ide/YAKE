package com.example.yake.Fragmentos

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.yake.R
import com.example.yake.SecondActivity
import kotlinx.android.synthetic.main.pesquisar_url.view.*


class FragmentoPesquisarURL : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar_url, container, false)

        view.constraintclick.setOnFocusChangeListener { _, _ ->
            run {
                view.constraintclick.hideKeyboard()
                view.imagePesquisa.setImageResource(R.drawable.ic_search_blue_24dp)
            }
        }


        view.constraintclick.setOnClickListener {
            // view.constraintclick.hideKeyboard()
        }

        view.searchbar.setOnFocusChangeListener { _, _ ->  view.imagePesquisa.setImageResource(R.drawable.ic_search_black_24dp) }


        view.imagePesquisa.setOnClickListener {
            var aux = view.searchbar.text.toString()

            val result = aux

            //var aux2 = listItemsTxt.get(view.spinner1.selectedItemPosition)
            view.imagePesquisa.hideKeyboard()

            val kotlinFragment = FragmentUrl.newInstance(result)

            (activity as SecondActivity).replaceFragment(kotlinFragment)
        }
        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}