package com.phm.language

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.phm.language.Helper.LocalHelper
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalHelper.onAttach(newBase, "en"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = text_view

        Paper.init(this)

        val language =  Paper.book().read<String>("language")

        if (language == null) {
            Paper.book().write("language", "en")
        }

        updateView(Paper.book().read<String>("language"))
    }

    private fun updateView(lang: String?) {
        val context = LocalHelper.setLocale(this, lang)
        val resources = context.resources
        textView.text = resources.getString(R.string.hello)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.language_en -> {
                Paper.book().write("language", "en")
                updateView(Paper.book().read<String>("language"))
            }
            R.id.language_th -> {
                Paper.book().write("language", "th")
                updateView(Paper.book().read<String>("language"))
            }
        }

        return true
    }
}
