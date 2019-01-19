package pl.dmcs.pb.richchat.data.entity

import java.util.*

data class ChatHandle (var chatId : String,
                       var participants : List<String> = arrayListOf())