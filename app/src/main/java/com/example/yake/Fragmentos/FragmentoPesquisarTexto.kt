package com.example.yake.Fragmentos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.R
import com.example.yake.SecondActivity
import kotlinx.android.synthetic.main.pesquisar_texto.*
import kotlinx.android.synthetic.main.pesquisar_texto.view.*
import kotlinx.android.synthetic.main.pesquisar_texto.view.info
import kotlinx.android.synthetic.main.pesquisar_texto.view.seekBar
import kotlinx.android.synthetic.main.pesquisar_url.view.*
import kotlinx.android.synthetic.main.pesquisar_url.view.searchbar


class FragmentoPesquisarTexto : androidx.fragment.app.Fragment() {
    private lateinit var langHelper: LangHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar_texto, container, false)

        var aux = "3"
        var textoparam = arguments?.getString("texto")
        if(textoparam!=null){
            view.titulo_texto.visibility = View.GONE
            view.searchbar.setText(textoparam.toString())
            view.info2_texto.visibility = View.GONE
            view.constraint_seekbar_texto.visibility = View.GONE
            val step = 1
            val max = 10
            val min = 1
            view.seekBar2.setMax((max - min) / step)


            view.seekBar2.setOnSeekBarChangeListener(
                object : OnSeekBarChangeListener {
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
                                view.info3.text = value.toString() + "-term"
                            }
                            else{
                                view.info3.text = value.toString() + "-terms"
                            }

                        }
                        else{
                            if(value == 1){
                                view.info3.text = value.toString() + "-termo"
                            }
                            else{
                                view.info3.text = value.toString() + "-termos"
                            }
                        }

                    }
                }
            )

        }
        else{
            view.titulo_texto.visibility = View.VISIBLE
            view.info4.visibility = View.GONE
            view.constraint_seekbar2.visibility = View.GONE
            val step = 1
            val max = 10
            val min = 1
            view.seekBar.setMax((max - min) / step)


            view.seekBar.setOnSeekBarChangeListener(
                object : OnSeekBarChangeListener {
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
        }



        view.btn_pesquisar.setOnClickListener {
            var content = view.searchbar.text.toString()




            content.let {
                if(content.isNotEmpty()){
                    view.hideKeyboard()



                    val kotlinFragment = FragmentTexto.newInstance(content,"",aux)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
            }
        }

        langHelper = LangHelper(activity!!.applicationContext)




        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    companion object {
        fun newInstance(texto: String): FragmentoPesquisarTexto {
            val args = Bundle()
            args.putString("texto",texto)
            val fragment = FragmentoPesquisarTexto()
            fragment.arguments = args
            return fragment
        }
    }


}