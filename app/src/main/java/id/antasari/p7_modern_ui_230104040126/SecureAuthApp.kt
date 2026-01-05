package id.antasari.p7_modern_ui_230104040126

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import id.antasari.p7_modern_ui_230104040126.ui.auth.AuthViewModel
import id.antasari.p7_modern_ui_230104040126.ui.navigation.AppNavHost

@Composable
fun SecureAuthApp(
    authViewModel: AuthViewModel,
    onBiometricClick: () -> Unit
) {
    val navController = rememberNavController()

    AppNavHost(
        navController = navController,
        authViewModel = authViewModel, // Pastikan parameter ini ada di AppNavHost
        onBiometricClick = onBiometricClick
    )
}