package com.example.yake.Fragmentos


import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.view.*
import android.widget.Button
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.Auxiliares.RetrofitClientInstance
import com.example.yake.Auxiliares.ServiceAPI
import com.example.yake.Auxiliares.ViewPagerAdapter
import com.example.yake.Models.Example_Yake
import com.example.yake.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_url.view.*
import kotlinx.android.synthetic.main.layout_error.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.util.*


class FragmentTexto : androidx.fragment.app.Fragment() {
    private var sharePath = "no"
    private var aux = 0
    lateinit var call: Call<Example_Yake>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_url, container, false)
        val adapter = ViewPagerAdapter(childFragmentManager)


        val texto = arguments?.getString("texto")
        var titulo = arguments?.getString("titulo")
        var ngram = arguments?.getString("ngram")

        if(ngram == null){
            ngram = "3"
        }
        if(titulo==null){
            titulo=""
        }

        view.linear_vis.visibility = View.INVISIBLE
        // faz com que o utilizador nao consiga carregar enquanto faz load
        activity!!.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
        view.view_grayscreen.visibility = View.VISIBLE

        view.spin_kit.visibility = View.VISIBLE

        val service = RetrofitClientInstance.retrofitInstance?.create(ServiceAPI::class.java)
        call = service!!.search_keywords_texto(texto!!,titulo,ngram.toInt(),20)
        call?.enqueue(object : Callback<Example_Yake> {


            override fun onResponse(call: Call<Example_Yake>, response: Response<Example_Yake>) {
                val examples = response.body()
                if (response.message().equals("INTERNAL SERVER ERROR")) {
                    view.linear_vis.visibility = View.VISIBLE
                    view.spin_kit.visibility = View.INVISIBLE
                    // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    view.view_grayscreen.visibility = View.GONE
                    withButtonCentered(view,"vazio")
                    //Toast.makeText(activity,"Erro, tente com outro input",Toast.LENGTH_LONG);
                    //activity!!.onBackPressed()
                }
                else if (examples == null) {
                    view.linear_vis.visibility = View.VISIBLE
                    view.spin_kit.visibility = View.INVISIBLE
                    // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    view.view_grayscreen.visibility = View.GONE
                    // por popup e mandar para o fragmento two
                }
                else if( examples.keywords.isEmpty()){
                    view.linear_vis.visibility = View.VISIBLE
                    view.spin_kit.visibility = View.INVISIBLE
                    // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    view.view_grayscreen.visibility = View.GONE
                    withButtonCentered(view,"vazio")
                }

                else{


                examples?.let {



                    var gson = Gson()
                    var jsonString = gson.toJson(examples)

                    var fragmento1 = FragmentoAnnotatedText.newInstance(jsonString,texto,titulo)
                    var fragmento3 = FragmentoKeywords.newInstance(jsonString)
                    var fragmento2 = Fragmento_WordCloud.newInstance(jsonString)


                    adapter.addFragment(fragmento1, getString(R.string.key_annotated_text))
                    adapter.addFragment(fragmento3, getString(R.string.key_keywords))
                    adapter.addFragment(fragmento2, getString(R.string.key_wordcloud))
                    view.viewpager.adapter = adapter

                    view.viewpager.offscreenPageLimit = 3
                    view.tabs.setupWithViewPager(view.viewpager)

                    view.linear_vis.visibility = View.VISIBLE
                    view.spin_kit.visibility = View.INVISIBLE

                    // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    view.view_grayscreen.visibility = View.GONE

                }
                }
            }

            override fun onFailure(call: Call<Example_Yake>, t: Throwable) {
                if (aux != 1) {
                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    withButtonCentered(view,"failure")
                }
            }
        })




        view.shareButton.setOnClickListener {
            //val bitmap = loadBitmapFromView(activity!!.window.decorView.rootView)
            //saveImage(bitmap)
            //share(sharePath, bitmap, queryPesquisa!!, years)
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        if (::call.isInitialized) {
            aux = 1
            activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            call.cancel()
        }
    }


    fun saveImage(bitmap: Bitmap) {
        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File(root + "/contamehistorias")
        myDir.mkdirs()
        val fname = now
        val file = File(myDir, "$fname.png")
        sharePath = file.toString()
        if (file.exists())
            file.delete()
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBitmapFromView(v: View): Bitmap {
        // create bitmap screen capture
        v.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(v.drawingCache)
        v.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun share(sharePath: String, bitmap: Bitmap, pesquisa: String, anos: String) {

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val file = File(sharePath)
        val uri = Uri.fromFile(file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "http://contamehistorias.pt/arquivopt/search?query=" + pesquisa + "&lastyears=" + anos + "&lang_code=pt"
        )
        startActivity(Intent.createChooser(intent, "share image via..."))
    }


    companion object {
        fun newInstance(texto: String,titulo : String, ngram : String): FragmentTexto {
            val args = Bundle()
            args.putString("texto", texto)
            args.putString("titulo", titulo)
            args.putString("ngram", ngram)
            val fragment = FragmentTexto()
            fragment.arguments = args
            return fragment
        }
    }

    fun withButtonCentered(view: View,string : String) {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_error)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

        if(string=="internal" || string=="failure"){
            dialog.texto1.text = getString(R.string.key_error_texto1_internal)
            dialog.texto2.text = getString(R.string.key_error_texto2_internal)
        }
        if(string=="vazio"){
            dialog.texto1.text = getString(R.string.key_error_texto1_semresultados_texto)
            dialog.texto2.text = ""
        }


        val button = dialog.findViewById(R.id.buttonOk) as Button

        button.setOnClickListener {
            dialog.dismiss()
            activity!!.onBackPressed()
        }
        dialog.show()
    }


}
