package com.david.redcristianauno.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.david.redcristianauno.R
import com.david.redcristianauno.model.User
import com.david.redcristianauno.network.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private  var firebaseService = FirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        database = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        //dbReference=database.reference.child("usuarios")
        dbReference = database.reference
    }
    fun userRegister(view: View?){
        val names = etNamesResgisterActivity.text.toString().trim{it <= ' '}
        val last_name = etLastNamesRegisterActivity.text.toString().trim{it <= ' '}
        val email = etEmailResgisterActivity.text.toString().trim{it <= ' '}
        val password = etPassResgisterActivity.text.toString().trim{it <= ' '}
        val confirm_password = etConfirmPassResgisterActivity.text.toString().trim{it <= ' '}

        if(!TextUtils.isEmpty(names) &&
            !TextUtils.isEmpty(last_name) &&
            !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password) &&
            !TextUtils.isEmpty(confirm_password)){
            if (password == confirm_password){
                rlBaseRegister.visibility = View.VISIBLE

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){task ->

                        if(task.isComplete){
                            val user: FirebaseUser? = firebaseAuth.currentUser
                            verifyEmail(user)

                            createAccount(names, last_name, email, password)
                            actionLogin()
                        }

                    }
            }
        }

    }

    private fun actionLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun createAccount(names: String, last_names: String, email: String, password: String){

        var user = User()
        user.names = names
        user.last_names = last_names
        user.email = email
        user.password = password
        user.permission = "Normal"

        firebaseService.firebaseFirestore.collection("usuarios").document().set(user)

    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){task ->

                if(task.isComplete){
                    Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
                }

            }
    }
}