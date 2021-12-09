package com.example.convidados.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application):  AndroidViewModel(application){
    private val mContext = application.applicationContext
    private var mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>() //what is this
    val saveGuest:LiveData<Boolean> = mSaveGuest

    fun save(name:String, presence:Boolean) {
        val guest = GuestModel(name=name,presence = presence)
        mGuestRepository.save(guest)


    }

}