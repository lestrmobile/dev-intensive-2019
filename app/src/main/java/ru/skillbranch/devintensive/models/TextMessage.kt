package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.Extensions.humanizeDiff
import java.util.*

class TextMessage(
    id:String,
    Date:User?,
    chat:Chat,
    isIncoming : Boolean = false,
    date: Date= Date(),
    var text:String?
): BaseMessage(id, Date,chat,isIncoming,date) {
    override fun formatMessage(): String = "id:$id ${from?.firstName}"+
        " ${if(isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"

}



