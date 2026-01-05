package id.antasari.p7_modern_ui_230104040126

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040126.ui.auth.AuthUiState
import id.antasari.p7_modern_ui_230104040126.ui.theme.P7ModernUiTheme

@Composable
fun SettingsScreen(
    uiState: AuthUiState,
    onBackClick: () -> Unit = {},
    onBiometricToggle: (Boolean) -> Unit = {},
    onDarkThemeToggle: (Boolean) -> Unit = {},
    onAppLockToggle: (Boolean) -> Unit = {},
    onLoginAlertsToggle: (Boolean) -> Unit = {},
    onNewDeviceAlertsToggle: (Boolean) -> Unit = {},
    onWifiWarningToggle: (Boolean) -> Unit = {},
    onDeleteAccountClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    // Warna Abu-abu Monyet Terpusat
    val monkeyGreyMain = Color(0xFF4A4A4A) // Deep Charcoal
    val monkeyGreyLight = Color(0xFF6B6B6B) // Slate Grey
    val backgroundColor = if (uiState.isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
    val cardColor = if (uiState.isDarkTheme) Color(0xFF1E293B) else Color.White
    val textColor = if (uiState.isDarkTheme) Color.White else Color(0xFF1E293B)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // --- Top bar ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(cardColor, CircleShape)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = textColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Security Settings",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = "Manage your protection, ${uiState.name}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Helper function untuk Section Header
            @Composable
            fun SectionHeader(title: String) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = monkeyGreyLight,
                    modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
                )
            }

            // --- Section: Account & sign-in ---
            SectionHeader("Account & Security")
            SettingsGroupCard(cardColor) {
                SettingsSwitchItem(
                    title = "Biometric Sign-in",
                    description = "Use fingerprint or face recognition",
                    icon = Icons.Filled.Fingerprint,
                    checked = uiState.isBiometricEnabled,
                    accentColor = monkeyGreyLight,
                    textColor = textColor,
                    onCheckedChange = onBiometricToggle
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = backgroundColor)
                SettingsSwitchItem(
                    title = "App Lock",
                    description = "Lock app when inactive",
                    icon = Icons.Filled.Lock,
                    checked = uiState.appLockEnabled,
                    accentColor = monkeyGreyLight,
                    textColor = textColor,
                    onCheckedChange = onAppLockToggle
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Section: Appearance ---
            SectionHeader("Appearance")
            SettingsGroupCard(cardColor) {
                SettingsSwitchItem(
                    title = "Dark Theme",
                    description = "Modern charcoal interface",
                    icon = Icons.Filled.DarkMode,
                    checked = uiState.isDarkTheme,
                    accentColor = monkeyGreyLight,
                    textColor = textColor,
                    onCheckedChange = onDarkThemeToggle
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Section: Alerts ---
            SectionHeader("Alerts")
            SettingsGroupCard(cardColor) {
                SettingsSwitchItem(
                    title = "Login Alerts",
                    description = "Notify on account access",
                    icon = Icons.Filled.Notifications,
                    checked = uiState.loginAlertsEnabled,
                    accentColor = monkeyGreyLight,
                    textColor = textColor,
                    onCheckedChange = onLoginAlertsToggle
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = backgroundColor)
                SettingsSwitchItem(
                    title = "Device Alerts",
                    description = "Notify on new device login",
                    icon = Icons.Filled.Devices,
                    checked = uiState.newDeviceAlertsEnabled,
                    accentColor = monkeyGreyLight,
                    textColor = textColor,
                    onCheckedChange = onNewDeviceAlertsToggle
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- DELETE ACCOUNT BUTTON (Disesuaikan agar tidak terlalu mencolok) ---
            OutlinedButton(
                onClick = onDeleteAccountClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFCA5A5)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFEF4444))
            ) {
                Icon(Icons.Filled.Delete, null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete Account", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun SettingsGroupCard(
    containerColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth(),
        content = content
    )
}

@Composable
private fun SettingsSwitchItem(
    title: String,
    description: String,
    icon: ImageVector,
    checked: Boolean,
    accentColor: Color,
    textColor: Color,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(accentColor.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                lineHeight = 16.sp
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = accentColor,
                checkedThumbColor = Color.White,
                uncheckedTrackColor = accentColor.copy(alpha = 0.2f),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}