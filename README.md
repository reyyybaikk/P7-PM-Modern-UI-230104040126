# 🔐 SecureAuth - Modern UI & Biometric Security App


### 👤 Author
M. Reyhan

NIM: 230104040126

Institusi: UIN Antasari Banjarmasin

Mata Kuliah: Mobile Programming


> **Modul Praktikum #7 Mobile Programming**
>
> Sebuah aplikasi Android modern yang mendemonstrasikan implementasi **Material Design 3**, **Autentikasi Biometrik**, **Manajemen Tema**, dan **Keamanan Aplikasi**.

---


---

## ✨ Fitur Utama

### 🎨 Modern UI & Theming ("Cavosh" Style)
* **Custom Design System:** Menggunakan palet warna *Terracotta Orange* & *Dark Slate Blue* yang terinspirasi dari tema Coffee Shop modern.
* **Material 3 Components:** Implementasi penuh `ElevatedCard`, `OutlinedTextField`, `Switch`, dan `Shape` yang dinamis (Pill-shaped buttons).
* **Dark & Light Mode:** Dukungan tema gelap dan terang yang dapat diatur langsung dari aplikasi.

### 🔐 Keamanan & Autentikasi
* **Biometric Login:** Integrasi sensor sidik jari (Fingerprint) menggunakan Android Biometric API.
* **App Lock Mechanism:** Fitur keamanan otomatis yang mengunci aplikasi saat pengguna meninggalkannya (background) selama durasi tertentu.
* **Input Validation:** Validasi real-time untuk email, password, dan konfirmasi password.
* **Account Persistence:** Penyimpanan sesi dan pengaturan pengguna menggunakan `SharedPreferences` yang aman.

### 🚀 Navigasi & Arsitektur
* **Jetpack Navigation Compose:** Manajemen rute antar layar (Login -> Home -> Settings) yang mulus.
* **MVVM Architecture:** Pemisahan logic menggunakan `AuthViewModel` dan `StateFlow` untuk reaktivitas UI yang tinggi.
* **State Hoisting:** Pengelolaan state UI yang efisien dan *stateless composables*.

---




---

## 📂 Struktur Proyek

```text
id.antasari.p7_modern_ui_230104040079
├── MainActivity.kt          // Entry point & App Lifecycle (App Lock logic)
├── SecureAuthApp.kt         // Root Composable & NavController setup
├── AccountStorage.kt        // Local Data Persistence (SharedPreferences)
├── BiometricUtils.kt        // Helper untuk cek ketersediaan sensor
├── LoginScreen.kt
├── CreateAccountScreen.kt
├── HomeScreen.kt
├── SecurityDetailsScreen.kt
├──SettingsScreen.kt
├── ui
│   ├── auth
│   │   └── AuthViewModel.kt // Logic pusat (State Management)
│   ├── components           // Reusable UI Components
│   │   ├── AppButton.kt
│   │   ├── AppCard.kt
│   │   ├── AppTextField.kt
│   │   └── ...
│   ├── navigation
│   │   └── AppNavHost.kt    // Definisi Rute Navigasi
│   └── theme                // Design System (Cavosh Theme)
│       ├── Color.kt         // Palet Warna (Orange & Dark Slate)
│       ├── Shape.kt         // Bentuk Sudut (Rounded/Pill)
│       ├── Type.kt          // Tipografi
│       └── Theme.kt         // Konfigurasi Tema Global

    
```

----

----

### 🚀 Cara Menjalankan
Clone Repository

```

git clone [https://github.com/username/P7_Modern_Ui_230104040079.git](https://github.com/username/P7_Modern_Ui_230104040079.git)

```
* Buka di Android Studio
* Pilih File > Open dan arahkan ke folder proyek.
* Sync Gradle
* Pastikan koneksi internet aktif untuk mengunduh dependency.
* Setup Emulator/Device
* Gunakan device yang memiliki sensor sidik jari atau Emulator dengan fitur Fingerprint yang diaktifkan.
* Run

----
