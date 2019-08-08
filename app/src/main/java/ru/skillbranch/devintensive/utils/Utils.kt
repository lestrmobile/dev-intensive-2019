package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        var fn :String?

        fn = when(fullName){
             ""," " -> null
             else -> fullName
        }


        val parts : List<String>? = fn?.split(" ")


        var firstName =parts?.getOrNull(0)
        var lastName =parts?.getOrNull(1)
        //return Pair(firstName,lastName)
        firstName = firstName?:null
        lastName = lastName?:null
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var rtn:String;
        if (firstName.isNullOrEmpty()) rtn = ""
            else rtn = firstName[0].toString().toUpperCase()
        if (lastName.isNullOrEmpty()) rtn += ""
            else rtn += lastName[0].toString().toUpperCase()
        //else rtn: String? = (firstName?.firstOrNull()?.toString()+lastName?.firstOrNull()?.toString()).toString()
        if (firstName == " ") rtn =""
        if (lastName == " ") rtn =""
        when (rtn) {
            "", " " -> return null
        }
        return rtn
    }

    fun transliteration(payload: String,divider:String =" "): String {
        TODO("not implemented")
    }



}