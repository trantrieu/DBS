package com.dbs.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dbs.base.BaseActivity
import com.dbs.data.article.detail.Detail
import com.dbs.databinding.ActivityDetailBinding
import com.dbs.list.DBSApp
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    companion object {
        const val DETAIL_EXTRA = "DETAIL_EXTRA"
    }

    @Inject
    internal lateinit var detailViewModelFactory: DetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDetailComponent.builder()
            .dBSAppComponent(DBSApp.getApp(this))
            .build()
            .inject(this)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.articleLongText.movementMethod = ScrollingMovementMethod()

        val detail = intent?.run {
            getParcelableExtra<Detail>(DETAIL_EXTRA)
        }
        if (detail == null) {
            finish()
            return
        }

        val viewModel = ViewModelProvider(
            this,
            detailViewModelFactory
        ).get(DetailViewModel::class.java)
        viewModel.setDetail(detail)
        viewModel.textLongLiveData.observe(this, Observer {
            binding.articleLongText.text = it
        })
        viewModel.avatarLiveData.observe(this, Observer {
            Picasso.get().load(it).into(binding.articleAvatar)
        })
        viewModel.titleLiveData.observe(this, Observer {
            supportActionBar?.title = it
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.dbs.base.R.menu.menu, menu)
        return true
    }
}