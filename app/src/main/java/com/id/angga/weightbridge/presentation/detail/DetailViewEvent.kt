package com.id.angga.weightbridge.presentation.detail

sealed class DetailViewEvent {
    data class DetailView(val primaryKey : Int) : DetailViewEvent()
}