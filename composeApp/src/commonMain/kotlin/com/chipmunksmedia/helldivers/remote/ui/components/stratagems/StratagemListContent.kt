package com.chipmunksmedia.helldivers.remote.ui.components.stratagems

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.chipmunksmedia.helldivers.remote.domain.stratagems.StratagemListComponent

@Composable
fun StratagemListContent(
    component: StratagemListComponent,
    modifier: Modifier = Modifier,
) {
    val models by component.models.collectAsState()

    StratagemList(
        modifier = modifier,
        stratagems = models.stratagems,
        highlightCount = models.filterLength,
    )
}
