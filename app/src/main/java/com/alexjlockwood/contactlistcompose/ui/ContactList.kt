package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexjlockwood.contactlistcompose.domain.Contact

/**
 * Displays a recyclable list of expandable [ContactCard]s.
 */
@ExperimentalAnimationApi
@Composable
fun ContactList(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // Remember a mutable state map of contact IDs to a boolean tracking
    // their card's current expanded state.
    val expandedContactsMap = remember { mutableStateMapOf<String, Boolean>() }
    LazyColumnForIndexed(
        items = contacts,
        modifier = modifier,
        contentPadding = contentPadding,
    ) { index, contact ->
        // Obtain this contact's current expanded state.
        val isContactCardExpanded = expandedContactsMap[contact.id] ?: false
        ContactCard(
            contact = contact,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    // Toggle the card's expanded state each time it is clicked.
                    expandedContactsMap[contact.id] = !isContactCardExpanded
                }),
            isExpanded = isContactCardExpanded,
        )
        if (index < contacts.size - 1) {
            // Add a bottom margin beneath every card except the last.
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
