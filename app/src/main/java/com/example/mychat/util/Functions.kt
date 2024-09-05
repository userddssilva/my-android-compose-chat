package com.example.mychat.util

import com.example.mychat.domain.Message
import com.parse.ParseObject
import com.parse.ParseUser

fun mapMessages(chatRoom: ParseObject): List<Message> {
    val messageObjects = chatRoom.getList<ParseObject>(
        ChatRoomTable.MESSAGES
    ) ?: emptyList()
    return messageObjects.map { parseObject ->
        Message(
            objectId = parseObject.objectId,
            chatRoom = chatRoom,
            owner = parseObject.getParseUser(MessageTable.OWNER)
                ?: ParseUser.getCurrentUser(),
            text = parseObject.getString(MessageTable.TEXT) ?: "",
            timestamp = parseObject.getLong(MessageTable.TIMESTAMP)
        )
    }
}