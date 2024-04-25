package com.id.angga.weightbridge.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.id.angga.weightbridge.presentation.component.TopBar

@Composable
fun TicketDetailScreen(
    primaryKey: Int,
    navigateUp : () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "",
                onBackClick = navigateUp
            )
        }
    ) {
        val viewModel : DetailViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.onEvent(DetailViewEvent.DetailView(primaryKey))
        }

        val viewState = viewModel.viewState.collectAsStateWithLifecycle()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Truck License Number"
            )

            Text(
                text = viewState.value.weight.licenseNumber
            )

            Text(
                text = "Driver Name"
            )

            Text(
                text = viewState.value.weight.driverName
            )

            Text(
                text = "Inbound Weight"
            )

            Text(
                text = viewState.value.weight.inboundWeight.toString()
            )

            Text(
                text = "Outbound Weight"
            )

            Text(
                text = viewState.value.weight.outboundWeight.toString()
            )

            Text(
                text = "Nett Weight"
            )

            Text(
                text = viewState.value.weight.nettWeight().toString()
            )

            Text(
                text = "Date In"
            )

            Text(
                text = viewState.value.weight.date
            )

        }
    }
}