package com.example.yake


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
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
import com.example.yake.Models.Media
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.media_item.view.*

class MediaAdapter(val mediaEle: ArrayList<Media>, val context: Context) : RecyclerView.Adapter<ViewHolderFour>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderFour {
        val view = ViewHolderFour(LayoutInflater.from(context).inflate(R.layout.media_item, p0, false))

        return view
    }

    override fun getItemCount(): Int {
        return mediaEle.size
    }

    override fun onBindViewHolder(p0: ViewHolderFour, p1: Int) {
        //p0.mediaTitulo.text = mediaEle.get(p1).titulo
        setTextViewHTML(p0.mediaTitulo,mediaEle.get(p1).titulo)
        setTextViewHTML(p0.mediaTexto,mediaEle.get(p1).texto)
        //p0.mediaTexto.text = mediaEle.get(p1).texto
        p0.mediaOrganizacao.text = mediaEle.get(p1).organizacao
        p0.mediaLink.text = mediaEle.get(p1).link
        //p0.mediaImg.image = mediaEle.get(p1).img
        Picasso.get().load(mediaEle.get(p1).img).into(p0.mediaImg)

        p0.mediaTexto.movementMethod = LinkMovementMethod.getInstance()

        p0.bind(mediaEle.get(p1))
        p0.mediaOrganizacao.setOnClickListener() {
            val expanded: Boolean = mediaEle.get(p1).isExpanded()
            if (expanded) {
                mediaEle.get(p1).setExpanded(!expanded)
                notifyItemChanged(p1)
            } else {
                for ((index, item) in mediaEle.withIndex()) {
                    if (item.isExpanded()) {
                        item.setExpanded(false)
                        notifyItemChanged(index)
                    }
                }
                mediaEle.get(p1).setExpanded(!expanded)
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

class ViewHolderFour(view: View) : RecyclerView.ViewHolder(view) {
    val mediaTitulo = view.titulo
    val mediaOrganizacao = view.organizacao
    val mediaTexto = view.texto
    val mediaLink = view.link
    val sub_item = view.sub_item
    val mediaImg = view.media_img

    fun bind(mediaEl: Media) {
        val expanded = mediaEl.isExpanded()
        sub_item.setVisibility(if (expanded) View.VISIBLE else View.GONE)
    }
}

