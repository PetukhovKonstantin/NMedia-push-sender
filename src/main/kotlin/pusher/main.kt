package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val token =
        "crYZkKYCR_ayEFuKmIW0hX:APA91bFdXhCxkWJ5Ve8nWOpHPh4ViYV8zFaQV50jDAKzdEidAkTjq2jAh2-eSBI17eJ0TY-P7o0Gjudn10Mipv1Ba3UhMBnlIzbl_tXa3pm5UZEUxE4mPKGfEIF8zHmg1bIMrG3VBdrd"
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNewPost = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "authorId": 1,
          "authorName": "Konstantin",
          "content": "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageNewPost)
}