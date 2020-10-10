package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alexjlockwood.contactlistcompose.domain.Contact
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalAnimationApi
@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
) {
    Card(
        modifier = modifier,
        elevation = 2.dp,
    ) {
        Column {
            CoilImage(
                data = contact.imageUrl,
                modifier = Modifier.aspectRatio(16f / 9f),
                fadeIn = true,
                contentScale = ContentScale.Crop,
            )
            ContactListItem(
                startIcon = { RoundedCoilImage(imageUrl = contact.imageUrl) },
                text = { SingleLineText(text = contact.name) },
                detailText = { SingleLineText(text = contact.description) },
                endIcon = { ExpandableChevron(isExpanded) },
            )
            AnimatedVisibility(visible = isExpanded) {
                Column {
                    ContactListItem(
                        startIcon = { VectorIcon(asset = Icons.Filled.Phone) },
                        text = { SingleLineText(text = contact.phoneNumber) },
                        detailText = { SingleLineText(text = contact.phoneNumberType) },
                    )
                    ContactListItem(
                        startIcon = { VectorIcon(asset = Icons.Filled.Email) },
                        text = { SingleLineText(text = contact.email) },
                        detailText = { SingleLineText(text = contact.emailType) },
                    )
                }
            }
        }
    }
}

@Composable
private fun RoundedCoilImage(imageUrl: String, modifier: Modifier = Modifier) {
    CoilImage(
        data = imageUrl,
        modifier = modifier.preferredSize(48.dp).clip(CircleShape),
        fadeIn = true,
    )
}

@Composable
private fun VectorIcon(asset: VectorAsset, modifier: Modifier = Modifier) {
    Icon(asset = asset, modifier = modifier.preferredSize(48.dp))
}

@Composable
private fun SingleLineText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
