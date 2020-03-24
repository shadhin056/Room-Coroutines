package com.shadhin.roomcoroutines.view

import android.app.AlertDialog
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
import com.devtides.coroutinesroom.viewmodel.MainViewModel

import com.shadhin.roomcoroutines.R
import com.shadhin.roomcoroutines.model.LoginState
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    var navController: NavController? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTV.text=LoginState.user?.userName
        navController = Navigation.findNavController(view)
        signoutBtn.setOnClickListener { onSignout() }
        deleteUserBtn.setOnClickListener { onDelete() }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(this, Observer {
            Toast.makeText(activity, "Signed out", Toast.LENGTH_LONG).show()
            navController!!.navigate(R.id.action_mainFragment_to_signUpFragment)
        })
        viewModel.userDeleted.observe(this, Observer {
            Toast.makeText(activity, "User Deleted", Toast.LENGTH_LONG).show()
            navController!!.navigate(R.id.action_mainFragment_to_signUpFragment)
        })
    }

    private fun onSignout() {
        viewModel.onSignout()
        //navController!!.navigate(R.id.action_mainFragment_to_signUpFragment)
    }

    private fun onDelete() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete the user ?")
                .setPositiveButton("Yes") { p0, p1 -> viewModel.onDeleteUser() }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
        // navController!!.navigate(R.id.action_mainFragment_to_signUpFragment)
    }


}
