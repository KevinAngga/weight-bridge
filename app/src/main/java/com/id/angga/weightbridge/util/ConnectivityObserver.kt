package com.id.angga.weightbridge.util

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun observer() : Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}