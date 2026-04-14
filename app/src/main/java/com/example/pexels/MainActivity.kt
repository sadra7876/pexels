package com.example.pexels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.common.loader.loaders.LocalImageLoader
import com.example.common.loader.loaders.PexelImageLoaderProvider
import com.example.core.datastore.di.DataStoreProvider
import com.example.core.navigation.navHost.NavHostContent
import com.example.pexels.ui.theme.PexelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val imageLoaderProvider = PexelImageLoaderProvider(this)
        val appSetting = DataStoreProvider.provideAppSetting(this)
        setContent {
            CompositionLocalProvider(
                LocalImageLoader provides imageLoaderProvider
            ) {
                val isDark by appSetting
                    .getDarkMode()
                    .collectAsState(initial = false)

                PexelsTheme(
                    darkTheme = isDark
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHostContent(
                            modifier = Modifier.padding(innerPadding),
                            navController = rememberNavController()
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PexelsTheme {
        Greeting("Android")
    }
}