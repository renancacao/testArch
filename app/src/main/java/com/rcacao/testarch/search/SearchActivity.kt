package com.rcacao.testarch.search

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.rcacao.testarch.R
import kotlinx.android.synthetic.main.activity_main.*


class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btSearch.setOnClickListener { searchCode() }
        subscribe()
    }

    private fun searchCode() {
        viewModel.searchCode(edCode.text.toString())
    }

    private fun subscribe() {
        viewModel.state.observe(this, Observer<SearchState> {
            when (it) {
                is SearchState.Success -> showSuccessMessage(it.message)
                is SearchState.Error -> showErrorMessage(it.message)
                is SearchState.Loading -> showLoading(true)
                is SearchState.InvalidCode -> showInvalidCodeError()
            }
        })
    }

    private fun showInvalidCodeError() {
        Snackbar.make(root,getString(R.string.invalid_code),Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccessMessage(message: String) {
        showLoading(false)
        txtMessage.setTextColor(Color.BLUE)
        txtMessage.text = message
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            cleanMessage()
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

        btSearch.isEnabled = !show
        edCode.isEnabled = !show

    }

    private fun showErrorMessage(message: String) {
        showLoading(false)
        txtMessage.setTextColor(Color.RED)
        txtMessage.text = message
    }

    private fun cleanMessage() {
        txtMessage.text = ""
    }
}
