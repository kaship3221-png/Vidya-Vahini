package com.example.vidya_vahini

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ParentDashboardActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dashboard)

        db = FirebaseFirestore.getInstance()

        val parentMessageText = findViewById<TextView>(R.id.parentMessageText)
        val checkMessageBtn = findViewById<Button>(R.id.checkMessageBtn)

        checkMessageBtn.setOnClickListener {
            db.collection("SafeReach")
                .document("latest")
                .get()
                .addOnSuccessListener { document ->

                    if (document.exists()) {
                        val message = document.getString("message") ?: "No message"
                        val time = document.getString("time") ?: ""

                        parentMessageText.text = "$message\nTime: $time"
                    } else {
                        parentMessageText.text = "No safe reach message received yet"
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to load message", Toast.LENGTH_SHORT).show()
                }
        }
    }
}