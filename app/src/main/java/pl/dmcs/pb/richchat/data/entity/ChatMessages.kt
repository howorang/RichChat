package pl.dmcs.pb.richchat.data.entity

data class ChatMessages (var chatId : String,
                         var participants : MutableList<String> = mutableListOf(),
                         var messages : MutableList<Message> = mutableListOf()
)