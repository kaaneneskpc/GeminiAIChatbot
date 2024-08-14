package com.kaaneneskpc.geminiaichatbot.presentation.chat

import android.graphics.Bitmap
import com.kaaneneskpc.geminiaichatbot.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null,
)