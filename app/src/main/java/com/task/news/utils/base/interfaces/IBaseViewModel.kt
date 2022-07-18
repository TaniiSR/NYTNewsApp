package com.task.news.utils.base.interfaces

import com.task.news.utils.base.SingleClickEvent
import com.task.news.utils.base.sealed.Dispatcher
import kotlinx.coroutines.Job

interface IBaseViewModel {
    val clickEvent: SingleClickEvent
    fun onClick(view: android.view.View)
    fun launch(dispatcher: Dispatcher = Dispatcher.Background, block: suspend () -> Unit): Job
}