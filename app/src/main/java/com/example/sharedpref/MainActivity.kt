package com.example.sharedpref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    @SuppressLint("CommitPrefEdits", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.editName)
        val editId = findViewById<EditText>(R.id.editId)
        val outputName = findViewById<TextView>(R.id.outputName)
        val outputId = findViewById<TextView>(R.id.outputId)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val sharedPrefrence: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(editId.text.toString())
            val name: String = editName.text.toString()
            val editor: SharedPreferences.Editor = sharedPrefrence.edit()
            editor.putInt("id_key", id)
            editor.putString("id_name", name)
            editor.apply()
            Toast.makeText(this,"Save Clicked",Toast.LENGTH_SHORT).show()

        }

        btnView.setOnClickListener {
            val sharedId = sharedPrefrence.getInt("id_key", 0)
            val sharedName = sharedPrefrence.getString("id_name", "default Name")
            if (sharedId.equals(0) && sharedName.equals("default Name")) {
                outputId.setText("Your id : $sharedId").toString()
                outputName.setText("Your name : $sharedName").toString()
            } else {
                outputId.setText("Your id : ${sharedId}").toString()
                outputName.setText("Your name : ${sharedName}").toString()
            }

            Toast.makeText(this,"View Clicked",Toast.LENGTH_SHORT).show()

        }

        btnClear.setOnClickListener {
            val editor = sharedPrefrence.edit()
            editor.clear()
            editor.apply()
            outputName.setText("")
            outputId.setText("")
            Toast.makeText(this,"Clear Clicked",Toast.LENGTH_SHORT).show()

        }


    }
}