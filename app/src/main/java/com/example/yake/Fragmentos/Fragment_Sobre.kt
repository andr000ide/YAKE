package com.example.yake.Fragmentos


import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.Auxiliares.SobreAdapter
import com.example.yake.EquipaAdapter
import com.example.yake.Models.sobreTopico
import com.example.yake.R
import kotlinx.android.synthetic.main.fragment_sobre.view.*


class FragmentSobre : androidx.fragment.app.Fragment(){
    private lateinit var langHelper: LangHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sobre, container, false)
        val topicoArr : ArrayList<sobreTopico> = ArrayList()
        val topicoArr2 : ArrayList<sobreTopico> = ArrayList()

        topicoArr2.add(sobreTopico(titulo = "dedede", texto = "dede", url = "dede"))


        //topicoArr.add(sobreTopico(titulo = getString(R.string.sobre01), texto = "O Conta-me Histórias é um projeto científico, criado no âmbito dos Prémios Arquivo.pt 2018, que permite aos utilizadores a possibilidade de criarem automaticamente uma sumarização temporal das notícias preservadas pelo Arquivo.pt (http://arquivo.pt). Numa era marcada pela pós-verdade e pelas fake news, o Conta-me Histórias é um importante contributo para uma democracia mais transparente. Esta aplicação permite a qualquer cidadão um acesso livre e democrático a informação contextualizada, assente em fatos e tendencialmente livre de filtros, fazendo uso de diversas fontes. Jornalistas, estudantes e investigadores de diversas áreas, tais como a sociologia, a ciência política, as ciências da comunicaçãos, a história e outras humanidades, encontram aqui uma ferramenta poderosa para o seu trabalho.", url = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DrX1PjpyNHw\" frameborder=\"0\" allowfullscreen></iframe>"))

        topicoArr.add(sobreTopico(titulo = "YAKE!", texto = getString(R.string.sobre_yake), url = ""))
        topicoArr.add(sobreTopico(titulo = "Software", texto = getString(R.string.sobre_software), url = ""))
        topicoArr.add(sobreTopico(titulo = getString(R.string.sobreContributions_title), texto = getString(R.string.sobre_contributions), url = ""))

        topicoArr.add(sobreTopico(titulo = getString(R.string.sobreHowdoesitwork_title), texto = getString(R.string.sobre_howdoesitwork), url = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/b4_ZBr2ijVw\" frameborder=\"0\" allowfullscreen></iframe>"))
        //topicoArr.add(sobreTopico(titulo = getString(R.string.origem_nome), texto = "O nome ‘Conta-me Histórias’ é uma homenagem aos Xutos & Pontapés. Tal como na música do famoso grupo de rock português, também o Conta-me Histórias tem por objetivo contar histórias ao utilizador de algo que este não viu ou do que não se recorda. ", url =  ""))
        topicoArr.add(sobreTopico(titulo = getString(R.string.sobreAwards_title), texto = getString(R.string.sobre_awards), url = ""))
        topicoArr.add(sobreTopico(titulo = getString(R.string.sobreReferences_title), texto = getString(R.string.sobreReferenciasT01) +"\n"+
                getString(R.string.sobreReferenciasT02) + "\n" +
                getString(R.string.sobreReferenciasT03), url = ""))
        // Creates a vertical Layout Manager


        view.rec_view.setNestedScrollingEnabled(false)
        view.rec_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = SobreAdapter(topicoArr, context)
        }

        return view
    }
}