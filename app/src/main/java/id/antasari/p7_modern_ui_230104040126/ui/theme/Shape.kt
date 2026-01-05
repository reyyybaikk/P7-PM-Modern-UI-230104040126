package id.antasari.p7_modern_ui_230104040126.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    // Extra Small (untuk chips/tag kecil)
    extraSmall = RoundedCornerShape(8.dp),

    // Small (untuk card kecil atau elemen input)
    small = RoundedCornerShape(12.dp),

    // Medium (untuk Card utama - kita buat lebih rounded/melengkung)
    medium = RoundedCornerShape(24.dp),

    // Large (untuk BottomSheet atau Modal)
    large = RoundedCornerShape(32.dp),

    // Extra Large (KHUSUS untuk Button utama agar berbentuk Pill/Lonjong)
    extraLarge = RoundedCornerShape(50)
)