package pl.dmcs.pb.richchat.data.entity

data class Chat (var chatId : String,
                 var messages : List<Message> = arrayListOf())