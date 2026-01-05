package id.antasari.p7_modern_ui_230104040126.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import id.antasari.p7_modern_ui_230104040126.AccountStorage
import id.antasari.p7_modern_ui_230104040126.StoredAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,

    // Biometric & Security
    val isBiometricAvailable: Boolean = false,
    val isBiometricEnabled: Boolean = false,
    val appLockEnabled: Boolean = true,

    // Theme & Alerts
    val isDarkTheme: Boolean = false,
    val loginAlertsEnabled: Boolean = true,
    val newDeviceAlertsEnabled: Boolean = true,
    val publicWifiWarningEnabled: Boolean = false,

    // Account Data
    val registeredEmail: String? = null,
    val registeredPassword: String? = null,
    val lastErrorMessage: String? = null
)

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    // App Lock Logic
    private var lastBackgroundTime: Long = 0
    private val LOCK_TIMEOUT_MS = 5000L // 5 detik untuk testing (biasanya 30s - 1m)

    fun onNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName, lastErrorMessage = null)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, lastErrorMessage = null)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword, lastErrorMessage = null)
    }

    fun onConfirmPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = newPassword, lastErrorMessage = null)
    }

    fun setBiometricAvailability(available: Boolean) {
        _uiState.value = _uiState.value.copy(isBiometricAvailable = available)
    }

    fun restoreAccountFromStorage(account: StoredAccount) {
        _uiState.value = _uiState.value.copy(
            name = account.name,
            registeredEmail = account.email,
            registeredPassword = account.password,
            isBiometricEnabled = account.biometricEnabled,
            isDarkTheme = account.isDarkTheme,
            appLockEnabled = account.appLockEnabled,
            loginAlertsEnabled = account.loginAlertsEnabled,
            newDeviceAlertsEnabled = account.newDeviceAlertsEnabled,
            publicWifiWarningEnabled = account.publicWifiWarningEnabled
        )
    }

    fun signInWithPassword() {
        val current = _uiState.value
        if (current.email.isBlank() || current.password.isBlank()) {
            _uiState.value = current.copy(lastErrorMessage = "Email dan password wajib diisi.")
            return
        }
        if (current.email != current.registeredEmail || current.password != current.registeredPassword) {
            _uiState.value = current.copy(lastErrorMessage = "Email atau password salah.")
            return
        }
        // Login sukses
        _uiState.value = current.copy(isSignedIn = true, lastErrorMessage = null)
    }

    fun onBiometricAuthenticated() {
        _uiState.value = _uiState.value.copy(isSignedIn = true, lastErrorMessage = null)
    }

    fun createAccount(): Boolean {
        val current = _uiState.value
        if (current.name.isBlank() || current.email.isBlank() || current.password.isBlank()) {
            _uiState.value = current.copy(lastErrorMessage = "Semua data wajib diisi.")
            return false
        }
        if (current.password != current.confirmPassword) {
            _uiState.value = current.copy(lastErrorMessage = "Password tidak sama.")
            return false
        }

        _uiState.value = current.copy(
            registeredEmail = current.email,
            registeredPassword = current.password,
            isBiometricEnabled = true,
            lastErrorMessage = null
        )
        return true
    }

    fun logout() {
        // Hanya reset status login, data akun tetap ada
        _uiState.value = _uiState.value.copy(isSignedIn = false, email = "", password = "")
    }

    fun deleteAccount(context: Context) {
        // Hapus storage dan reset semua state
        AccountStorage.clearAccount(context)
        _uiState.value = AuthUiState(isBiometricAvailable = _uiState.value.isBiometricAvailable)
    }

    // --- SETTINGS UPDATERS ---
    // Helper untuk auto-save setiap kali setting berubah
    private fun saveSettings(context: Context) {
        val current = _uiState.value
        // Hanya save jika user sudah terdaftar
        if (current.registeredEmail != null) {
            AccountStorage.saveAccount(
                context,
                current.name,
                current.registeredEmail,
                current.registeredPassword ?: "",
                current.isBiometricEnabled,
                current.isDarkTheme,
                current.appLockEnabled,
                current.loginAlertsEnabled,
                current.newDeviceAlertsEnabled,
                current.publicWifiWarningEnabled
            )
        }
    }

    fun setBiometricEnabled(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(isBiometricEnabled = enabled)
        saveSettings(context)
    }

    fun setDarkTheme(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(isDarkTheme = enabled)
        saveSettings(context)
    }

    fun setAppLockEnabled(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(appLockEnabled = enabled)
        saveSettings(context)
    }

    fun setLoginAlerts(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(loginAlertsEnabled = enabled)
        saveSettings(context)
    }

    fun setNewDeviceAlerts(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(newDeviceAlertsEnabled = enabled)
        saveSettings(context)
    }

    fun setWifiWarning(enabled: Boolean, context: Context) {
        _uiState.value = _uiState.value.copy(publicWifiWarningEnabled = enabled)
        saveSettings(context)
    }

    // --- LIFECYCLE APP LOCK ---
    fun onAppBackgrounded() {
        if (_uiState.value.isSignedIn && _uiState.value.appLockEnabled) {
            lastBackgroundTime = System.currentTimeMillis()
        }
    }

    fun onAppForegrounded(): Boolean {
        // Return true jika harus lock
        if (_uiState.value.isSignedIn && _uiState.value.appLockEnabled) {
            val timeDiff = System.currentTimeMillis() - lastBackgroundTime
            // Jika lebih dari timeout dan lastBackgroundTime sudah diset
            if (lastBackgroundTime != 0L && timeDiff > LOCK_TIMEOUT_MS) {
                _uiState.value = _uiState.value.copy(isSignedIn = false) // Logout paksa
                lastBackgroundTime = 0
                return true
            }
        }
        lastBackgroundTime = 0
        return false
    }
}