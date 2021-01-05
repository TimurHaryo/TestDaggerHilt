package id.train.hilter2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.train.hilter2.R
import id.train.hilter2.base.BaseActivity
import id.train.hilter2.model.Blog
import id.train.hilter2.util.DataState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<List<Blog>>() {
    override val TAG: String
        get() = this.javaClass.simpleName

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObserver()
        mainViewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObserver() {
        mainViewModel.dataState.observe(this, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Blog>> -> {
                    onLoading(false)
                    onSuccess(dataState.data)
                }
                is DataState.Error -> {
                    onLoading(false)
                    onError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    onLoading(true)
                }
            }
        })
    }

    override fun onSuccess(data: List<Blog>) {
        val stringBuilder = StringBuilder()
        for (blog in data) {
            stringBuilder.append(blog.title + "\n\n")
        }
        tvContainer.text = stringBuilder.toString()
    }

    override fun onError(message: String?) {
        if (message != null) {
            tvContainer.text = message
        } else {
            tvContainer.text = getString(R.string.unknown_error)
        }
    }

    override fun onLoading(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}