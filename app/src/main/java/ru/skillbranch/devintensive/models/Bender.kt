package ru.skillbranch.devintensive.models

import androidx.core.text.isDigitsOnly

class Bender(var status:Status = Status.NORMAL, var question:Question = Question.NAME) {

    var cnt:Int = 0

    fun askQuestion():String = when (question){

        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String,Triple<Int,Int,Int>>{
        val regex = Regex(pattern = "0|1|2|3|4|5|6|7|8|9")
        var ans:String

        if (question != Question.IDLE){
            if (question == Question.NAME) {
                if (answer[0].isLowerCase()) {
                    return "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
                }
            } else if (question == Question.PROFESSION) {
                if (answer[0].isUpperCase()) {
                    return "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
                }
            } else if (question == Question.MATERIAL) {
                if (answer.contains(regex)) {
                    return "Материал не должен содержать цифр\n${question.question}" to status.color
                }
            } else if (question == Question.BDAY) {
                if (answer.isDigitsOnly().not()) {
                    return "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
                }
            } else if (question == Question.SERIAL) {
                if ((answer.isDigitsOnly().not()) || ((answer.length == 7).not())) {
                    return "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
                }
            }
        }
        ans = answer.toLowerCase()

        if (question.answers.contains(ans)) {
            cnt=0
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        }else{
            cnt++
            if(cnt < 3){
            status = status.nextStatus()
            return "Это неправильный ответ\n${question.question}" to status.color}
            else{
                cnt = 0
                resetBender()
                return  "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
        }
    }

    fun resetBender(){
        cnt = 0
        this.status = Status.NORMAL
        this.question = Question.NAME

    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus():Status{
            return if(this.ordinal< values().lastIndex){
                values()[this.ordinal+1]
            }else{
                values()[0]
            }
        }

    }

    enum class Question(val question:String, val answers:List<String> ) {

        NAME("Как меня зовут?",listOf("бендер","bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?",listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?",listOf("металл","дерево","metall","iron","wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?",listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?",listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет",listOf()){
            override fun nextQuestion(): Question = IDLE
        };
        abstract fun nextQuestion():Question
    }
}