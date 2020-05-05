package com.dbs.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbs.base.BaseActivity
import com.dbs.databinding.ActivityArticleListBinding
import com.dbs.list.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_article_list.*
import javax.inject.Inject

class ListArticleActivity : BaseActivity() {

    private val adapter = ArticlesAdapter()

    @Inject
    internal lateinit var listArticleViewModelFactory: ListArticleViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerListArticleComponent.builder()
            .dBSAppComponent(DBSApp.getApp(this))
            .build()
            .inject(this)

        val viewBinding = ActivityArticleListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewItems.layoutManager = layoutManager
        recyclerViewItems.adapter = adapter
        recyclerViewItems.setHasFixedSize(true)

        val viewModel = ViewModelProvider(
            this,
            listArticleViewModelFactory
        ).get(ListArticleViewModel::class.java)
        viewModel.articleLiveData.observe(this, Observer {
            adapter.update(it)
        })
        viewModel.errorErrorLiveData.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()?.let { error ->
                    showPopup(error)
                }
            }
        })
        viewModel.loadingSpinnerLiveData.observe(this, Observer { it ->
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()?.let { show ->
                    if (show) {
                        showLoadingDialogSpinner()
                    } else {
                        hideLoadingDialogSpinner()
                    }
                }
            }
        })

        viewModel.fetchArticleList()
    }

}