package com.dbs.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbs.MainScope
import com.dbs.article.ArticleProvider
import com.dbs.config.SchedulerConfig
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@MainScope
internal class ListArticleViewModelFactory @Inject constructor(
    private val articleProvider: ArticleProvider,
    private val schedulerConfig: SchedulerConfig,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListArticleViewModel(articleProvider, schedulerConfig, compositeDisposable) as T
    }

}