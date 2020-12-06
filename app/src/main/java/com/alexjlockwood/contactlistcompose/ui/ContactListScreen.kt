package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexjlockwood.contactlistcompose.R
import com.alexjlockwood.contactlistcompose.domain.Contact

/**
 * The home screen of the application that displays a [TopAppBar] and the [ContactList].
 */
@Composable
fun ContactListScreen(contacts: List<Contact>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.background,
            )
        },
    ) {
        ContactList(
            contacts = contacts,
            contentPadding = PaddingValues(16.dp),
        )
    }
}
