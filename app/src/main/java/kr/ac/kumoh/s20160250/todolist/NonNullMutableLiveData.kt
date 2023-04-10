package kr.ac.kumoh.s20160250.todolist

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}