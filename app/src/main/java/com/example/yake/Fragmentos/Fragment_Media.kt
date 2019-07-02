package com.example.yake.Fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.MediaAdapter
import com.example.yake.Models.Media
import com.example.yake.R
import kotlinx.android.synthetic.main.fragment_media.view.*


class Fragment_Media : androidx.fragment.app.Fragment() {
    private lateinit var langHelper: LangHelper
    val mediaArr: ArrayList<Media> = ArrayList()
    val mediaArr2: ArrayList<Media> = ArrayList()

    val mediaArrEn: ArrayList<Media> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_media, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        langHelper = LangHelper(activity!!.applicationContext)

        if (langHelper.getLanguageSaved().equals("en")) {
            mediaArr.add(
                Media(
                    organizacao = "Inesctec",
                    titulo = getString(R.string.media_en_titulo1),
                    texto = "The paper entitled “A Text Feature Based Automatic Keyword Extraction Method for Single Documents” by Ricardo Campos, Vitor Mangaravite, Arian Pasquali and Alípio M. Jorge, researchers from INESC TEC’s Artificial Intelligence and Decision Support Laboratory (LIAAD) and by Célia Nunes from the University of Beira Interior (UBI), and by Adam Jatowt from Kyoto University, won the ECIR 2018 Best Short Paper Award, promoted by the 40th European Conference on Information Retrieval.",
                    link = "https://www.inesctec.pt/en/news/inesc-tec-team-wins-another-best-paper-award#about",
                    img = R.drawable.img_github
                )
            )

        } else {
            mediaArr.add(
                Media(
                    organizacao = "Inesctec",
                    titulo = getString(R.string.media_pt_titulo1),
                    texto = getString(R.string.media_inesctec_texto),
                    link = "http://bip.inesctec.pt/192/noticia-pd02.html",
                    img = R.drawable.img_github
                )
            )
            mediaArr.add(
                Media(
                    organizacao = "UBI",
                    titulo = getString(R.string.media_pt_titulo2),
                    texto = "Prémio de Best Short Paper Award atribuído em evento de recuperação de informação (Information Retrieval), que decorreu em França.",
                    link = "https://www.ubi.pt/Noticia/6250",
                    img = R.drawable.img_github
                )
            )


        }


        //mediaArr2.add(Media("","","",""))
        view.rec_view.setNestedScrollingEnabled(false)
        view.rec_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = MediaAdapter(mediaArr, context!!)
        }

        /*view.linearlayout.isNestedScrollingEnabled=false


        view.rec_view.isNestedScrollingEnabled = false
        view.rec_view.isFocusable=false*/
    }
}
