package com.david.redcristianauno.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.david.redcristianauno.*
import com.david.redcristianauno.application.AppConstants.LOGIN_ACTIVITY
import com.david.redcristianauno.data.network.FirebaseService
import com.david.redcristianauno.data.network.UserRepositoryImpl
import com.david.redcristianauno.domain.ProfileUseCaseImpl
import com.david.redcristianauno.presentation.objectsUtils.SnackBarMD
import com.david.redcristianauno.presentation.objectsUtils.UserSingleton
import com.david.redcristianauno.presentation.viewmodel.LoginViewModelP
import com.david.redcristianauno.presentation.viewmodel.ProfileViewModel
import com.david.redcristianauno.presentation.viewmodel.ProfileViewModelFactory
import com.david.redcristianauno.vo.Resource
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel by viewModels<LoginViewModelP>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginMain(view: View?) {
        val email = etEmailLogin.text.toString().trim()
        val password = etPasswordLogin.text.toString().trim()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            setupObservers(email, password)
        } else {
            SnackBarMD.getSBIndefinite(btn_start_session_loginActivity, "Completa todos los campos")
        }
    }

    private fun actionMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun actionRegister(view: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun actionForgotPassword(view: View?) {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
       if(loginViewModel.isCurrentUserFromFirebase()) {
           actionMain()
       }
    }

    private fun setupObservers(email: String, password: String){
        loginViewModel.login(email, password).observe(this, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    rlBaseLogin.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    rlBaseLogin.visibility = View.GONE
                    result.data?.let { loginViewModel.getUser(it) }
                }
                is Resource.Failure -> {
                    if(result.exception is FirebaseAuthInvalidUserException){
                        SnackBarMD.getSBIndefinite(btn_start_session_loginActivity, "Usuario Invalido")
                    }else if(result.exception is FirebaseAuthInvalidCredentialsException){
                        SnackBarMD.getSBIndefinite(btn_start_session_loginActivity, "Contraseña Invalida")
                    }
                    rlBaseLogin.visibility = View.GONE

                }
            }
        })

        loginViewModel.getUser.observe(this, Observer { result ->
            when (result) {
                is Resource.Loading -> Log.i(LOGIN_ACTIVITY, "Cargando...")
                is Resource.Success -> {
                    Log.i(LOGIN_ACTIVITY, "Data: ${result.data}")
                    if(result.data == null){
                        return@Observer
                    }
                    loginViewModel.saveUserLocal(result.data)
                    actionMain()
                }
                is Resource.Failure -> Log.i(LOGIN_ACTIVITY, "Error: ${result.exception}")
            }
        })
    }
}