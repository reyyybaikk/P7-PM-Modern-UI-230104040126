package id.antasari.p7_modern_ui_230104040126

import androidx.compose.foundation.BorderStroke // Import yang benar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040126.ui.auth.AuthUiState
import id.antasari.p7_modern_ui_230104040126.ui.theme.P7ModernUiTheme

@Composable
fun LoginScreen(
    uiState: AuthUiState = AuthUiState(), // Default value for preview
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onSignInClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onBiometricClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top bar (refresh icon - placeholder)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: refresh */ }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // --- APP ICON DENGAN GRADIENT ---
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF6B6B6B), // Abu-abu Monyet Terang (Warm Slate)
                                Color(0xFF3D3D3D)  // Abu-abu Monyet Gelap (Deep Charcoal)
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Fingerprint,
                    contentDescription = "Logo",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "SecureAuth",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Sign in to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- CARD FORM LOGIN ---
            ElevatedCard(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Input Email
                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = onEmailChange,
                        label = { Text("Email address") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Input Password
                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = onPasswordChange,
                        label = { Text("Password") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password")
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Forgot Password
                    Text(
                        text = "Forgot password?",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4B6BFB), // Primary Blue
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { /* TODO */ }
                    )

                    // Error Message Display
                    if (uiState.lastErrorMessage != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.lastErrorMessage!!, // Force unwrap safe here or use default
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tombol Sign In (Full Width, Pill Shape)
                    Button(
                        onClick = onSignInClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp), // Ditingkatkan sedikit agar lebih elegan dan mudah ditekan
                        shape = RoundedCornerShape(50), // Tetap mempertahankan bentuk pill
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4A4A4A), // Abu-abu Monyet (Deep Charcoal)
                            contentColor = Color.White          // Teks tetap putih agar kontras
                        )
                    ) {
                        Text(text = "Sign In", fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Or sign in with",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tombol Fingerprint Bulat
                    IconButton(
                        onClick = onBiometricClick,
                        enabled = uiState.isBiometricAvailable && uiState.isBiometricEnabled,
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                color = if (uiState.isBiometricEnabled) Color(0xFF4B6BFB).copy(alpha = 0.1f) else Color.LightGray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Fingerprint,
                            contentDescription = "Biometric",
                            tint = if (uiState.isBiometricEnabled) Color(0xFF4B6BFB) else Color.Gray,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Divider OR
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
                        Text(
                            text = "OR",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tombol Create Account (Outline)
                    OutlinedButton(
                        onClick = onCreateAccountClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(50),
                        // PERBAIKAN DI SINI: BorderStroke yang benar
                        border = BorderStroke(1.dp, Color(0xFF4A4A4A))
                    ) {
                        Text(text = "Create Account", fontWeight = FontWeight.SemiBold, color = Color(0xFF4A4A4A))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- INFO CARDS ---
            InfoCard(
                title = "Secure & Private",
                description = "Your data is encrypted end-to-end with industry-standard security protocols.",
                iconBackground = Color(0xFFE4FBF1), // Hijau muda
                iconTint = Color(0xFF10B981)       // Hijau tua
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(
                title = "Biometric Authentication",
                description = "Use fingerprint or face recognition for quick and secure access.",
                iconBackground = Color(0xFFEDE9FE), // Ungu muda
                iconTint = Color(0xFF7C3AED)       // Ungu tua
            )

            Spacer(modifier = Modifier.height(24.dp))

            TermsAndPrivacyText()
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    description: String,
    iconBackground: Color,
    iconTint: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.White, // Pastikan putih agar clean
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(iconBackground, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Fingerprint, // Icon generik, bisa diganti shield dll
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun TermsAndPrivacyText() {
    val annotated = buildAnnotatedString {
        append("By continuing, you agree to our ")
        pushStringAnnotation(tag = "terms", annotation = "terms")
        withStyle(style = SpanStyle(color = Color(0xFF4B6BFB), textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Medium)) {
            append("Terms of Service")
        }
        pop()
        append(" and ")
        pushStringAnnotation(tag = "privacy", annotation = "privacy")
        withStyle(style = SpanStyle(color = Color(0xFF4B6BFB), textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Medium)) {
            append("Privacy Policy")
        }
        pop()
    }

    ClickableText(
        text = annotated,
        style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center, color = Color.Gray, lineHeight = 16.sp),
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    P7ModernUiTheme {
        LoginScreen()
    }
}