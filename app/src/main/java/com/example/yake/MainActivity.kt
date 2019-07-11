package com.example.yake

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.example.yake.Auxiliares.EndDrawerToggle
import com.example.yake.Auxiliares.LangHelper
import com.example.yake.Fragmentos.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var langHelper: LangHelper
    private val TAG = "PermissionDemo"
    private val RECORD_REQUEST_CODE = 101
    private var navView: NavigationView? = null
    private var check: Int = 0
    private var drawer: androidx.drawerlayout.widget.DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //makeReq()




        val checkIfFirstTime = intent.getBooleanExtra("FIRSTTIME", true)
        val checkIfFirstTime2 = intent.getBooleanExtra("SECONDTIME", false)
        langHelper = LangHelper(applicationContext)



        setSupportActionBar(toolbar)
        val actionbar = supportActionBar

        drawer = drawerlayout2
        val toggle = EndDrawerToggle(
            this, drawerlayout2, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerlayout2.addDrawerListener(toggle)
        toggle.syncState()

        navView = nav_view2
        navView?.setNavigationItemSelectedListener(this)






        if (checkIfFirstTime == true) {
            refreshApp(langHelper.getLanguageSaved())
        }


        Btn_try.setOnClickListener {
            val randomIntent = Intent(this, SecondActivity::class.java)
            randomIntent.putExtra("indicacao","try")
            startActivity(randomIntent)
        }


        btn_url.setOnClickListener {
            val randomIntent = Intent(this, SecondActivity::class.java)
            randomIntent.putExtra("indicacao","url")
            startActivity(randomIntent)
        }

        btn_texto.setOnClickListener {
            val randomIntent = Intent(this, SecondActivity::class.java)
            randomIntent.putExtra("indicacao","texto")
            startActivity(randomIntent)
        }

    }





    private fun makeReq(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                RECORD_REQUEST_CODE)
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), RECORD_REQUEST_CODE)
            } else {
            }
        }
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_one -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","try")
                startActivity(randomIntent)
            }
            R.id.nav_two -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","texto")
                startActivity(randomIntent)
            }
            R.id.nav_three -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","about")
                startActivity(randomIntent)
            }
            R.id.nav_four -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","team")
                startActivity(randomIntent)
            }
            R.id.nav_five -> {
                if (p0.title == "Português") {
                    refreshApp("pt")
                } else {
                    refreshApp("en")
                }
            }
            R.id.nav_six -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","agradecimentos")
                startActivity(randomIntent)
            }
            R.id.nav_eight  -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","contactos")
                startActivity(randomIntent)
            }
            R.id.nav_nine  -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","media")
                startActivity(randomIntent)
            }
            R.id.nav_ten  -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","url")
                startActivity(randomIntent)
            }
            R.id.nav_eleven  -> {
                val randomIntent = Intent(this, SecondActivity::class.java)
                randomIntent.putExtra("indicacao","projetos")
                startActivity(randomIntent)
            }
        }

        drawer?.closeDrawer(GravityCompat.END)
        return true
    }

    fun refreshApp(lang: String) {
        val context = langHelper.setNewLocale(this, lang)
        val refresh = Intent(context, MainActivity::class.java)
        refresh.putExtra("FIRSTTIME", false)
        finish()
        startActivity(refresh)
    }

    override fun onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
        //super.onBackPressed()
    }
}

