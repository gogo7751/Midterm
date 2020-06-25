package com.eric.publisher.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class PostViewModel: ViewModel() {

    val title = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    private val articles = FirebaseFirestore.getInstance()
        .collection("articles")

    fun addData() {
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to title.value,
            "content" to content.value,
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "tag" to tag.value
        )
        document.set(data)
    }
}