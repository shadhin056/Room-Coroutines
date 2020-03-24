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
import com.devtides.coroutinesroom.viewmodel.SignupViewModel

import com.shadhin.roomcoroutines.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    var navController: NavController? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        signupBtn.setOnClickListener { onSignup(it) }
        gotoLoginBtn.setOnClickListener { onGotoLogin(it) }

        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupComplete.observe(this, Observer { isComplete ->
            Toast.makeText(activity, "Signup Completed", Toast.LENGTH_LONG).show()
            navController!!.navigate(R.id.action_signUpFragment_to_mainFragment)
        })

        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun onSignup(v: View) {

        val userName = signupUsername.text.toString()
        val password = signupPassword.text.toString()
        val info = otherInfo.text.toString()
        if (userName.isNullOrEmpty() || password.isNullOrEmpty() || info.isNullOrEmpty()) {
            Toast.makeText(activity, "Please Fill all the fields", Toast.LENGTH_LONG).show()
        } else {
            viewModel.signup(userName, password, info)
        }

    }

    private fun onGotoLogin(v: View) {
        navController!!.navigate(R.id.action_signUpFragment_to_loginFragment)
    }

}
