package id.antasari.p7_modern_ui_230104040126.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.antasari.p7_modern_ui_230104040126.*
import id.antasari.p7_modern_ui_230104040126.ui.auth.AuthViewModel

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val SECURITY_DETAILS = "security_details"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onBiometricClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by authViewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Auto navigate to Home if signed in
    LaunchedEffect(uiState.isSignedIn) {
        if (uiState.isSignedIn) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = modifier
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                uiState = uiState,
                onEmailChange = authViewModel::onEmailChange,
                onPasswordChange = authViewModel::onPasswordChange,
                onSignInClick = { authViewModel.signInWithPassword() },
                onCreateAccountClick = { navController.navigate(Routes.REGISTER) },
                onBiometricClick = onBiometricClick
            )
        }

        composable(Routes.REGISTER) {
            CreateAccountScreen(
                uiState = uiState,
                onNameChange = authViewModel::onNameChange,
                onEmailChange = authViewModel::onEmailChange,
                onPasswordChange = authViewModel::onPasswordChange,
                onConfirmPasswordChange = authViewModel::onConfirmPasswordChange,
                onSignUpClick = {
                    if (authViewModel.createAccount()) {
                        val current = authViewModel.uiState.value
                        AccountStorage.saveAccount(
                            context,
                            current.name,
                            current.email,
                            current.password,
                            current.isBiometricEnabled
                        )
                        onBiometricClick()
                    }
                },
                onSignInClick = { navController.popBackStack() }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                userName = uiState.name,
                onLogoutClick = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onOpenSecurityDetailsClick = { navController.navigate(Routes.SECURITY_DETAILS) },
                onOpenSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.SECURITY_DETAILS) {
            SecurityDetailsScreen(userName = uiState.name, onBackClick = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() },
                onBiometricToggle = { authViewModel.setBiometricEnabled(it, context) },
                onDarkThemeToggle = { authViewModel.setDarkTheme(it, context) },
                onAppLockToggle = { authViewModel.setAppLockEnabled(it, context) },
                onLoginAlertsToggle = { authViewModel.setLoginAlerts(it, context) },
                onNewDeviceAlertsToggle = { authViewModel.setNewDeviceAlerts(it, context) },
                onWifiWarningToggle = { authViewModel.setWifiWarning(it, context) },
                onDeleteAccountClick = {
                    authViewModel.deleteAccount(context)
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true } // Clear all back stack
                    }
                }
            )
        }
    }
}