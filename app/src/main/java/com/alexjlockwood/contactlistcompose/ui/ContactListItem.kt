package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

        // Render the text and detail text vertically within the list item.
        Column(modifier = Modifier.align(Alignment.CenterVertically).weight(1f)) {
            Providers(AmbientContentAlpha provides ContentAlpha.high) {
                ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                    // If not explicitly set by the caller, apply a high-emphasis,
                    // subtitle1 text style to the text.
                    text()
                }
            }

            if (detailText != null) {
                Providers(AmbientContentAlpha provides ContentAlpha.medium) {
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

        Spacer(modifier = Modifier.width(16.dp))
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
                        imageVector = Icons.Filled.AccountCircle,
                        modifier = Modifier.size(48.dp),
                    )
                },
            )
        }
    }
}