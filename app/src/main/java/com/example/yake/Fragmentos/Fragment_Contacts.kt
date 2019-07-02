package com.example.yake.Fragmentos


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.R
import kotlinx.android.synthetic.main.fragment_contacts.view.*

import org.jetbrains.anko.toast


class Fragment_Contacts : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_contacts, container, false)

        view.copiar_mail.setOnClickListener {
            var mail = "ricardo.campos@ipt.pt"
            val clipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copy text", mail)
            clipboard.primaryClip = clip
            context!!.toast(mail +"\n"+ getString(R.string.mail_foi_copiado))
        }

        return view
    }
}
