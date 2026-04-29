package io.github.elmer.movish

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CabinFont = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_bold, FontWeight.Bold)
)

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. Logo de la App
        Image(
            painter = painterResource(id = R.drawable.logo_main_purplewhite_dark),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )

        // 2. Nombre de la App con tipografía Cabin
        Text(
            text = "MOVISH",
            fontFamily = CabinFont,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            letterSpacing = 4.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp, bottom = 64.dp)
        )

        MainActionButton(text = "Sign Up") {
            /* Acción Demo */
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Texto de Login
        Row {
            Text(text = "I already have an account? ", color = Color.Gray)
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        // 5. Divisor "Or sign up with"
        OrDivider()

        Spacer(modifier = Modifier.height(32.dp))

        // 6. Botones de Login Externo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialLoginButton(R.drawable.ic_google_logo)
            SocialLoginButton(R.drawable.ic_apple_logo)
            SocialLoginButton(R.drawable.ic_facebook_logo)
        }
    }
}

@Composable
fun MainActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Normal)
    }
}

@Composable
fun OrDivider() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = DividerDefaults.Thickness,
            color = Color.DarkGray
        )
        Text(
            text = "Or sign up with",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.Gray,
            fontSize = 14.sp
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = DividerDefaults.Thickness,
            color = Color.DarkGray
        )
    }
}

@Composable
fun SocialLoginButton(iconRes: Int) {

    Surface(
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        color = Color.Transparent
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}