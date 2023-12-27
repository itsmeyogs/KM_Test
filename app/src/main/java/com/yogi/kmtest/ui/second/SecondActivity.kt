package com.yogi.kmtest.ui.second

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.yogi.kmtest.databinding.ActivitySecondBinding
import com.yogi.kmtest.ui.main.MainActivity
import com.yogi.kmtest.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == RESULT_CODE && it.data != null){
            val userName = it.data?.getStringExtra(EXTRA_SELECTED_USER)
            binding.tvSelectedUserName.text = userName.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)

        binding.tvName.text = name

        binding.topAppBar.setNavigationOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    companion object{
        const val EXTRA_SELECTED_USER = "extra_selected_user"
        const val RESULT_CODE = 110
    }
}