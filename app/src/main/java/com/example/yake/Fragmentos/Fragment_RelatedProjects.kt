package com.example.yake.Fragmentos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.yake.R
import kotlinx.android.synthetic.main.fragment_relatedprojects.*


class Fragment_RelatedProjects : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_relatedprojects, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_pketoolkit.setOnClickListener {
            var link: String = "https://github.com/boudinfl/pke"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

        btn_kaggle.setOnClickListener {
            var link: String = "https://www.youtube.com/watch?v=6TBvZmg7AsA"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

        btn_contamehistorias.setOnClickListener {
            var link: String = "http://contamehistorias.pt/arquivopt/"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

        btn_dockerizedyake.setOnClickListener {
            var link: String = "https://github.com/LIAAD/yake#option-1-yake-as-a-cli-utility-inside-a-docker-container"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

        btn_dendro.setOnClickListener {
            var link: String = "http://dendro-stg.inesctec.pt/"
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            ContextCompat.startActivity(activity!!.applicationContext, intent, null)
        }

    }
}
