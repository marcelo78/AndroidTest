package com.mac.intacttest.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mac.intacttest.R
import com.mac.intacttest.activities.data.ProductsCollection
import com.mac.intacttest.backend.ServiceUtil
import com.mac.intacttest.tools.Constants
import com.mac.intacttest.tools.Event
import kotlinx.coroutines.launch

class ProductsViewModel constructor(private val serviceUtil: ServiceUtil) : ViewModel() {
    private val _uiState = MutableLiveData<ProductsDataState>()
    val uiState: LiveData<ProductsDataState> get() = _uiState

    init {
        retrieveArticle()
    }

    private fun retrieveArticle() {
        viewModelScope.launch {
            runCatching {
                emitUiState(showProgress = true)
                serviceUtil.getProducts(apiKey = Constants.API_KEY)
            }.onSuccess {
                emitUiState(article = Event(it.articles))
            }.onFailure {
                it.printStackTrace()
                emitUiState(error = Event(R.string.internet_failure_error))
            }
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        article: Event<List<ProductsCollection.Article>>? = null,
        error: Event<Int>? = null
    ) {
        val dataState = ProductsDataState(showProgress, article, error)
        _uiState.value = dataState
    }
}

data class ProductsDataState(
    val showProgress: Boolean,
    val article: Event<List<ProductsCollection.Article>>?,
    val error: Event<Int>?
)
