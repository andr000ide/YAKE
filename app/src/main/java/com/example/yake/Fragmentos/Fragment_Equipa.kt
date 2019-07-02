package com.example.yake.Fragmentos

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yake.EquipaAdapter
import com.example.yake.Models.MembroEquipa
import com.example.yake.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_equipa.view.*


class Fragment_Equipa : androidx.fragment.app.Fragment() {

    val membrosArr : ArrayList<MembroEquipa> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_equipa, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        membrosArr.add(MembroEquipa(nome = "Ricardo Campos", linkedin = "https://www.linkedin.com/in/camposricardo/", mail = "ricardo.campos@ipt.pt",  github = "",web = "http://www.ccc.ipt.pt/~ricardo/",imagemUrl = R.drawable.ricardo, cargo = getString(R.string.cargo_resea_prof)))
        membrosArr.add(MembroEquipa(nome = "Arian Pasquali", linkedin = "https://www.linkedin.com/in/arianpasquali/", mail = "arrp@inesctec.pt",   github = "https://github.com/arianpasquali",web = "",imagemUrl = R.drawable.arian, cargo = getString(R.string.cargo_soft_resea)))
        membrosArr.add(MembroEquipa(nome = "Vitor Mangaravite", linkedin = "https://www.linkedin.com/in/v%C3%ADtor-mangaravite-81998222/", mail = "vima@inesctec.pt",   github = "https://github.com/vitordouzi",web = "",imagemUrl = R.drawable.vitor, cargo = getString(R.string.cargo_soft_resea)))
        membrosArr.add(MembroEquipa(nome = "Alípio Jorge", linkedin = "https://www.linkedin.com/in/al%C3%ADpio-jorge-29085813/", mail = "amjorge@fc.up.pt",   github = "",web = "http://www.dcc.fc.up.pt/~amjorge/",imagemUrl = R.drawable.alipio, cargo = getString(R.string.cargo_resea_prof)))
        membrosArr.add(MembroEquipa(nome = "Célia Nunes", linkedin = "https://www.linkedin.com/in/c%C3%A9lia-nunes-7523a968/", mail = "celian@ubi.pt",   github = "",web = "http://www.mat.ubi.pt/~celia/",imagemUrl = R.drawable.celia, cargo = getString(R.string.cargo_commuticaions)))
        membrosArr.add(MembroEquipa(nome = "Adam Jatowt", linkedin = "https://www.linkedin.com/in/adam-jatowt-a1869b4/", mail = "adam@dl.kuis.kyoti-u.ac.jp",   github = "",web = "http://www.dl.kuis.kyoto-u.ac.jp/~adam/",imagemUrl = R.drawable.adam, cargo = getString(R.string.cargo_resea_prof)))

        view.rec_view.setNestedScrollingEnabled(false)
        view.rec_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = EquipaAdapter(membrosArr, context!!)
        }


        Picasso.get().load(R.drawable.joao).into(view.image)
        Picasso.get().load(R.drawable.simao).into(view.image2)

        Picasso.get().load(R.drawable.img_github).into(view.img_github_joao)
        Picasso.get().load(R.drawable.linkdin_image).into(view.img_linkedin_joao)
        Picasso.get().load(R.drawable.mail_image).into(view.img_mail_joao)

        Picasso.get().load(R.drawable.img_github).into(view.img_github2_simao)
        Picasso.get().load(R.drawable.linkdin_image).into(view.img_linkedin2_simao)
        Picasso.get().load(R.drawable.mail_image).into(view.img_mail2_simao)

        view.linearlayout.isNestedScrollingEnabled=false


        view.rec_view.isNestedScrollingEnabled = false
        view.rec_view.isFocusable=false

    }
}