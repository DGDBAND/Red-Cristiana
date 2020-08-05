package com.david.redcristianauno.data.network

import com.david.redcristianauno.data.model.GeneralModel
import com.david.redcristianauno.data.model.Iglesia


interface ChurchRepository {
    fun getIglesias(callback: Callback<MutableList<Iglesia>>)
    fun getRedes(name: String, callback: Callback<MutableList<String>>)
    fun getSubredes(iglesia: String, red: String, callback: Callback<MutableList<String>>)
    fun getCelulas(iglesia: String, red: String, subred: String, callback: Callback<MutableList<String>>)
    fun getRedObject(name: String, callback: Callback<MutableList<GeneralModel>>)
    fun getSubredObject(iglesia: String, red: String, callback: Callback<MutableList<GeneralModel>>)
    fun getCelulaObject(iglesia: String, red: String, subred: String, callback: Callback<MutableList<GeneralModel>>)
}