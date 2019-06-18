package com.example.yake.Fragmentos


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.Layout
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.yake.R
import kotlinx.android.synthetic.main.fragment_fragment_agradecimentos.view.*


class FragmentAgradecimentos() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_agradecimentos, container, false)

        view.ola1.setOnClickListener {
            var link: String = "http://portal2.ipt.pt/"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }
        view.ola2.setOnClickListener {
            var link: String = "https://sigarra.up.pt/fcup/pt/web_page.inicial"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }
        view.ola3.setOnClickListener {
            var link: String = "https://www.kyoto-u.ac.jp/en/"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }
        view.ola4.setOnClickListener {
            var link: String = getString(R.string.liaad_url)
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }
        view.ola5.setOnClickListener {
            var link: String = "http://foureyes.inesctec.pt/"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

        // view.texto_outros.setText(Html.fromHtml(getString(R.string.outros_texto),3))
        view.texto_outros.movementMethod = LinkMovementMethod.getInstance()

        return view
    }




}
