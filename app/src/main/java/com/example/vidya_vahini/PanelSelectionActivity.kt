package com.example.vidya_vahini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PanelSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_selection)

        val studentBtn = findViewById<Button>(R.id.studentBtn)
        val adminBtn = findViewById<Button>(R.id.adminBtn)
        val parentBtn = findViewById<Button>(R.id.parentBtn)

        studentBtn.setOnClickListener {
            startActivity(Intent(this, StudentDashboardActivity::class.java))
        }

        adminBtn.setOnClickListener {
            startActivity(Intent(this, AdminDashboardActivity::class.java))
        }

        parentBtn.setOnClickListener {
            startActivity(Intent(this, ParentDashboardActivity::class.java))
        }
    }
}