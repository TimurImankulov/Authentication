package com.example.auth.ui.main.newActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.auth.R
import com.example.auth.databinding.ActivityNewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewBinding
    private val viewModel by viewModel<NewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.error.observe(this, Observer{
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.getProfileModel().observe(this, Observer{
            binding.tvUserName.text = it?.firstName
        })
    }
}
