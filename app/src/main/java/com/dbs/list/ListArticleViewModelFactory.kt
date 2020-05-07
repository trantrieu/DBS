package com.dbs.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbs.DBSApp
import com.dbs.MainScope
import com.dbs.article.ArticleProvider
import com.dbs.config.SchedulerConfig
import com.dbs.detail.DetailProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@MainScope
internal class ListArticleViewModelFactory @Inject constructor(
    private val articleProvider: ArticleProvider,
    private val detailProvider: DetailProvider,
    private val schedulerConfig: SchedulerConfig,
    private val context: Context,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListArticleViewModel(articleProvider, detailProvider, schedulerConfig, context.applicationContext as DBSApp, compositeDisposable) as T
    }

}