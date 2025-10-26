package com.example.notes.ui.on_board.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.models.OnBoardModel
import com.example.notes.databinding.ItemOnBoardBinding

class OnBoardAdapter(private val onBoardList: List<OnBoardModel>, val onStart:()-> Unit): RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false,
            )
        )
    }

    override fun onBindViewHolder(
        holder: OnBoardViewHolder,
        position: Int
    ) {
        holder.onBind(onBoardList[position])
    }

    override fun getItemCount(): Int {
        return onBoardList.size
    }

  inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(onBoard: OnBoardModel){
            binding.theme.text = onBoard.theme
            binding.desc.text = onBoard.desc
            binding.lottie.setAnimation(onBoard.lottie)
            binding.lottie.playAnimation()
            if (adapterPosition == (onBoardList.size-1)){
                binding.skip.visibility = View.INVISIBLE
                binding.btnStart.setOnClickListener {
                    onStart()
                }
            }else{
                binding.btnStart.visibility = View.INVISIBLE
                binding.skip.setOnClickListener {
                    onStart()
                }
            }
        }
}}