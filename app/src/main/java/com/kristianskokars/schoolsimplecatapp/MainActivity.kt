package com.kristianskokars.schoolsimplecatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kristianskokars.schoolsimplecatapp.data.repository.CatRepositoryImpl
import com.kristianskokars.schoolsimplecatapp.feature.cat_list.CatListScreen
import com.kristianskokars.schoolsimplecatapp.feature.cat_list.CatListViewModel
import com.kristianskokars.schoolsimplecatapp.ui.components.BackgroundSurface
import com.kristianskokars.schoolsimplecatapp.ui.theme.SchoolSimpleCatAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BackgroundSurface {
                CatListScreen()
            }
        }
    }
}
