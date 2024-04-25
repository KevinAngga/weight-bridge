package com.id.angga.weightbridge.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.angga.weightbridge.R
import com.id.angga.weightbridge.domain.model.Weight
import com.id.angga.weightbridge.presentation.component.CustomButton
import com.id.angga.weightbridge.ui.theme.monserratBold

@Composable
fun TicketItem(
    weight: Weight,
    onClick: (Weight) -> Unit,
    onEdit : (Weight) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable { onClick(weight) }
        ) {
            Text(
                text = weight.licenseNumber,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                style = monserratBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = weight.driverName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                style = monserratBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(id = R.string.in_text, weight.inboundWeight),
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.out_text, weight.outboundWeight)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.nett_text, weight.nettWeight()),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Icon(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { onEdit(weight)  },
                imageVector = Icons.Default.Edit,
                contentDescription = "",
            )
        }
    }
}