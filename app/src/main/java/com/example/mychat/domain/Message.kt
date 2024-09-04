package com.example.mychat.domain

import com.parse.ParseObject
import com.parse.ParseUser

class Message(
    val objectId: String,
    val chatRoom: ParseObject,
    val owner: ParseUser,
    val text: String,
    val timestamp: Long
)