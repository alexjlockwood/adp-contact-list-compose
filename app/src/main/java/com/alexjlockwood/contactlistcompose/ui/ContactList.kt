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

@ExperimentalAnimationApi
@Composable
fun ContactList(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val expandedContactsMap = remember { mutableStateMapOf<String, Boolean>() }
    LazyColumnForIndexed(
        items = contacts,
        modifier = modifier,
        contentPadding = contentPadding,
    ) { index, contact ->
        val isContactExpanded = expandedContactsMap[contact.id] ?: false
        ContactCard(
            contact = contact,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expandedContactsMap[contact.id] = !isContactExpanded }),
            isExpanded = isContactExpanded,
        )
        if (index < contacts.size - 1) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
