package com.example.todolist.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.activity.viewModels

import androidx.lifecycle.lifecycleScope


import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.example.todolist.R
import com.example.todolist.database.App

import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.entities.Project
import com.example.todolist.ui.alertDialogs.DialogListener

import com.example.todolist.viewmodels.MainActivityViewModel
import com.example.todolist.viewmodels.MainActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID


class MainActivity : AppCompatActivity(), DialogListener {

    val viewModel: MainActivityViewModel by viewModels { MainActivityViewModelFactory((application as App).projectsRepository) }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        Log.d("gfhfhfdsdj", "onPause: ")
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)


  /*      binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val frag = ProjectsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.general_layout, frag)
                    transaction.commit()
                    Toast.makeText(this, "menu_profile", Toast.LENGTH_SHORT).show()
                }

                R.id.menu_new -> {
                    val newProjectDialogFragment = NewProjectDialogFragment()
                    val manager = supportFragmentManager
                    val transaction: FragmentTransaction = manager.beginTransaction()
                    newProjectDialogFragment.show(transaction, "dialog")
                }

                R.id.menu_profile -> {
                    val frag = ProfileFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.general_layout, frag)
                    transaction.commit()
                    Toast.makeText(this, "menu_profile", Toast.LENGTH_SHORT).show()
                }

            }цц
            true
        }*/
    }

    override fun onStop() {
        super.onStop()
        Log.d("life", "life: ")
    }
    override fun onPause() {
        super.onPause()
        Log.d("life", "li1fe: ")
    }
    override fun onFinishDialog(data: String) {
        // Toast.makeText(this, "Hey super!", Toast.LENGTH_SHORT).show()
        val uuid = UUID.randomUUID()
        lifecycleScope.launch {
            viewModel.addNewProject(
                Project(
                    id = uuid.toString(),
                    name = data,
                    date = Calendar.getInstance().time,
                    isCompleted = false,
                )
            )
        }
    }
}
