package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        var fn :String?

        fn = when(fullName){
             ""," " -> "null null"
             else -> fullName
        }


        val parts : List<String>? = fn?.split(" ")


        var firstName =parts?.getOrNull(0)
        var lastName =parts?.getOrNull(1)
        //return Pair(firstName,lastName)
        firstName = firstName?:"null"
        lastName = lastName?:"null"
        return firstName to lastName
    }

    fun toinitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented")
    }

    fun transliteration(payload: String,divider:String =" "): String {
        TODO("not implemented")
    }



}