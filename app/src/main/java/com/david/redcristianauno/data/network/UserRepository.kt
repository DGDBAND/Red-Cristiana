package com.david.redcristianauno.data.network

import android.content.Context
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.david.redcristianauno.data.model.Subred
import com.david.redcristianauno.data.model.User
import com.david.redcristianauno.vo.Resource

interface UserRepository {
    suspend fun getNamesUsers(id_user: String): Resource<String>
    fun getCelulasForFillTil(subred: String, callback: Callback<MutableList<String>>)
    fun getSubredesForFillTil(callback: Callback<MutableList<String>>)
    fun getDataUser(callback: Callback<User>)
    fun updateDataUser(names: String, last_names: String, telephone: String, address: String)
}