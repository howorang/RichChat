package pl.dmcs.pb.richchat.data.entity

import java.time.ZonedDateTime

data class Message (var text : String,
                    var attachment : Attachment,
                    var senderDisplayName : String,
                    var messageTimestamp : ZonedDateTime)