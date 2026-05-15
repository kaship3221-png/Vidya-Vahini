Vidya-Vahini 🚍

Smart College Transport Management System

 Overview

Vidya-Vahini is an Android-based smart transport management application developed for colleges and schools.
The application helps students, parents, and administrators manage transport communication efficiently using real-time updates and Firebase integration.

The system provides:

Bus status updates
ETA tracking
Emergency/breakdown alerts
Replacement bus details
Parent safe-reach notifications


 Features

 
 Student Panel

 
View bus route
Check bus status
View ETA
Receive emergency alerts
View replacement bus details
Send safe reach notification to parents


 Admin Panel


Update route details
Update bus status
Add ETA
Send breakdown/emergency alerts
Add replacement bus details:
New Bus Number
Driver Contact
New ETA
Pickup Location


 Parent Panel

 
Receive safe reach confirmation
View notification time
Monitor student arrival status
🛠 Technologies Used
Kotlin
XML
Android Studio
Firebase Firestore
Firebase Authentication
Android SDK
Material Design Components


 Firebase Features


Firebase Authentication
Cloud Firestore Database
Real-time Data Updates


 Project Structure


Vidya-Vahini/
│
├── app/
│   ├── java/com/example/vidya_vahini/
│   │   ├── LoginActivity.kt
│   │   ├── SignupActivity.kt
│   │   ├── PanelSelectionActivity.kt
│   │   ├── StudentDashboardActivity.kt
│   │   ├── AdminDashboardActivity.kt
│   │   └── ParentDashboardActivity.kt
│   │
│   └── res/layout/
│       ├── activity_login.xml
│       ├── activity_signup.xml
│       ├── activity_panel_selection.xml
│       ├── activity_student_dashboard.xml
│       ├── activity_admin_dashboard.xml
│       └── activity_parent_dashboard.xml



⚙️ Installation


1️⃣ Clone Repository

git clone https://github.com/your-username/Vidya-Vahini.git


2️⃣ Open in Android Studio


Open Android Studio
Click Open Project
Select the project folder


3️⃣ Connect Firebase


Open Firebase Console
Create a Firebase project
Add Android App
Download google-services.json
Paste inside:
app/google-services.json


4️⃣ Sync Gradle

Click:

Sync Project with Gradle Files


5️⃣ Run Application


Connect Emulator or Android Device
Click ▶ Run


 Application Workflow


Admin Updates Bus Status
            ↓
Firebase Firestore Updates
            ↓
Students Receive Updates
            ↓
Students Send Safe Reach Message
            ↓
Parents Receive Confirmation


 Emergency Features


Breakdown alerts
Alternative bus arrangement
Driver contact sharing
Updated pickup location
New ETA updates


 Future Enhancements


Live GPS Tracking
Google Maps Integration
Push Notifications
AI-based ETA Prediction
QR Attendance System
Driver Authentication


 Developer

Kashinath
BE – Computer Science Engineering

📄 License

This project is developed for educational and academic purposes.
