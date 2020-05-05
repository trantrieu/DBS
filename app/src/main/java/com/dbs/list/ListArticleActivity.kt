package com.dbs.list

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbs.base.BaseActivity
import com.dbs.databinding.ActivityArticleListBinding
import com.dbs.detail.DetailActivity
import com.dbs.list.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_article_list.*
import javax.inject.Inject

class ListArticleActivity : BaseActivity() {

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
        setSupportActionBar(viewBinding.toolbar)

        val viewModel = ViewModelProvider(
            this,
            listArticleViewModelFactory
        ).get(ListArticleViewModel::class.java)
        val adapter = ArticlesAdapter(onItemClickListener = object: ArticlesAdapter.OnItemClickListener {
            override fun onItemClickListener(id: Int) {
                viewModel.fetchDetail(id)
            }
        })
        val layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerViewItems.layoutManager = layoutManager
        viewBinding.recyclerViewItems.adapter = adapter
        viewBinding.recyclerViewItems.setHasFixedSize(true)

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
        viewModel.navigateDetailLiveData.observe(this, Observer { it ->
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()?.let {detail ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.DETAIL_EXTRA, detail)
                    startActivity(intent)
                }
            }
        })

        viewModel.fetchArticleList()
    }

}