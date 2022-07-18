package com.task.news.domain.enums

sealed class ArticleDuration(var duration: Int) {
    object OneDay : ArticleDuration(1)
    object Week : ArticleDuration(7)
    object Month : ArticleDuration(30)
}