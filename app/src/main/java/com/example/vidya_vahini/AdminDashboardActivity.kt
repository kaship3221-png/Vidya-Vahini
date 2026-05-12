package com.example.vidya_vahini

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        db = FirebaseFirestore.getInstance()

        val routeInput = findViewById<EditText>(R.id.routeInput)
        val statusInput = findViewById<EditText>(R.id.statusInput)
        val etaInput = findViewById<EditText>(R.id.etaInput)
        val emergencyInput = findViewById<EditText>(R.id.emergencyInput)
        val updateBtn = findViewById<Button>(R.id.updateBtn)
        val outputText = findViewById<TextView>(R.id.outputText)
        val newBusInput = findViewById<EditText>(R.id.newBusInput)
        val driverContactInput = findViewById<EditText>(R.id.driverContactInput)
        val newEtaInput = findViewById<EditText>(R.id.newEtaInput)
        val pickupLocationInput = findViewById<EditText>(R.id.pickupLocationInput)

        updateBtn.setOnClickListener {

            val route = routeInput.text.toString().trim().lowercase()
            val status = statusInput.text.toString().trim()
            val eta = etaInput.text.toString().trim()
            val emergency = emergencyInput.text.toString().trim()
            val newBus = newBusInput.text.toString()
            val driverContact = driverContactInput.text.toString()
            val newEta = newEtaInput.text.toString()
            val pickupLocation = pickupLocationInput.text.toString()

            if (route.isEmpty() || status.isEmpty() || eta.isEmpty()) {
                Toast.makeText(this, "Please enter route, status and ETA", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val busData = hashMapOf(
                "route" to route,
                "status" to status,
                "eta" to eta,
                "emergency" to emergency,
                "newBus" to newBus,
                "driverContact" to driverContact,
                "newEta" to newEta,
                "pickupLocation" to pickupLocation
            )

            db.collection("BusDetails")
                .document(route)
                .set(busData)
                .addOnSuccessListener {
                    outputText.text =
                        "Route: $route\nStatus: $status\nETA: $eta Minutes\nEmergency: $emergency"

                    Toast.makeText(this, "Bus details updated", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show()
                }
        }
    }
}