package com.dbs.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.dbs.base.BaseActivity
import com.dbs.base.R
import com.dbs.data.article.detail.Detail
import com.dbs.databinding.ActivityEditBinding
import com.dbs.detail.DetailActivity.Companion.DETAIL_EXTRA

class EditActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, detail: Detail, code: Int) {
            val intent = Intent(activity, EditActivity::class.java)
            intent.putExtra(DETAIL_EXTRA, detail)
            activity.startActivityForResult(intent, code)
        }
    }

    private lateinit var viewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detail = intent.run {
            getParcelableExtra<Detail>(DETAIL_EXTRA)
        }
        if (detail == null) {
            finish()
            return
        }

        val binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = detail.article!!.title
        binding.saveButton.setOnClickListener {
            handleSave(binding)
        }
        binding.editTextDetail.setText(detail.text)

        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        viewModel.detail = detail
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cancel_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home, R.id.action_cancel -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleSave(binding: ActivityEditBinding) {
        val text = binding.editTextDetail.text.toString()
        viewModel.updateText(text)
        val data = Intent()
        data.putExtra(DETAIL_EXTRA, viewModel.detail)
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}