package com.rocketseat.nlw.nearby.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.ui.theme.GreenLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateTo: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        delay(3_000)
        onNavigateTo()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreenLight)
    ) {
        Image(
            modifier = modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_logo_logo_logo_text),
            contentDescription = "Imagem Logo"
        )
        Image(
            modifier = modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.bg_splash_screen),
            contentDescription = "Imagem background"
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        onNavigateTo = {}
    )
}