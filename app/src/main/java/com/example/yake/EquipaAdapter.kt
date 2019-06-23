package com.example.yake


import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.yake.Models.MembroEquipa
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_equipa.view.*
import org.jetbrains.anko.toast

class EquipaAdapter(val membros: ArrayList<MembroEquipa>, val context: Context) :
    RecyclerView.Adapter<ViewHolderThree>() {

    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderThree {
        val view = ViewHolderThree(LayoutInflater.from(context).inflate(R.layout.item_equipa, p0, false))

        return view
    }

    override fun getItemCount(): Int {
        return membros.size
    }

    @SuppressLint("ServiceCast")
    override fun onBindViewHolder(p0: ViewHolderThree, p1: Int) {


        p0.membroNome?.text = membros.get(p1).nome
        p0.membroCargo.text = membros.get(p1).cargo
        //p0.membroImagem.setImageBitmap(membros.get(p1).imagemUrl)
        Picasso.get().load(membros.get(p1).imagemUrl).into(p0.membroImagem)
        Picasso.get().load(R.drawable.linkdin_image).into(p0.membrolinkedin)
        Picasso.get().load(R.drawable.mail_image).into(p0.membroMail)
        Picasso.get().load(R.drawable.img_github).into(p0.membroGithub)


        p0.membrolinkedin.setOnClickListener() {
            var lindin: String = membros.get(p1).linkedin
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(lindin))
            startActivity(context, intent, null)
        }
        p0.membroMail.setOnClickListener() {
            var mail: String = membros.get(p1).mail
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copy text", mail)
            clipboard.primaryClip = clip
            context!!.toast(mail + "\n" + context.getString(R.string.mail_foi_copiado))

        }

        if (membros.get(p1).github != "") {
            p0.membroGithub.setOnClickListener {
                var git: String = membros.get(p1).github
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(git))
                startActivity(context, intent, null)
            }
            p0.membroGithub.visibility = View.VISIBLE
        } else {
            p0.membroGithub.visibility = View.GONE
        }

        if (membros.get(p1).web != "") {
            p0.membroWeb.setOnClickListener {
                var web: String = membros.get(p1).web
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
                startActivity(context, intent, null)
            }
            p0.membroWeb.visibility = View.VISIBLE
        } else {
            p0.membroWeb.visibility = View.GONE
        }


    }

}

class ViewHolderThree(view: View) : RecyclerView.ViewHolder(view) {

    val membroNome = view.nome
    val membroImagem = view.image
    val membroCargo = view.cargo
    val membrolinkedin = view.img_linkedin
    val membroMail = view.img_mail
    val membroGithub = view.img_github
    val membroWeb = view.img_web
}