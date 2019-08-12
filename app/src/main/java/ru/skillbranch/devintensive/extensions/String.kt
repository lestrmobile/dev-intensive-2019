package ru.skillbranch.devintensive.extensions

fun String.truncate(len:Int = 16):String{
  var r:String =""
  for(i in 0..len-1) {
    if(i == len-1){
      if (this[i].toString() == " ") continue
    }
    r += this[i].toString()
  }
  r += "..."
  return r
}

fun String.stripHtml():String{
  val s:String = this.toString()
  var tg: Boolean = false
  var br: Boolean =false
  var r:String =""
  for(i in 0..s.length-1) {
    if (s[i].toString() == " "&&s[i+1].toString() == " ") {
      r += ""
      continue
    }


    if (s[i].toString() == "<") tg = true
    else if (s[i].toString() == ">"){
      tg = false
      continue
    }
    if (tg) {
      r += ""
      continue
    } else {
      r += s[i]
      continue
    }





  }

  return r
}