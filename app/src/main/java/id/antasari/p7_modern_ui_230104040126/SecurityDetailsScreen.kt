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
fun SecurityDetailsScreen(
    userName: String = "reyyybaikk",
    isDarkTheme: Boolean = true, // Tambahkan untuk sinkronisasi tema
    onBackClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    // Palet Abu-abu Monyet
    val monkeyGreyMain = Color(0xFF4A4A4A)
    val monkeyGreyLight = Color(0xFF6B6B6B)
    val backgroundColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
    val surfaceColor = if (isDarkTheme) Color(0xFF1E293B) else Color.White
    val textColor = if (isDarkTheme) Color.White else Color(0xFF1E293B)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Top bar with back
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(surfaceColor, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Security Details",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = "Account: $userName",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Security,
                    contentDescription = null,
                    tint = monkeyGreyLight
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Overall security score card (Revisi: Abu-abu Monyet Gradient)
            OverallSecurityScoreCard(monkeyGreyMain, monkeyGreyLight)

            Spacer(modifier = Modifier.height(16.dp))

            // Status badges row
            SecurityStatusChipsRow(monkeyGreyLight)

            Spacer(modifier = Modifier.height(24.dp))

            // Category cards
            SecurityCategoryCard(
                title = "Sign-in & passwords",
                description = "No weak passwords detected. Biometric login is enabled.",
                icon = Icons.Filled.Lock,
                highlightText = "Secure",
                highlightColor = monkeyGreyLight,
                surfaceColor = surfaceColor,
                textColor = textColor
            )
            Spacer(modifier = Modifier.height(12.dp))

            SecurityCategoryCard(
                title = "Devices & sessions",
                description = "3 active devices. All verified in the last 30 days.",
                icon = Icons.Filled.Devices,
                highlightText = "All verified",
                highlightColor = monkeyGreyLight,
                surfaceColor = surfaceColor,
                textColor = textColor
            )
            Spacer(modifier = Modifier.height(12.dp))

            SecurityCategoryCard(
                title = "Alerts & notifications",
                description = "Login alerts and warnings are currently active.",
                icon = Icons.Filled.Notifications,
                highlightText = "Protected",
                highlightColor = monkeyGreyLight,
                surfaceColor = surfaceColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Recommendation section
            RecommendationsSection(surfaceColor, monkeyGreyMain, textColor)

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun OverallSecurityScoreCard(mainColor: Color, lightColor: Color) {
    Card(
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(lightColor, mainColor)
                    )
                )
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Shield,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = "Overall Security",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "Score 92 / 100",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Status: Very Strong",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun SecurityStatusChipsRow(accentColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("3 Categories Good", "0 Attention", "0 Critical").forEach { label ->
            Surface(
                color = accentColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, accentColor.copy(alpha = 0.2f))
            ) {
                Text(
                    text = label,
                    color = accentColor,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun SecurityCategoryCard(
    title: String,
    description: String,
    icon: ImageVector,
    highlightText: String,
    highlightColor: Color,
    surfaceColor: Color,
    textColor: Color
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(highlightColor.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = highlightColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, color = textColor, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(highlightText, color = highlightColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun RecommendationsSection(surfaceColor: Color, btnColor: Color, textColor: Color) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Recommendations", fontWeight = FontWeight.Bold, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Review your devices regularly and avoid reusing passwords across different platforms.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Text("View Best Practices", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SecurityDetailsPreview() {
    P7ModernUiTheme {
        SecurityDetailsScreen()
    }
}