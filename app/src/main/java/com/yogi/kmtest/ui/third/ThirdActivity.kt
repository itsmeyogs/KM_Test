package com.yogi.kmtest.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.kmtest.R
import com.yogi.kmtest.data.remote.response.DataItem
import com.yogi.kmtest.databinding.ActivityThirdBinding
import com.yogi.kmtest.ui.ViewModelFactory
import com.yogi.kmtest.ui.second.SecondActivity
import com.yogi.kmtest.ui.utils.ItemClickListener
import com.yogi.kmtest.ui.utils.ResultState

class ThirdActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var binding: ActivityThirdBinding
    private val viewModel by viewModels<ThirdViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        getUsers()
        val dividerItemDecoration = DividerItemDecoration(this,LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divier)!!)
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration)
        }

    }

    override fun onItemClicked(userName: String) {
        val resultIntent = Intent()
        resultIntent.putExtra(SecondActivity.EXTRA_SELECTED_USER, userName)
        setResult(SecondActivity.RESULT_CODE, resultIntent)
        finish()

    }

    private fun getUsers(){
        viewModel.getAllUsers().observe(this) {
            when (it) {
                is ResultState.Loading ->{
                    showLoading(true)
                }
                is ResultState.Success ->{
                    showLoading(false)
                    setListUsers(it.data.data)
                }
                is ResultState.Error->{
                    showLoading(false)
                }
            }
        }

    }

    private fun setListUsers(listUsers:List<DataItem?>?){
        val adapter = UsersAdapter(this)
        adapter.submitList(listUsers)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}