package top.omooo.develop_tools.sp

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_preference_manager.*
import top.omooo.develop_tools.R
import java.io.File

/**
 * Created by Omooo
 * Date:2019/6/11
 */
class PreferenceManagerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PreferenceManager"
        private const val REQUEST_CODE = 0x01
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_manager)

        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }else{
            parsePreferences()
        }
    }

    private fun parsePreferences() {
        val prefsFolder = File(applicationInfo.dataDir, "shared_prefs")
        Log.d(TAG, prefsFolder.path)
        if (prefsFolder.exists() && prefsFolder.isDirectory) {
            prefsFolder.list()
                    .map {
                        truncateXmlExtension(it)
                    }
                    .forEach {
                        generateForm(it)
                    }
        } else {
            Log.d(TAG, "null")
        }
    }

    private fun truncateXmlExtension(it: String): String {
        return if (it.endsWith(".xml"))
            it.substring(0, it.indexOf(".xml"))
        else
            it
    }

    private fun generateForm(prefsName: String) {
        val file = File(applicationInfo.dataDir + "/shared_prefs", "$prefsName.xml")
        val preferences = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val entries = ArrayList<Pair<String, *>>()
        for ((key, value) in preferences.all) {
            Log.d(TAG, String.format("(%s) %s = %s", prefsName, key, value.toString()))
            entries.add(Pair(key, value))
        }
        addSection(prefsName, file.length(), entries, preferences)
    }

    private fun addSection(prefsName: String, fileSize: Long, entries: ArrayList<Pair<String, *>>, preferences: SharedPreferences) {
        val sectionView = LayoutInflater.from(this).inflate(R.layout.item_preference, null)
        val tvFileName = sectionView.findViewById<TextView>(R.id.tv_name)
        val tvFileSize = sectionView.findViewById<TextView>(R.id.tv_size)
        val llContent = sectionView.findViewById<LinearLayout>(R.id.ll_content)
        tvFileName.text = prefsName
        tvFileSize.text = fileSize.toString()
        ll_root.addView(sectionView)

        for (pref in entries) {
            val listItem = LayoutInflater.from(this).inflate(R.layout.item_preference_list, null)
            val tvKey = listItem.findViewById<TextView>(R.id.tv_key)
            val tvValue = listItem.findViewById<TextView>(R.id.tv_value)
            tvKey.text = pref.first
            tvValue.text = pref.second.toString()
            llContent.addView(listItem)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            parsePreferences()
        }
    }
}