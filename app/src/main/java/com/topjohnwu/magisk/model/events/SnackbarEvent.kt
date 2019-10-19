package com.topjohnwu.magisk.model.events

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.topjohnwu.magisk.base.BaseActivity
import com.topjohnwu.magisk.extensions.snackbar

class SnackbarEvent private constructor(
    @StringRes private val messageRes: Int,
    private val messageString: String?,
    val length: Int,
    val f: Snackbar.() -> Unit
) : ViewEvent(), ActivityExecutor {

    constructor(
        @StringRes messageRes: Int,
        length: Int = Snackbar.LENGTH_SHORT,
        f: Snackbar.() -> Unit = {}
    ) : this(messageRes, null, length, f)

    constructor(
        message: String,
        length: Int = Snackbar.LENGTH_SHORT,
        f: Snackbar.() -> Unit = {}
    ) : this(-1, message, length, f)

    fun message(context: Context): String = messageString ?: context.getString(messageRes)

    override fun invoke(activity: AppCompatActivity) {
        if (activity is BaseActivity<*, *>) {
            activity.snackbar(activity.snackbarView, message(activity), length, f)
        }
    }

}
