package com.dbs.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dbs.article.ArticleListResult
import com.dbs.article.ArticleProvider
import com.dbs.config.SchedulerConfig
import com.dbs.data.SingleEvent
import com.dbs.data.article.list.Article
import com.dbs.list.adapter.ArticleModel
import com.dbs.list.adapter.ArticlesModelConvert
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

internal class ListArticleViewModel constructor(
    private val articleProvider: ArticleProvider,
    private val schedulerConfig: SchedulerConfig,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val articleMutableLiveData = MutableLiveData<List<ArticleModel>>()
    private val errorMutableLiveData = MutableLiveData<SingleEvent<String>>()
    private val loadingSpinnerMutableLiveData = MutableLiveData<SingleEvent<Boolean>>()

    val articleLiveData: LiveData<List<ArticleModel>> = articleMutableLiveData
    val errorErrorLiveData: LiveData<SingleEvent<String>> = errorMutableLiveData
    val loadingSpinnerLiveData: LiveData<SingleEvent<Boolean>> = loadingSpinnerMutableLiveData

    fun fetchArticleList() {
        val fetch = articleProvider
            .fetchListArticle()
            .doOnSubscribe { loadingSpinnerMutableLiveData.postValue(SingleEvent(true)) }
            .doOnTerminate { loadingSpinnerMutableLiveData.postValue(SingleEvent(false)) }
            .observeOn(schedulerConfig.getMainScheduler())
            .subscribe({
                when (it) {
                    is ArticleListResult.Success -> articleMutableLiveData.value =
                        ArticlesModelConvert.convertViewModel(it.articleList)
                    is ArticleListResult.Failure -> errorMutableLiveData.value =
                        SingleEvent(it.message)
                }
            }, {
                val msg = it.message ?: "Generic error"
                errorMutableLiveData.value = SingleEvent(msg)
            })
        compositeDisposable.add(fetch)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}