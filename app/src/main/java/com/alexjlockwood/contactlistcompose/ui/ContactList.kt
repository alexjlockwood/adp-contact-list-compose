package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexjlockwood.contactlistcompose.domain.Contact

/**
 * Displays a recyclable list of expandable [ContactCard]s.
 */
@Composable
fun ContactList(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // Remember a mutable state map of contact IDs to a boolean tracking
    // their card's current expanded state.
    val expandedContactsMap = remember { mutableStateMapOf<String, Boolean>() }
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(contacts) { contact ->
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
        }
    }
}
