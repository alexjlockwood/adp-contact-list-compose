package com.alexjlockwood.contactlistcompose.domain

import java.util.*

data class Contact(
    val id: String,
    val name: String,
    val description: String,
    val phoneNumber: String,
    val phoneNumberType: String,
    val email: String,
    val emailType: String,
    val imageUrl: String,
)

val CONTACT_LIST = arrayOfNulls<Contact>(20).map {
    Contact(
        id = "${UUID.randomUUID()}",
        name = "Alex Lockwood",
        description = "Software Engineer",
        phoneNumber = "415-555-1809",
        phoneNumberType = "Mobile",
        email = "alex@gmail.com",
        emailType = "Work",
        imageUrl = "https://i.imgur.com/V00qvM4.jpg",
    )
}