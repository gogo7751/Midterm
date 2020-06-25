package com.eric.publisher

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eric.publisher.data.Article
import com.eric.publisher.data.TimeUtil
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainViewModel: ViewModel() {

    val allArticle = MutableLiveData<List<Article>>()

    val author = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val createdTime = MutableLiveData<Long>()
    val id = MutableLiveData<String>()

    val articles = FirebaseFirestore.getInstance()
        .collection("articles")


    fun addData() {
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "tag" to "Beauty"
        )
        document.set(data)
    }


    fun getData(){
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        articles.get()
            .addOnSuccessListener { result ->
                val listArticle = mutableListOf<Article>()
                for (document in result) {
                    val article = Article(
                        author = document.getString("author.name"),
                        content = document.getString("content"),
                        createdTime = TimeUtil.StampToDate(document.getLong("createdTime") as Long),
                        title = document.getString("title"),
                        id = document.id,
                        tag = document.getString("tag")
                    )
                    listArticle.add(article)
//                    author.value = document.getString("author.name")
//                    title.value = document.getString("title")
//                    content.value = document.getString("content")
//                    createdTime.value = document.getLong("createdTime")
//                    tag.value = document.getString("tag")
//                    id.value = document.id
//                    Log.d("read" , "id:${id.value}" + "author:${author.value} + title:${title.value} + content:${content.value} + createdTime:$createdTime + tag:${tag.value}")
                }
                allArticle.value = listArticle
                Log.d("read", "${allArticle.value}")

            }
    }



    init {
        getData()
    }


}