package id.train.hilter2.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<in R> : AppCompatActivity() {
    protected abstract val TAG: String

    abstract fun onSuccess(data: R)
    abstract fun onError(message: String?)
    abstract fun onLoading(isDisplayed: Boolean)
}