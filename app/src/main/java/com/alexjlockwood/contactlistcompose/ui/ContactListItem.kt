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

/**
 * A simple ListItem class that displays text, detail text, a start icon,
 * and an optional end icon.
 */
@Composable
fun ContactListItem(
    text: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    detailText: @Composable (() -> Unit)? = null,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
) {
    Row(modifier = modifier.preferredHeightIn(min = 64.dp)) {
        Spacer(modifier = Modifier.width(16.dp))

        if (startIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                startIcon()
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            provideTextStyleWithEmphasis(
                textStyle = MaterialTheme.typography.subtitle1,
                emphasis = EmphasisAmbient.current.high,
            ) {
                text()
            }

            if (detailText != null) {
                provideTextStyleWithEmphasis(
                    textStyle = MaterialTheme.typography.body2,
                    emphasis = EmphasisAmbient.current.medium,
                ) {
                    detailText()
                }
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

/**
 * Helper function that provides a default [textStyle] and [emphasis]
 * to the given composable [children].
 */
@Composable
private fun provideTextStyleWithEmphasis(
    textStyle: TextStyle,
    emphasis: Emphasis,
    children: @Composable () -> Unit,
) {
    ProvideEmphasis(emphasis) {
        ProvideTextStyle(textStyle, children)
    }
}
