package com.example.vidya_vahini

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val signupText = findViewById<TextView>(R.id.signupText)
        val emailInput = findViewById<EditText>(R.id.loginEmail)
        val passwordInput = findViewById<EditText>(R.id.loginPassword)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, PanelSelectionActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        signupText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}