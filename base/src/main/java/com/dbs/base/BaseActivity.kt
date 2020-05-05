package com.dbs.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private var dialog: AlertDialog? = null
    private var dialogSpinner: AlertDialog? = null

    protected fun showPopup(message: String) {
        dialog?.dismiss()
        dialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
        dialog?.show()
    }

    protected fun showLoadingDialogSpinner() {
        dialogSpinner?.dismiss()
        dialogSpinner = AlertDialog.Builder(this)
            .setView(R.layout.dialog_progress)
            .setTitle(R.string.loading)
            .setCancelable(false)
            .create()
        dialogSpinner?.show()
    }

    protected fun hideLoadingDialogSpinner() {
        dialogSpinner?.dismiss()
    }
}