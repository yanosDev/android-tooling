package de.yanosdev.tooling

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // On versions < SDK 30 Modifier.navigationBarsPadding() always returns 0.dp, which
            // makes it incompatible for edge to edge.
            enableEdgeToEdge()
        }
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}