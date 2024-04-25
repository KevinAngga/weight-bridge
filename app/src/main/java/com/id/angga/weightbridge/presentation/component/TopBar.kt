package com.id.angga.weightbridge.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.id.angga.weightbridge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar (
    title : String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back),
                    contentDescription = null,
                )
            }
        },
    )
}