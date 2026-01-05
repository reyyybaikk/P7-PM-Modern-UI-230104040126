package id.antasari.p7_modern_ui_230104040126.ui.theme

import androidx.compose.ui.graphics.Color

// --- NEW PALETTE (Cavosh Inspired) ---
// Warna Baru: Orange Terracotta & Dark Slate
val CoffeeOrange = Color(0xFFE85A4F)
val CoffeeOrangeLight = Color(0xFFFF8A80)
val DarkSlate = Color(0xFF2D3648)
val DarkSlateLight = Color(0xFF3E4C63)

// Warna Netral Baru
val SoftBeige = Color(0xFFF9F9F9)
val SurfaceWhite = Color(0xFFFFFFFF)
val TextDark = Color(0xFF1A202C)
val TextGray = Color(0xFFA0AEC0)

// --- MAPPING KE VARIABEL LAMA ---
// Kita "meminjam" nama variabel lama tapi isinya diganti warna baru
// agar tidak perlu mengubah kode di layar Login/Home dll.

// "BluePrimary" sekarang menjadi Orange Utama
val BluePrimary = CoffeeOrange
val BlueDark = DarkSlate
val BlueLight = CoffeeOrangeLight

// "PurpleAccent" sekarang menjadi Dark Slate Light (Aksen sekunder)
val PurpleAccent = DarkSlateLight

// Warna Status
val GreenAccent = Color(0xFF48BB78)
val OrangeAccent = Color(0xFFED8936)
val RedError = Color(0xFFF56565)

// Background
val SoftGray = SoftBeige // Background sekarang agak krem/putih bersih
val SurfaceGray = Color(0xFFEDF2F7)
val BorderGray = Color(0xFFE2E8F0)

// Text Colors
val TextPrimary = TextDark
val TextSecondary = TextGray
val TextOnPrimary = Color.White

// Dark Mode Colors
val BackgroundDark = DarkSlate
val SurfaceDark = DarkSlateLight
val OnSurfaceDark = Color(0xFFE2E8F0)