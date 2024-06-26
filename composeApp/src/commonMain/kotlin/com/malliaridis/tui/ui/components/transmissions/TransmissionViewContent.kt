package com.malliaridis.tui.ui.components.transmissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.malliaridis.tui.domain.transmissions.TransmissionComponent

@Composable
fun TransmissionViewContent(
    component: TransmissionComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.models.collectAsState()

    // TODO Handle loading details

    val transmission = model.transmission
    if (transmission != null) TransmissionView(
        modifier = modifier,
        transmission = transmission,
    )
}
