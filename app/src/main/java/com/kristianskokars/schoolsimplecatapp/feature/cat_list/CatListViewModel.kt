package com.kristianskokars.schoolsimplecatapp.feature.cat_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristianskokars.schoolsimplecatapp.data.repository.CatRepository
import com.kristianskokars.schoolsimplecatapp.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing = _isRefreshing.asStateFlow()
    val cats = repository.cats.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        refreshCats()
    }

    fun refreshCats() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            repository.refreshCats()
            _isRefreshing.update { false }
        }
    }
}

/*
    API/Repo ---------------------- UI

    UI grib kaķus
    pajautā API kaķus
    viņam tikmēr ko nav parādīt, tāpēc ir tukšs saraksts
    API atbild pēc 1 sekundes, 2 gadiem
    API pasaka ka ir jauns data šajā plusmā
    jaunais cats stāvoklis -> jaunie kaķi
 */
