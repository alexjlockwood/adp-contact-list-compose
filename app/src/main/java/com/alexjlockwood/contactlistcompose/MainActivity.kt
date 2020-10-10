package com.alexjlockwood.contactlistcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.alexjlockwood.contactlistcompose.domain.CONTACT_LIST
import com.alexjlockwood.contactlistcompose.ui.AppTheme
import com.alexjlockwood.contactlistcompose.ui.ContactListScreen

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ContactListScreen(contacts = CONTACT_LIST)
                }
            }
        }
    }
}
