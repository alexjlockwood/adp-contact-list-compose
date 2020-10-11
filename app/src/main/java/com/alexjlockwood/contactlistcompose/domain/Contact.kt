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

val CONTACT_LIST = listOf(
    Contact(
        id = "${UUID.randomUUID()}",
        name = "Alex Lockwood",
        description = "Takes forever writing blog posts",
        phoneNumber = "415-555-1809",
        phoneNumberType = "Mobile",
        email = "alex@gmail.com",
        emailType = "Work",
        imageUrl = "https://i.imgur.com/FMtnN6X.jpg",
    ),
    Contact(
        id = "${UUID.randomUUID()}",
        name = "Roman Nurik",
        description = "Android Developer",
        phoneNumber = "415-555-9967",
        phoneNumberType = "Mobile",
        email = "roman@gmail.com",
        emailType = "Work",
        imageUrl = "https://i.imgur.com/MOur0yy.png",
    ),
    Contact(
        id = "${UUID.randomUUID()}",
        name = "Nick Butcher",
        description = "Likes motion a lot",
        phoneNumber = "415-555-8375",
        phoneNumberType = "Mobile",
        email = "nick@gmail.com",
        emailType = "Work",
        imageUrl = "https://i.imgur.com/BFSEupZ.jpg",
    ),
    Contact(
        id = "${UUID.randomUUID()}",
        name = "Chris Banes",
        description = "CEO of Friendcaster",
        phoneNumber = "415-555-5113",
        phoneNumberType = "Mobile",
        email = "chris@gmail.com",
        emailType = "Work",
        imageUrl = "https://i.imgur.com/1C63cnE.jpg",
    ),
)