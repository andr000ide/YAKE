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
import com.example.yake.Models.Keyword
import com.example.yake.Models.Media
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.keyword_item.view.*
import kotlinx.android.synthetic.main.media_item.view.*

class KeywordAdapter(val lista: List<Keyword>, val context: Context) : RecyclerView.Adapter<ViewHolderFive>() {
    override fun onBindViewHolder(holder: ViewHolderFive, position: Int) {
        holder.keyword.text = lista.get(position).ngram
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderFive {
        val view = ViewHolderFive(LayoutInflater.from(context).inflate(R.layout.keyword_item, p0, false))
        return view
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}

class ViewHolderFive(view: View) : RecyclerView.ViewHolder(view) {
    val keyword = view.keyword
    val line = view.linha

}

