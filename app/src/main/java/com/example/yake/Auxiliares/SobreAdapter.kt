package com.example.yake.Auxiliares


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.text.Html
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yake.Models.sobreTopico
import com.example.yake.R
import kotlinx.android.synthetic.main.item_sobreadapter.view.*


class SobreAdapter(val topicos : ArrayList<sobreTopico>, val context: Context) : RecyclerView.Adapter<ViewHoldertwo>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHoldertwo {
        val view = ViewHoldertwo(LayoutInflater.from(context).inflate(R.layout.item_sobreadapter, p0, false))

        return view
    }

    override fun getItemCount(): Int {
        return topicos.size
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onBindViewHolder(p0: ViewHoldertwo, p1: Int) {
        val sobreTopico: sobreTopico = topicos[p1]

        p0.topicoTitulo?.text = topicos.get(p1).titulo

        val aux = topicos.get(p1).texto
        setTextViewHTML(p0.topicoTexto,aux)
        //p0.video_view.settings.javaScriptEnabled = true

//        if (sobreTopico.url != ""){
//            p0.video_view.visibility = View.VISIBLE
//            p0.video_view.loadData(sobreTopico.url, "text/html","utf-8")
//        }else {
//            p0.video_view.visibility = View.GONE
//        }

        p0.bind(sobreTopico)
        p0.topicoTitulo.setOnClickListener(){
            val expanded: Boolean = sobreTopico.isExpanded()
            if(expanded){
                sobreTopico.setExpanded(!expanded)
                notifyItemChanged(p1)
            }
            else{
                for((index, item) in topicos.withIndex()){
                    if(item.isExpanded()){
                        item.setExpanded(false)
                        notifyItemChanged(index)
                    }
                }
                sobreTopico.setExpanded(!expanded)
                notifyItemChanged(p1)
            }
            //sobreTopico.setExpanded(!expanded)
            //notifyItemChanged(p1)
        }
    }

    protected fun setTextViewHTML(text: TextView, html: String) {
        val sequence = Html.fromHtml(html)
        val strBuilder = SpannableStringBuilder(sequence)
        val urls = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
        for (span in urls) {
            makeLinkClickable(strBuilder, span)
        }
        text.text = strBuilder
        text.movementMethod = LinkMovementMethod.getInstance()
    }

    protected fun makeLinkClickable(strBuilder: SpannableStringBuilder, span: URLSpan) {
        val start = strBuilder.getSpanStart(span)
        val end = strBuilder.getSpanEnd(span)
        val flags = strBuilder.getSpanFlags(span)
        val clickable = object : ClickableSpan() {
            override fun onClick(view: View) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(span.url))
                ContextCompat.startActivity(context!!, intent, null)
            }
        }
        strBuilder.setSpan(clickable, start, end, flags)
        strBuilder.removeSpan(span)
    }
}



class ViewHoldertwo (view: View) : RecyclerView.ViewHolder(view) {

    val topicoTitulo = view.topico_titulo
    val topicoTexto = view.topico_texto
    val sub_item = view.sub_item
    //val video_view = view.video_view
    fun bind(sobreTopico: sobreTopico) {
        val expanded = sobreTopico.isExpanded()
        sub_item.setVisibility(if (expanded) View.VISIBLE else View.GONE)
    }

}

