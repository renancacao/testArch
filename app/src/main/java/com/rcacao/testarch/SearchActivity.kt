package com.rcacao.testarch

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.ViewModelProviders



class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        btSearch.setOnClickListener { viewModel.searchCode(edCode.text.toString()) }
        subscribe()
    }

    private fun subscribe() {
        viewModel.state.observe(this, Observer<SearchState> {
            when (it) {
                is SearchState.Success -> showSuccessMessage(it.message)
                is SearchState.Error -> showErrorMessage(it.message)
                is SearchState.Loading -> showLoading(true)
                is SearchState.Clean -> cleanMessage()
            }
        })
    }

    private fun showSuccessMessage(message: String) {
        showLoading(false)
        txtMessage.setTextColor(Color.BLUE)
        txtMessage.text = message
    }

    private fun showLoading(show: Boolean) {
        if (show) {
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
