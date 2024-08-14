package com.kaaneneskpc.geminiaichatbot

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaaneneskpc.geminiaichatbot.presentation.chat.ChatScreen
import com.kaaneneskpc.geminiaichatbot.ui.theme.GeminiAIChatbotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val uriState = MutableStateFlow("")
    private val imagePicker =
        registerForActivityResult<PickVisualMediaRequest, Uri?>(ActivityResultContracts.PickVisualMedia()) {
            it?.let { uri ->
                uriState.update { uri.toString() }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiAIChatbotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary)
                                    .height(35.dp)
                                    .windowInsetsPadding(
                                        WindowInsets.systemBars.only(
                                            WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                                        )
                                    )
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.TopStart),
                                    text = "Gemini AI Chatbot",
                                    fontSize = 19.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    ) {
                        ChatScreen(imagePicker, uriState, paddingValues = it)
                    }
                }
            }
        }
    }
}