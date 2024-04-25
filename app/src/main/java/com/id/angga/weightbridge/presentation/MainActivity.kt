package com.id.angga.weightbridge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.rememberNavController
import com.id.angga.weightbridge.presentation.navigation.NavGraph
import com.id.angga.weightbridge.presentation.utils.ChangeSystemBarsTheme
import com.id.angga.weightbridge.ui.theme.WeighbridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeighbridgeTheme {
                ChangeSystemBarsTheme(lightTheme = !isSystemInDarkTheme())
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
