package com.id.angga.weightbridge.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.presentation.component.CustomButton
import com.id.angga.weightbridge.presentation.component.CustomTextField

@Composable
fun TicketListScreen(
    navigateToDetail :  (Weight) ->  Unit
) {
    val viewModel: TicketViewModel = hiltViewModel()
    val tickets by viewModel.tickets.collectAsState()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showSheet = true
                    viewModel.onEvent(TicketFormEvent.AddTicket)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            CustomTextField(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                value = viewModel.ticket.filterQuery,
                labelText = stringResource(id = R.string.truck_license),
                onValueChange = {
                    viewModel.onEvent(TicketFormEvent.FilterTicket(it))
                },
                keyboardType = KeyboardType.Text,
                trailingIcon = Icons.Default.Info,
                singleLine = true,
                maxLine = 1
            )
            
            if (tickets.any { ticket -> !ticket.isSync }) {
                Row (
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = stringResource(id = R.string.pending_data))
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { viewModel.onEvent(TicketFormEvent.SyncDataWithFirebase(tickets.filter { !it.isSync })) }) {
                        Text(text = stringResource(id = R.string.send_to_server))
                    }
                }
            }

            AnimatedVisibility(
                visible = tickets.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(
                        tickets, key = { it.id }
                    ) { ticket ->
                        TicketItem(
                            weight = ticket,
                            onEdit = {
                                showSheet = true
                                viewModel.onEvent(TicketFormEvent.EditTicket(it))
                            },
                            onClick = {
                                navigateToDetail(it)
                            }
                        )
                    }
                }
            }


            if (showSheet) {
                FormDialog(
                    onDismiss = { showSheet = false },
                    onEvent = viewModel::onEvent,
                    ticketFormState = viewModel.ticket
                )
            }
        }
    }
}

@Composable
fun FormDialog(
    onDismiss: () -> Unit,
    onEvent: (TicketFormEvent) -> Unit,
    ticketFormState: TicketFormState,
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            CustomTextField(
                value = ticketFormState.licenseNumber,
                labelText = stringResource(id = R.string.truck_license),
                onValueChange = {
                    onEvent(TicketFormEvent.LicenseNumberChanged(it))
                },
                keyboardType = KeyboardType.Text,
                trailingIcon = Icons.Default.Info,
                singleLine = true,
                maxLine = 1,
                isError = ticketFormState.licenseNumberError != null,
                errorMessage = ticketFormState.licenseNumberError
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = ticketFormState.driverName,
                labelText = stringResource(id = R.string.driver_name),
                onValueChange = {
                    onEvent(TicketFormEvent.DriverNameChanged(it))
                },
                keyboardType = KeyboardType.Text,
                trailingIcon = Icons.Default.AccountCircle,
                singleLine = true,
                maxLine = 1,
                isError = ticketFormState.driverNameError != null,
                errorMessage = ticketFormState.driverNameError
            )

            Spacer(modifier = Modifier.height(10.dp))


            CustomTextField(
                value = ticketFormState.inboundWeight,
                labelText = stringResource(id = R.string.inbound),
                onValueChange = {
                    onEvent(TicketFormEvent.InboundWeightChanged(it))
                },
                keyboardType = KeyboardType.Number,
                trailingIcon = Icons.Default.ArrowForward,
                singleLine = true,
                maxLine = 1,
                isError = ticketFormState.inboundWeightError != null,
                errorMessage = ticketFormState.inboundWeightError
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = ticketFormState.outboundWeight,
                labelText = stringResource(id = R.string.outbound),
                onValueChange = {
                    onEvent(TicketFormEvent.OutboundWeightChanged(it))
                },
                keyboardType = KeyboardType.Number,
                trailingIcon = Icons.Default.ArrowBack,
                singleLine = true,
                maxLine = 1,
                isError = ticketFormState.outboundWeightError != null,
                errorMessage = ticketFormState.outboundWeightError
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                textButton = stringResource(id = R.string.submit),
                showIcon = false,
                enable = ticketFormState.buttonEnable
            ) {
                onEvent(TicketFormEvent.SubmitTicket)
                onDismiss()
            }

        }
    }
}