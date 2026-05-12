package com.example.vidya_vahini

import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private var panelType = "student"
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        panelType = intent.getStringExtra("panel") ?: "student"

        val signupTitle = findViewById<TextView>(R.id.signupTitle)
        val createBtn = findViewById<Button>(R.id.createBtn)

        signupTitle.text = if (panelType == "student") "Student Sign Up" else "Admin Sign Up"

        createBtn.setOnClickListener {

            val name = findViewById<EditText>(R.id.signupName).text.toString()
            val email = findViewById<EditText>(R.id.signupEmail).text.toString()
            val password = findViewById<EditText>(R.id.signupPassword).text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Account Created Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            task.exception?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}