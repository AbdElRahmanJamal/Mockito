package com.m.mockito.voidmethodtest

class Student(private var name: String?) {

    fun getStudentName() = if (name == null) throw NullPointerException()
    else name


    fun setStudentName(str: String?) {
        this.name = str!!.capitalize()
    }
}
