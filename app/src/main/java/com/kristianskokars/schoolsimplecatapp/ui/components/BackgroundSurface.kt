package com.kristianskokars.schoolsimplecatapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kristianskokars.schoolsimplecatapp.ui.theme.SchoolSimpleCatAppTheme

@Composable
fun BackgroundSurface(block: @Composable () -> Unit) {
    SchoolSimpleCatAppTheme {
        Surface(
           modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            block()
        }
    }
}
