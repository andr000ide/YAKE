package com.example.yake.Fragmentos


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.yake.Auxiliares.*
import com.example.yake.Models.Example_Yake
import com.example.yake.Models.UrlExample
import com.example.yake.Models.Wordcloud
import com.example.yake.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_url.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.util.*


class FragmentUrl : androidx.fragment.app.Fragment() {
    private var sharePath = "no"
    private var aux = 0
    lateinit var call: Call<Example_Yake>
    lateinit var call2: Call<UrlExample>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_url, container, false)
        val adapter = ViewPagerAdapter(childFragmentManager)


        val url = arguments?.getString("url")

        var ngram = arguments?.getString("ngram")

        if(ngram == null){
            ngram = "3"
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
            call = service!!.search_keywords_url(url!!,ngram.toInt(),15)
            call?.enqueue(object : Callback<Example_Yake> {


                override fun onResponse(call: Call<Example_Yake>, response: Response<Example_Yake>) {
                    val examples = response.body()
                    if (response.message().equals("INTERNAL SERVER ERROR")) {
                        withButtonCentered(view)
                        //Toast.makeText(activity,"Erro, tente com outro input",Toast.LENGTH_LONG);
                        //activity!!.onBackPressed()
                    }
                    if (examples == null) {
                        view.linear_vis.visibility = View.VISIBLE
                        view.spin_kit.visibility = View.INVISIBLE
                        // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                        activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        view.view_grayscreen.visibility = View.GONE
                        // por popup e mandar para o fragmento two
                    }
                    examples?.let {


                        ////////////////////////////

                        val service2 = RetrofitURLInstance.retrofitInstance?.create(ServiceAPI::class.java)
                        call2 = service2!!.search_url(url!!)
                        call2?.enqueue(object : Callback<UrlExample> {
                            override fun onFailure(call: Call<UrlExample>, t: Throwable) {
                                if (aux != 1) {
                                    activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    withButtonCentered(view)
                                }
                            }

                            override fun onResponse(call: Call<UrlExample>, response: Response<UrlExample>) {
                                var urlExample = response.body()
                                var gson = Gson()
                                var jsonString = gson.toJson(examples)


                                var fragmento1 = FragmentoAnnotatedText.newInstance(jsonString,urlExample!!.content!!,urlExample!!.title!!)
                                var fragmento2 = Fragmento_WordCloud.newInstance(jsonString)

                                adapter.addFragment(fragmento1, "Annotated Text")
                                adapter.addFragment(fragmento2, "WordCloud")
                                view.viewpager.adapter = adapter
                                view.tabs.setupWithViewPager(view.viewpager)

                                view.linear_vis.visibility = View.VISIBLE
                                view.spin_kit.visibility = View.INVISIBLE

                                // faz com que o utilizador volte a conseguir carregar depois de fazer o load
                                activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                view.view_grayscreen.visibility = View.GONE
                            }

                        })



                        ///////////////////////////



                    }
                }

                override fun onFailure(call: Call<Example_Yake>, t: Throwable) {
                    if (aux != 1) {
                        activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        withButtonCentered(view)
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
        if (::call2.isInitialized) {
            aux = 1
            activity!!.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            call2.cancel()
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
        fun newInstance(url: String,ngram : String): FragmentUrl {
            val args = Bundle()
            args.putString("url", url)
            args.putString("ngram", ngram)
            val fragment = FragmentUrl()
            fragment.arguments = args
            return fragment
        }
    }

    fun withButtonCentered(view: View) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(getString(R.string.error_message))
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setNeutralButton("Ok") { dialog, which ->
            activity!!.onBackPressed()
        }
        builder.show()
    }


}
