package id.antasari.p7_modern_ui_230104040126

import android.content.Context
import androidx.biometric.BiometricManager

object BiometricUtils {
    fun isBiometricReady(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK
        return biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
    }
}