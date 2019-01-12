package pl.dmcs.pb.richchat.data.entity

data class ChatMessages (var chatId : String,
                         var participants : List<String> = arrayListOf(),
                         var messages : List<Message> = arrayListOf())