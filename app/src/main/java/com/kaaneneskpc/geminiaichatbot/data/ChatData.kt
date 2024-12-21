package com.kaaneneskpc.geminiaichatbot.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {

    suspend fun getResponse(prompt: String, bitmap: Bitmap? = null): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-2.0-flash-exp", apiKey = BuildConfig.API_KEY
        )

        try {

            val inputContent = content {
                bitmap?.let { image(it) }
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }

}