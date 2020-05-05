package com.dbs.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbs.DBSApp
import com.dbs.base.BaseActivity
import com.dbs.databinding.ActivityArticleListBinding
import com.dbs.detail.DetailActivity
import com.dbs.list.adapter.ArticlesAdapter
import javax.inject.Inject

class ListArticleActivity : BaseActivity() {

    @Inject
    internal lateinit var listArticleViewModelFactory: ListArticleViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerListArticleComponent
            .builder()
            .dBSAppComponent(DBSApp.getApp(this))
            .build()
            .inject(this)

        val viewBinding = ActivityArticleListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        val viewModel = ViewModelProvider(this, listArticleViewModelFactory)
            .get(ListArticleViewModel::class.java)
        val adapter =
            ArticlesAdapter(onItemClickListener = object : ArticlesAdapter.OnItemClickListener {
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
        viewModel.loadingSpinnerLiveData.observe(this, Observer {
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
        viewModel.navigateDetailLiveData.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()?.let { detail ->
                    DetailActivity.startActivity(this, detail)
                }
            }
        })

        viewModel.fetchArticleList()
    }

}