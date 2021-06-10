package com.example.itsapp.model.vo

data class Blog (
    var lastBuildDate: String = "",
    var postdate : String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<BlogItems>
)
data class BlogItems(
    var title: String = "",
    var link: String = "",
    var description: String = "",
    var bloggername: String = "",
    var bloggerlink: String = ""
)
