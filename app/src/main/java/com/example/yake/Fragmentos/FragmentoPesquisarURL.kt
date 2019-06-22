package com.example.yake.Fragmentos

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import com.example.yake.R
import com.example.yake.SecondActivity
import kotlinx.android.synthetic.main.pesquisar_texto.view.*
import kotlinx.android.synthetic.main.pesquisar_url.view.*
import kotlinx.android.synthetic.main.pesquisar_url.view.constraintclick
import kotlinx.android.synthetic.main.pesquisar_url.view.info
import kotlinx.android.synthetic.main.pesquisar_url.view.searchbar
import kotlinx.android.synthetic.main.pesquisar_url.view.seekBar


class FragmentoPesquisarURL : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar_url, container, false)

        var aux = "1"
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
            var result = view.searchbar.text.toString()


            //var aux2 = listItemsTxt.get(view.spinner1.selectedItemPosition)
            view.imagePesquisa.hideKeyboard()

            val kotlinFragment = FragmentUrl.newInstance(result,aux)

            (activity as SecondActivity).replaceFragment(kotlinFragment)
        }


        val step = 1
        val max = 10
        val min = 1
        view.seekBar.setMax((max - min) / step)


        view.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {}

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    // Ex :
                    // And finally when you want to retrieve the value in the range you
                    // wanted in the first place -> [3-5]
                    //
                    // if progress = 13 -> value = 3 + (13 * 0.1) = 4.3
                    val value = (min + progress * step).toDouble().toInt()
                    aux = value.toString()
                    view.info.text = value.toString() + "-gram"

                }
            }
        )

        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}