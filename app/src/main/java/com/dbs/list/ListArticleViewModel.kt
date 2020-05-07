package com.dbs.list

import android.content.Context
import androidx.lifecycle.*
import com.dbs.DBSApp
import com.dbs.article.ArticleListResult
import com.dbs.article.ArticleProvider
import com.dbs.base.R
import com.dbs.base.nonNull
import com.dbs.config.SchedulerConfig
import com.dbs.data.SingleEvent
import com.dbs.data.article.detail.Detail
import com.dbs.data.article.list.Article
import com.dbs.detail.DetailProvider
import com.dbs.detail.DetailResult
import com.dbs.list.adapter.ArticleModel
import com.dbs.list.adapter.ArticlesModelConvert
import io.reactivex.disposables.CompositeDisposable

internal class ListArticleViewModel constructor(
    private val articleProvider: ArticleProvider,
    private val detailProvider: DetailProvider,
    private val schedulerConfig: SchedulerConfig,
    private val dbsApp: DBSApp,
    private val compositeDisposable: CompositeDisposable
) : AndroidViewModel(dbsApp) {

    private val articleMutableLiveData = MutableLiveData<List<Article>>()
    private val errorMutableLiveData = MutableLiveData<SingleEvent<String>>()
    private val loadingSpinnerMutableLiveData = MutableLiveData<SingleEvent<Boolean>>()
    private val navigateDetailMutableLiveData = MutableLiveData<SingleEvent<Detail>>()

    val articleLiveData: LiveData<List<ArticleModel>> =
        Transformations.map(articleMutableLiveData.nonNull()) {
            ArticlesModelConvert.convertViewModel(it)
        }
    val errorErrorLiveData: LiveData<SingleEvent<String>> = errorMutableLiveData.nonNull()
    val loadingSpinnerLiveData: LiveData<SingleEvent<Boolean>> =
        loadingSpinnerMutableLiveData.nonNull()
    val navigateDetailLiveData: LiveData<SingleEvent<Detail>> =
        navigateDetailMutableLiveData.nonNull()

    fun fetchArticleList() {
        val fetch = articleProvider
            .fetchListArticle()
            .doOnSubscribe { loadingSpinnerMutableLiveData.postValue(SingleEvent(true)) }
            .doOnTerminate { loadingSpinnerMutableLiveData.postValue(SingleEvent(false)) }
            .observeOn(schedulerConfig.getMainScheduler())
            .subscribe({
                when (it) {
                    is ArticleListResult.Success -> articleMutableLiveData.value = it.articleList
                    is ArticleListResult.Failure -> errorMutableLiveData.value =
                        SingleEvent(it.message ?: dbsApp.getString(R.string.generic_failure))
                }
            }, {
                val msg = it.message ?: dbsApp.getString(R.string.generic_failure)
                errorMutableLiveData.value = SingleEvent(msg)
            })
        compositeDisposable.add(fetch)
    }

    fun fetchDetail(id: Int) {
        val fetch = detailProvider
            .fetchDetailWithArticle(id, articleMutableLiveData.value!!)
            .doOnSubscribe { loadingSpinnerMutableLiveData.postValue(SingleEvent(true)) }
            .doOnTerminate { loadingSpinnerMutableLiveData.postValue(SingleEvent(false)) }
            .observeOn(schedulerConfig.getMainScheduler())
            .subscribe({
                when (it) {
                    is DetailResult.Success -> navigateDetailMutableLiveData.value =
                        SingleEvent(it.detail)
                    is DetailResult.Failure -> errorMutableLiveData.value =
                        SingleEvent(it.message ?: dbsApp.getString(R.string.generic_failure))
                }
            }, {
                val msg = it.message ?: dbsApp.getString(R.string.generic_failure)
                errorMutableLiveData.value = SingleEvent(msg)
            })
        compositeDisposable.add(fetch)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}