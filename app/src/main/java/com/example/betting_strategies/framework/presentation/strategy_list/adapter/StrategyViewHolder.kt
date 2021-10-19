package com.example.betting_strategies.framework.presentation.strategy_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.betting_strategies.R
import com.example.betting_strategies.business.domain.model.DataItem
import com.example.betting_strategies.databinding.ItemStrategyOverviewBinding
import com.example.betting_strategies.framework.presentation.strategy_list.clicklisteners.ListFragmentClickListener

class StrategyViewHolder(private val binding: ItemStrategyOverviewBinding) : RecyclerView.ViewHolder(binding.root) {

    private var clickListener: ListFragmentClickListener? = null
    private var dataItem: DataItem? = null
    var isSwipeable = false
        private set


    fun onBind(s: DataItem?, clickListener: ListFragmentClickListener?) {
        dataItem = s
        isSwipeable = dataItem?.getIsFavorite() ?: false
        this.clickListener = clickListener
        binding.title.text = s?.id
        binding.description.text = s?.name
        binding.addToFavorites.setFill(s?.getIsFavorite() ?: false)
        loadImage(binding.imageView, s?.imageUrl)
    }

    private fun loadImage(view: ImageView, url: String?) =
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.shadow)
            .error(R.drawable.ic_launcher_background)
            .into(view)


    init {
        itemView.setOnClickListener { view: View? ->
            dataItem?.run { clickListener?.onClickGoToDetails(this) }
        }
        binding.addToFavorites.setOnClickListener { view: View? ->
            dataItem?.run { clickListener?.onClickFavorites(this) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): StrategyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemStrategyOverviewBinding.inflate(layoutInflater, parent, false)
            return StrategyViewHolder(binding)
        }
    }
}