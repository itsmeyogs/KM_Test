package com.yogi.kmtest.ui.third

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.kmtest.data.remote.response.DataItem
import com.yogi.kmtest.databinding.ItemUserBinding
import com.yogi.kmtest.ui.utils.ItemClickListener

class UsersAdapter(private val itemClickListener: ItemClickListener): ListAdapter<DataItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users =getItem(position)
        holder.bind(users)
        Glide.with(holder.itemView)
            .load("${users.avatar}")
            .into(holder.binding.ivProfileUser)

        holder.itemView.setOnClickListener {
            val userName = "${users.firstName} ${users.lastName}"
            itemClickListener.onItemClicked(userName)

        }
    }

    class MyViewHolder(val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(users:DataItem){
            binding.apply {
                tvNameUser.text = "${users.firstName} ${users.lastName}"
                tvEmailUser.text = users.email
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataItem>(){
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem==newItem
            }

            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem==newItem
            }
        }
    }
}