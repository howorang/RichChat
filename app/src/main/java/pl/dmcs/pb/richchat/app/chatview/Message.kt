package pl.dmcs.pb.richchat.app.chatview

import java.time.LocalDate

data class Message(val username : String, val content : String, val timestamp : LocalDate)