package com.rhezarijaya.yummyapp.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.domain.usecase.FoodUseCase
import com.rhezarijaya.core.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")

    val searchResult = searchQuery
        .debounce(Constants.SEARCH_DEBOUNCE)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            foodUseCase.searchFoodByName(it)
        }
        .asLiveData()

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}