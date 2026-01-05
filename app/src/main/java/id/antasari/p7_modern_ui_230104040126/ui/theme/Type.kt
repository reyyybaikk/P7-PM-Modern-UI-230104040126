package id.antasari.p7_modern_ui_230104040126.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Menggunakan Font Sans Serif default yang modern
private val DefaultFontFamily = FontFamily.SansSerif

val AppTypography = Typography(
    // Judul Besar (Contoh: "SecureAuth" di Login, atau Header Halaman)
    headlineSmall = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold, // Lebih tebal
        fontSize = 26.sp,
        lineHeight = 32.sp,
        color = TextPrimary,
        letterSpacing = (-0.5).sp // Sedikit rapat agar elegan
    ),

    // Subjudul (Contoh: Judul Section di Home)
    titleMedium = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = TextPrimary
    ),

    // Teks Biasa (Input field, Deskripsi paragraf)
    bodyMedium = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        color = TextPrimary
    ),

    // Teks Kecil (Caption, Label input yang tidak fokus)
    bodySmall = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        color = TextSecondary
    ),

    // Label Tombol (Penting untuk tombol Pill Shape kita)
    labelLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Label kecil (Chip status)
    labelSmall = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)