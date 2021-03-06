package com.david.redcristianauno.domain

import android.content.Context
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.david.redcristianauno.data.network.Callback
import com.david.redcristianauno.vo.Resource


interface UserUseCase{
    suspend fun getNamesUsers(id_user: String): Resource<String>
    fun getCelulasForFillTil(subred: String, callback: Callback<MutableList<String>>)
    fun getSubredesForFillTil(callback: Callback<MutableList<String>>)
}