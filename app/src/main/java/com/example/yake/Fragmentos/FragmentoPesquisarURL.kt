package com.example.yake.Fragmentos

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.SeekBar
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.R
import com.example.yake.SecondActivity
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.pesquisar_texto.view.*
import kotlinx.android.synthetic.main.pesquisar_url.view.*
import kotlinx.android.synthetic.main.pesquisar_url.view.constraintclick
import kotlinx.android.synthetic.main.pesquisar_url.view.info
import kotlinx.android.synthetic.main.pesquisar_url.view.searchbar
import kotlinx.android.synthetic.main.pesquisar_url.view.seekBar


class FragmentoPesquisarURL : androidx.fragment.app.Fragment() {
    private lateinit var langHelper: LangHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar_url, container, false)

        var aux = "3"
        view.constraintclick.setOnFocusChangeListener { _, _ ->
            run {
                view.constraintclick.hideKeyboard()
            }
        }


        view.constraintclick.setOnClickListener {
            // view.constraintclick.hideKeyboard()
        }



        view.imagePesquisa.setOnClickListener {
            var result = view.searchbar.text.toString()


            if(result.isNotEmpty()){
                var verificador = 0
                if(result.contains(" ")){
                    withEspacos()
                    verificador=1

                }
                else if(result.startsWith("https://www.") || result.startsWith("http://www.")){

                }
                else if(result.startsWith("http://") || result.startsWith("https://")){
                    if(result.startsWith("http://")){
                        result = result.replace("http://","http://www.")
                    }
                    else{
                        result = result.replace("https://","https://www.")
                    }
                }
                else if(result.startsWith("www.")){
                    result = result.replace("www.","http://www.")
                }
                else{
                    result = "http://www."+result
                }


                if(verificador==1){

                }
                else{
                    //var aux2 = listItemsTxt.get(view.spinner1.selectedItemPosition)
                    view.imagePesquisa.hideKeyboard()

                    val kotlinFragment = FragmentUrl.newInstance(result,aux)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }


            }
            else{
                withButtonCentered()
            }
        }


        langHelper = LangHelper(activity!!.applicationContext)


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
                    if (langHelper.getLanguageSaved().equals("en")) {
                        if(value == 1){
                            view.info.text = value.toString() + "-term"
                        }
                        else{
                            view.info.text = value.toString() + "-terms"
                        }

                    }
                    else{
                        if(value == 1){
                            view.info.text = value.toString() + "-termo"
                        }
                        else{
                            view.info.text = value.toString() + "-termos"
                        }
                    }



                }
            }
        )

        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    fun withButtonCentered() {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_error)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.texto1.text = getString(R.string.key_error_texto1_vazio)
        dialog.texto2.text = getString(R.string.key_error_texto2_vazio)


        val button = dialog.findViewById(R.id.buttonOk) as Button

        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun withEspacos() {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_error)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.texto1.text = getString(R.string.key_error_texto1_espaços)
        dialog.texto2.text = getString(R.string.key_error_texto2_espaços)


        val button = dialog.findViewById(R.id.buttonOk) as Button

        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


}