package com.malliaridis.tui.ui.components.transmissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malliaridis.tui.model.TransmissionListEntry
import com.malliaridis.tui.ui.components.StripesDecorator
import com.malliaridis.tui.ui.theme.CustomColors
import com.malliaridis.tui.ui.theme.Modifiers.border

@Composable
fun TransmissionsList(
    modifier: Modifier = Modifier,
    transmissions: List<TransmissionListEntry>,
    onSelectTransmission: (String) -> Unit,
    selectedTransmissionId: String? = null,
) = StripesDecorator(
    modifier = modifier.border(
        top = 1.5.dp,
        color = CustomColors.borderColorVariant,
    )
) {
    LazyColumn(
        modifier = Modifier.padding(top = 1.5.dp), // to avoid overlap with border
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(transmissions) { transmission ->
            TransmissionListItem(
                transmission = transmission,
                isSelected = transmission.id == selectedTransmissionId,
                onClick = { onSelectTransmission(transmission.id) },
            )
        }
    }
}
