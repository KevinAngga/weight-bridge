package com.id.angga.weightbridge.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.weightbridge.domain.usecase.TicketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: TicketUseCase
) : ViewModel() {
    private var _viewState = MutableStateFlow(DetailViewState())
    val viewState = _viewState.asStateFlow()

    fun onEvent(event: DetailViewEvent) {
        when(event) {
            is DetailViewEvent.DetailView -> {
                getTicket(event.primaryKey)
            }
        }
    }

    fun getTicket(primaryKey: Int) {
       viewModelScope.launch {
           val weight = useCase.getTicket.invoke(primaryKey)
           if (weight.id != 0) {
               _viewState.update {
                   it.copy(
                       weight = weight
                   )
               }
           }
       }
    }
}