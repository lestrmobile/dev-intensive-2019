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