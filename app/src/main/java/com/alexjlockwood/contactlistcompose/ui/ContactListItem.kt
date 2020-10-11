package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ProvideTextStyle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

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
    // Render a horizontal row of items with a min height of 64dp and 16dp horizontal padding.
    Row(modifier = modifier.preferredHeightIn(min = 64.dp).padding(horizontal = 16.dp)) {
        // If specified, center the start icon vertically at the start of the list item.
        if (startIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterVertically).padding(end = 16.dp)) {
                startIcon()
            }
        }

        // Render the text and detail text vertically within the list item.
        Column(modifier = Modifier.align(Alignment.CenterVertically).weight(1f)) {
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

        // If specified, center the end icon at the end of the list item.
        if (endIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                endIcon()
            }
        }
    }
}

@Preview("ContactListItem")
@Composable
private fun ContactListItemPreview() {
    AppTheme {
        Surface {
            ContactListItem(
                text = { Text("Alex Lockwood") },
                detailText = { Text("Software Engineer") },
                startIcon = {
                    Icon(
                        asset = Icons.Filled.AccountCircle,
                        modifier = Modifier.size(48.dp),
                    )
                },
            )
        }
    }
}