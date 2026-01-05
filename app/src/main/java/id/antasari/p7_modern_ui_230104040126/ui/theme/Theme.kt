package id.antasari.p7_modern_ui_230104040126.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Skema Warna Terang (Light Mode) - Tema Cavosh/Coffee
private val LightColorScheme = lightColorScheme(
    primary = CoffeeOrange,           // Warna tombol utama (Orange)
    onPrimary = Color.White,          // Warna teks di atas tombol (Putih)
    primaryContainer = CoffeeOrange.copy(alpha = 0.1f),
    onPrimaryContainer = CoffeeOrange,

    secondary = DarkSlate,            // Warna aksen (Biru Gelap)
    onSecondary = Color.White,
    secondaryContainer = DarkSlateLight,
    onSecondaryContainer = Color.White,

    tertiary = PurpleAccent,          // (Opsional)

    background = SoftBeige,           // Background halaman (Krem sangat muda)
    onBackground = TextPrimary,

    surface = SurfaceWhite,           // Background Card (Putih)
    onSurface = TextPrimary,

    surfaceVariant = SurfaceGray,
    onSurfaceVariant = TextSecondary,

    error = RedError,
    outline = BorderGray
)

// Skema Warna Gelap (Dark Mode)
private val DarkColorScheme = darkColorScheme(
    primary = CoffeeOrange,
    onPrimary = Color.White,
    primaryContainer = DarkSlate,
    onPrimaryContainer = CoffeeOrangeLight,

    secondary = BlueLight,
    onSecondary = DarkSlate,

    background = BackgroundDark,      // Background gelap
    onBackground = Color.White,

    surface = SurfaceDark,            // Card gelap
    onSurface = Color.White,

    error = RedError,
    outline = DarkSlateLight
)

@Composable
fun P7ModernUiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // PENTING: Set false agar warna aplikasi KONSISTEN Orange (tidak ikut wallpaper HP)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}