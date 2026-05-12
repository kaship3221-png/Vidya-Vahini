package com.example.vidya_vahini

import android.widget.Switch
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        val routeSpinner = findViewById<Spinner>(R.id.routeSpinner)
        val statusText = findViewById<TextView>(R.id.statusText)
        val etaText = findViewById<TextView>(R.id.etaText)
        val emergencyText = findViewById<TextView>(R.id.emergencyText)
        val busDetailsText = findViewById<TextView>(R.id.busDetailsText)
        val reportBtn = findViewById<Button>(R.id.reportBtn)
        val safeReachBtn = findViewById<Button>(R.id.safeReachBtn)
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        val themeSwitch = findViewById<Switch>(R.id.themeSwitch)

        val routes = arrayOf("Select Route", "ynk", "mvit route", "citnc", "village")

        routeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            routes
        )

        reportBtn.setOnClickListener {

            val selectedRoute = routeSpinner.selectedItem.toString()

            if (selectedRoute == "Select Route") {
                Toast.makeText(this, "Please select route", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseFirestore.getInstance()
                .collection("BusDetails")
                .document(selectedRoute.lowercase())
                .get()
                .addOnSuccessListener { document ->

                    if (document.exists()) {
                        val status = document.getString("status") ?: "No status"
                        val eta = document.getString("eta") ?: "--"
                        val emergency = document.getString("emergency") ?: ""
                        val newBus = document.getString("newBus") ?: "Not available"
                        val driverContact = document.getString("driverContact") ?: "Not available"
                        val newEta = document.getString("newEta") ?: "Not available"
                        val pickupLocation = document.getString("pickupLocation") ?: "Not available"

                        busDetailsText.text =
                            "New Bus Number: $newBus\n" +
                                    "Driver Contact: $driverContact\n" +
                                    "New ETA: $newEta\n" +
                                    "Pickup Location: $pickupLocation"

                        statusText.text = "Bus Status: $status"
                        etaText.text = "ETA: $eta Minutes"

                        if (status.lowercase() == "breakdown" || emergency.isNotEmpty()) {
                            emergencyText.text =
                                "⚠ Emergency Alert:\n$emergency\nSuggestion: Use backup bus or contact transport office."
                        } else {
                            emergencyText.text = "No emergency alert"
                        }

                    } else {
                        statusText.text = "Bus Status: No data found"
                        etaText.text = "ETA: --"
                        emergencyText.text = "No emergency alert"
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
        }

        safeReachBtn.setOnClickListener {

            val safeData = hashMapOf(
                "message" to "Your child reached college safely",
                "time" to java.text.SimpleDateFormat(
                    "hh:mm a, dd MMM yyyy",
                    java.util.Locale.getDefault()
                ).format(java.util.Date())
            )

            FirebaseFirestore.getInstance()
                .collection("SafeReach")
                .document("latest")
                .set(safeData)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Safe Reach Message Sent To Parents",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Failed To Send Message",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }
}