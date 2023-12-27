package com.yogi.kmtest.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yogi.kmtest.R
import com.yogi.kmtest.databinding.ActivityMainBinding
import com.yogi.kmtest.ui.second.SecondActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set Hint in textEdit
        binding.apply {
            editTextName.setHint(R.string.name)
            editTextPalindrome.setHint(R.string.palindrome)
        }

        binding.btnNext.setOnClickListener {
            val text = binding.editTextName.text.toString().trim()
            if (!text.isEmpty()){
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(EXTRA_NAME, text)
                startActivity(intent)
            }else{
                showMessage(getString(R.string.name_input_cannot_be_empty))
            }

        }

        binding.btnCheck.setOnClickListener {
            val text = binding.editTextPalindrome.text.toString().trim()
            if(!text.isEmpty()){
                when(checkPalindrome(text)){
                    true-> showMessage(getString(R.string.ispalindrome))
                    false->showMessage(getString(R.string.not_palindrome))
                }
            }else{
                showMessage(getString(R.string.palindrome_input_cannot_be_empty))
            }
        }

    }

    private fun showMessage(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun checkPalindrome(input:String):Boolean{
        val cleanText = input.lowercase().filter { it.isLetterOrDigit() }
        val length = cleanText.length

        for (i in 0 until length / 2) {
            if (cleanText[i] != cleanText[length - i - 1]) {
                return false
            }
        }
        return true
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}