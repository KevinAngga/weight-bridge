package com.id.angga.weightbridge.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.domain.usecase.TicketUseCase
import com.id.angga.weightbridge.domain.usecase.validation.TicketValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketUseCase: TicketUseCase,
    private val ticketValidationUseCase: TicketValidationUseCase
) : ViewModel() {
    var ticket by mutableStateOf(TicketFormState())

    private val _tickets: MutableStateFlow<List<Weight>> = MutableStateFlow(emptyList())
    val tickets = _tickets.asStateFlow()

    private var currentWeight = Weight()
    private var cachedList = listOf<Weight>()

    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        getTickets()
    }

    private fun filter(query: String) {
        val listToSearch = if (isSearchStarting) {
            _tickets.value
        } else {
            cachedList
        }

        viewModelScope.launch {
            if (query.isEmpty()) {
                _tickets.value = cachedList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }

            val result = listToSearch.filter {
                it.driverName.contains(query.trim(), ignoreCase = true)
                        || it.licenseNumber.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarting) {
                cachedList = _tickets.value
                isSearchStarting = false
            }
            _tickets.value = result
            isSearching.value = true
        }
    }

    fun onEvent(event: TicketFormEvent) {
        when (event) {
            is TicketFormEvent.DriverNameChanged -> {
                ticket = ticket.copy(
                    driverName = event.driverName
                )
                validateDriverName()
                buttonEnabled()
            }

            is TicketFormEvent.InboundWeightChanged -> {
                ticket = ticket.copy(
                    inboundWeight = event.inbound
                )
                validateInboundWeight()
                buttonEnabled()
            }

            is TicketFormEvent.LicenseNumberChanged -> {
                ticket = ticket.copy(
                    licenseNumber = event.licenseNumber
                )
                validateTruckNumber()
                buttonEnabled()
            }

            is TicketFormEvent.OutboundWeightChanged -> {
                ticket = ticket.copy(
                    outboundWeight = event.outbound
                )
                validateOutboundWeight()
                buttonEnabled()
            }

            is TicketFormEvent.SubmitTicket -> {
                if (ticket.isUpdate) {
                    updateTicket()
                } else {
                    addTicket()
                }
            }

            is TicketFormEvent.AddTicket -> {
                ticket = ticket.copy(
                    id = 0,
                    licenseNumber = "",
                    driverName = "",
                    inboundWeight = "",
                    outboundWeight = "",
                    isUpdate = false
                )
            }

            is TicketFormEvent.EditTicket -> {
                currentWeight = event.weight
                ticket = ticket.copy(
                    licenseNumber = event.weight.licenseNumber,
                    driverName = event.weight.driverName,
                    inboundWeight = event.weight.inboundWeight.toString(),
                    outboundWeight = event.weight.outboundWeight.toString(),
                    isUpdate = true
                )
            }

            is TicketFormEvent.FilterTicket -> {
                ticket = ticket.copy(
                    filterQuery = event.query
                )
                filter(event.query)
            }

            is TicketFormEvent.SyncDataWithFirebase -> {
                syncDataWithFirebase(event.unSendList)
            }
        }
    }

    private fun updateTicket() {
        viewModelScope.launch {
            val weight = Weight(
                id = currentWeight.id,
                licenseNumber = ticket.licenseNumber,
                driverName = ticket.driverName,
                inboundWeight = ticket.inboundWeight.toInt(),
                outboundWeight = ticket.outboundWeight.toInt(),
                pushId = currentWeight.pushId,
                date = currentWeight.date,
                isSync = false
            )
            ticketUseCase.updateTicket.invoke(weight)
        }
    }

    private fun addTicket() {
        val weight = Weight(
            licenseNumber = ticket.licenseNumber,
            driverName = ticket.driverName,
            inboundWeight = ticket.inboundWeight.toInt(),
            outboundWeight = ticket.outboundWeight.toInt(),
            date = setDate(),
            pushId = generateRandomString(8)
        )
        viewModelScope.launch {
            ticketUseCase.addTicket.invoke(weight)
        }
    }

    private fun syncDataWithFirebase(unSendList : List<Weight>) {
        viewModelScope.launch {
           ticketUseCase.syncTicket.invoke(unSendList)
        }
    }

    private fun getTickets() {
        viewModelScope.launch {
            println("--- launch ")
            ticketUseCase.getTickets.invoke().collect {
                _tickets.value = it
                println("--- view model "+it)
            }
        }
    }

    private fun validateTruckNumber(): Boolean {
        val truckNumber = ticketValidationUseCase.validationTruckNumber.execute(ticket.licenseNumber)
        ticket = ticket.copy(
            licenseNumberError = truckNumber.errorMessage,
            buttonEnable = truckNumber.successful
        )
        return truckNumber.successful
    }

    private fun validateDriverName(): Boolean {
        val name = ticketValidationUseCase.validationName.execute(ticket.driverName)
        ticket = ticket.copy(
            driverNameError = name.errorMessage,
            buttonEnable = name.successful
        )
        return name.successful
    }

    private fun validateInboundWeight(): Boolean {
        val inboundWeight = ticketValidationUseCase.validationInboundWeight.execute(ticket.inboundWeight)
        ticket = ticket.copy(
            inboundWeightError = inboundWeight.errorMessage,
            buttonEnable = inboundWeight.successful
        )
        return inboundWeight.successful
    }


    private fun validateOutboundWeight(): Boolean {
        val outboundWeight = ticketValidationUseCase.validationOutboundWeight
            .execute(
                ticket.inboundWeight,
                ticket.outboundWeight
            )
        ticket = ticket.copy(
            outboundWeightError = outboundWeight.errorMessage,
            buttonEnable = outboundWeight.successful
        )
        return outboundWeight.successful
    }

    private fun buttonEnabled(): Boolean {
        return validateTruckNumber() && validateDriverName()
                && validateInboundWeight() && validateOutboundWeight()
    }
}

private fun generateRandomString(sizeOfRandomString: Int): String {
    val random = Random()
    val allowedChars = "0123456789qwertyuiopasdfghjklzxcvbnm"
    val sb = StringBuilder(sizeOfRandomString)
    for (i in 0 until sizeOfRandomString) {
        val randomIndex = random.nextInt(allowedChars.length)
        sb.append(allowedChars[randomIndex])
    }
    return sb.toString()
}

fun setDate() : String = SimpleDateFormat("yyyy/MM/dd hh:mm")
    .format(Date(System.currentTimeMillis()))

