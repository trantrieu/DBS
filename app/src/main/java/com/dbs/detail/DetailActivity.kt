package com.dbs.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dbs.base.BaseActivity
import com.dbs.data.article.detail.Detail
import com.dbs.databinding.ActivityDetailBinding
import com.dbs.DBSApp
import com.squareup.picasso.Picasso
import javax.inject.Inject
import com.dbs.base.R
import com.dbs.edit.EditActivity

class DetailActivity : BaseActivity() {

    companion object {
        internal const val DETAIL_EXTRA = "DETAIL_EXTRA"
        internal const val REQUEST_EDIT_CODE = 1000

        fun startActivity(activity: Activity, detail: Detail) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DETAIL_EXTRA, detail)
            activity.startActivity(intent)
        }
    }

    @Inject
    internal lateinit var detailViewModelFactory: DetailViewModelFactory

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detail = intent?.run {
            getParcelableExtra<Detail>(DETAIL_EXTRA)
        }
        if (detail == null) {
            finish()
            return
        }

        DaggerDetailComponent.builder()
            .dBSAppComponent(DBSApp.getApp(this))
            .build()
            .inject(this)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.articleLongText.movementMethod = ScrollingMovementMethod()

        viewModel = ViewModelProvider(
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
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_edit -> {
                EditActivity.startActivity(this, viewModel.getDetail(), REQUEST_EDIT_CODE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_EDIT_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val detail = data.getParcelableExtra<Detail>(DETAIL_EXTRA)
            viewModel.setDetail(detail)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}