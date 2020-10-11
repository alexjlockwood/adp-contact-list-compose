package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.ProvideTextStyle
import androidx.compose.foundation.layout.*
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A simple ListItem that displays text, detail text, a start icon,
 * and an optional end icon.
 */
@Composable
fun ContactListItem(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    detailText: @Composable (() -> Unit)? = null,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
) {
    // Render a horizontal row of items with a min height of 64dp.
    Row(modifier = modifier.preferredHeightIn(min = 64.dp)) {
        Spacer(modifier = Modifier.width(16.dp))

        // If specified, center the start icon vertically at the start of the list item.
        if (startIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                startIcon()
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        // Render the text and potential detail text vertically within the list item.
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            ProvideEmphasis(EmphasisAmbient.current.high) {
                ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                    // If not explicitly set by the caller, apply a high-emphasis,
                    // subtitle1 text style to the text.
                    text()
                }
            }

            if (detailText != null) {
                ProvideEmphasis(EmphasisAmbient.current.medium) {
                    ProvideTextStyle(MaterialTheme.typography.body2) {
                        // If not explicitly set by the caller, apply a medium-emphasis,
                        // body2 text style to the text.
                        detailText()
                    }
                }
            }
        }

        // If specified, render the end icon at the end of the list item.
        if (endIcon == null) {
            Spacer(modifier = Modifier.width(16.dp))
        } else {
            Spacer(modifier = Modifier.weight(1f))
            endIcon()
        }
    }
}
