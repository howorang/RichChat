package pl.dmcs.pb.richchat.data.entity

data class User(var userId : String,
                var pushKey : String?,
                var username : String,
                var firstName : String?,
                var lastName : String?,
                var chats : List<String> = arrayListOf())