package id.antasari.p7_modern_ui_230104040126

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import id.antasari.p7_modern_ui_230104040126.ui.auth.AuthViewModel
import id.antasari.p7_modern_ui_230104040126.ui.theme.P7ModernUiTheme

class MainActivity : FragmentActivity() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isBiometricReady = BiometricUtils.isBiometricReady(this)
        authViewModel.setBiometricAvailability(isBiometricReady)

        val storedAccount = AccountStorage.loadAccount(this)
        if (storedAccount != null) {
            authViewModel.restoreAccountFromStorage(storedAccount)
        }

        setupBiometric()

        setContent {
            // Observasi state tema dari ViewModel
            val uiState by authViewModel.uiState.collectAsState()

            // Terapkan tema (dark/light) berdasarkan state
            P7ModernUiTheme(darkTheme = uiState.isDarkTheme) {
                SecureAuthApp(
                    authViewModel = authViewModel,
                    onBiometricClick = { showBiometricPrompt() }
                )
            }
        }
    }

    // --- APP LOCK LIFECYCLE LOGIC ---
    override fun onPause() {
        super.onPause()
        // Catat waktu saat aplikasi masuk background
        authViewModel.onAppBackgrounded()
    }

    override fun onResume() {
        super.onResume()
        // Cek apakah perlu dikunci
        val shouldLock = authViewModel.onAppForegrounded()
        if (shouldLock) {
            // Jika terkunci, user harus login ulang (otomatis logout di VM)
            Toast.makeText(this, "App locked due to inactivity.", Toast.LENGTH_SHORT).show()
            // Opsional: Langsung trigger biometric jika fitur itu aktif
            if (authViewModel.uiState.value.isBiometricEnabled) {
                showBiometricPrompt()
            }
        }
    }

    private fun setupBiometric() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    authViewModel.onBiometricAuthenticated()
                    Toast.makeText(applicationContext, "Authenticated", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Error: $errString", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Verify identity to proceed")
            .setNegativeButtonText("Use Password")
            .build()
    }

    private fun showBiometricPrompt() {
        biometricPrompt.authenticate(promptInfo)
    }
}