package com.shadhin.roomcoroutines.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.viewmodel.LoginViewModel

import com.shadhin.roomcoroutines.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    var navController : NavController? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        loginBtn.setOnClickListener { onLogin(it) }
        gotoSignupBtn.setOnClickListener { onGotoSignup(it) }

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(this, Observer { isComplete ->
            Toast.makeText(activity, "Login Complete", Toast.LENGTH_LONG).show()
            navController!!.navigate(R.id.action_loginFragment_to_mainFragment)
        })

        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(activity, error, Toast.LENGTH_LONG).show()

        })
    }
    private fun onLogin(v: View) {
        var userName=loginUsername.text.toString()
        var password=loginPassword.text.toString()
        if(userName.isNullOrEmpty()||password.isNullOrEmpty()){
            Toast.makeText(activity, "Please fill up the field", Toast.LENGTH_LONG).show()
        }else{
            viewModel.login(userName,password)
        }
        //navController!!.navigate(R.id.action_loginFragment_to_mainFragment)
    }
    private fun onGotoSignup(v: View){
        navController!!.navigate(R.id.action_loginFragment_to_signUpFragment)
    }
}
