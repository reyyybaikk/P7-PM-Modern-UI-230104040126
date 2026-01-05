package id.antasari.p7_modern_ui_230104040126

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040126.ui.theme.P7ModernUiTheme

@Composable
fun HomeScreen(
    userName: String = "Muhammad Raihan Azmi",
    isDarkTheme: Boolean = false, // Menentukan latar belakang
    onLogoutClick: () -> Unit = {},
    onOpenSecurityDetailsClick: () -> Unit = {},
    onOpenSettingsClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    // Palet Abu-abu Monyet Terpusat
    val monkeyGreyMain = Color(0xFF4A4A4A)   // Deep Charcoal
    val monkeyGreyLight = Color(0xFF6B6B6B)  // Slate Grey
    val backgroundColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
    val cardSurfaceColor = if (isDarkTheme) Color(0xFF1E293B) else Color.White

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor // Latar belakang tetap putih di light mode
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hi, $userName",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) Color.White else monkeyGreyMain
                    )
                    Text(
                        text = "Your security overview",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        tint = monkeyGreyMain
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Security status card (Revisi: Abu-abu Monyet Gradient)
            SecurityStatusCard(
                monkeyGreyMain = monkeyGreyMain,
                monkeyGreyLight = monkeyGreyLight,
                onOpenDetails = onOpenSecurityDetailsClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Quick actions row
            QuickActionsRow(
                cardColor = cardSurfaceColor,
                accentColor = monkeyGreyLight,
                onOpenSettingsClick = onOpenSettingsClick
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Recent activity
            RecentActivitySection(
                cardColor = cardSurfaceColor,
                accentColor = monkeyGreyLight
            )

            Spacer(modifier = Modifier.weight(1f))

            // Logout button (Revisi: Abu-abu Monyet Outlined style)
            Button(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = monkeyGreyMain,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Filled.Logout, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Log out", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SecurityStatusCard(
    monkeyGreyMain: Color,
    monkeyGreyLight: Color,
    onOpenDetails: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(monkeyGreyLight, monkeyGreyMain)
                    )
                )
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Shield, null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("SecureAuth Status", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("All systems protected", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Biometric login and device encryption are active.",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onOpenDetails,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("View security details", color = monkeyGreyMain, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, null, tint = monkeyGreyMain, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun QuickActionsRow(cardColor: Color, accentColor: Color, onOpenSettingsClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        QuickActionCard("Sign-in", Icons.Filled.VerifiedUser, cardColor, accentColor, Modifier.weight(1f)) { }
        QuickActionCard("Settings", Icons.Filled.Settings, cardColor, accentColor, Modifier.weight(1f), onOpenSettingsClick)
    }
}

@Composable
private fun QuickActionCard(title: String, icon: ImageVector, cardColor: Color, accentColor: Color, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, null, tint = accentColor, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = title, fontWeight = FontWeight.Bold, color = accentColor)
        }
    }
}

@Composable
private fun RecentActivitySection(cardColor: Color, accentColor: Color) {
    Column {
        Text("Recent activity", fontWeight = FontWeight.Bold, color = accentColor)
        Spacer(modifier = Modifier.height(12.dp))
        RecentActivityItem("New login from Chrome", "Just now", cardColor, accentColor)
        Spacer(modifier = Modifier.height(8.dp))
        RecentActivityItem("Password changed", "Yesterday", cardColor, accentColor)
    }
}

@Composable
private fun RecentActivityItem(title: String, time: String, cardColor: Color, accentColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Security, null, tint = accentColor, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = accentColor)
                Text(time, fontSize = 11.sp, color = Color.Gray)
            }
        }
    }
}