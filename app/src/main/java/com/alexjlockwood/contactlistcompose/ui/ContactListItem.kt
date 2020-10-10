package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.ProvideTextStyle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Emphasis
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    detailText: @Composable () -> Unit,
    endIcon: @Composable (() -> Unit)? = null,
) {
    Row(modifier = modifier.preferredHeightIn(min = 64.dp)) {
        Spacer(modifier = Modifier.width(16.dp))

        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            startIcon()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            applyTextStyle(
                emphasis = EmphasisAmbient.current.high,
                textStyle = MaterialTheme.typography.subtitle1,
            ) {
                text()
            }

            applyTextStyle(
                emphasis = EmphasisAmbient.current.medium,
                textStyle = MaterialTheme.typography.body2,
            ) {
                detailText()
            }
        }

        if (endIcon == null) {
            Spacer(modifier = Modifier.width(16.dp))
        } else {
            Spacer(modifier = Modifier.weight(1f))
            endIcon()
        }
    }
}

@Composable
private fun applyTextStyle(
    emphasis: Emphasis,
    textStyle: TextStyle,
    children: @Composable (() -> Unit),
) {
    ProvideEmphasis(emphasis) {
        ProvideTextStyle(textStyle, children)
    }
}
