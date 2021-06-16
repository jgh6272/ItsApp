package com.example.itsapp.model.vo

data class News(
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<NewsItems>
)
data class NewsItems(
    var title: String = "",
    var originallink: String = "",
    var link: String = "",
    var description: String = "",
    var pubDate: String = ""
)