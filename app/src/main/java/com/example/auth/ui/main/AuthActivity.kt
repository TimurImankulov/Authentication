package com.example.auth.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.auth.databinding.ActivityMainBinding
import com.example.auth.ui.main.newActivity.NewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupViewModel()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun setupViewModel() {
        viewModel.actionNewScreen.observe(this, Observer{
            startActivity(Intent(this, NewActivity::class.java))
        })

        viewModel.error.observe(this, Observer{
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}