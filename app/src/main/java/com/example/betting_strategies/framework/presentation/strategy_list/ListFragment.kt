package com.example.betting_strategies.framework.presentation.strategy_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.betting_strategies.business.domain.model.DataItem
import com.example.betting_strategies.databinding.FragmentListBinding
import com.example.betting_strategies.framework.presentation.strategy_list.adapter.StrategyAdapter
import com.example.betting_strategies.framework.presentation.strategy_list.clicklisteners.ListFragmentClickListener
import com.example.betting_strategies.framework.presentation.tabs.TabsViewModel
import java.lang.Exception

class ListFragment : Fragment() {

    private var fragmentListBinding: FragmentListBinding? = null

    private var strategyAdapter: StrategyAdapter? = null
    private var viewModel: TabsViewModel? = null

    private val addToFavoritesClickListener: ListFragmentClickListener =
        object : ListFragmentClickListener {
            override fun onClickFavorites(dataItem: DataItem) {
                if (dataItem.isFavorite) {
                    viewModel?.removeFromFavorites(dataItem)
                } else {
                    viewModel?.addToFavorites(dataItem)
                }
            }

            override fun onClickGoToDetails(dataItem: DataItem) {
                viewModel?.navigateToDetails(dataItem)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false)
        return fragmentListBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("dasdas", "onViewCreated")
        strategyAdapter = StrategyAdapter()
        strategyAdapter?.setClickListener(addToFavoritesClickListener)
        fragmentListBinding?.recyclerView?.adapter = strategyAdapter

        val isFavorite = arguments?.getBoolean(ARG_IS_FAVORITE) ?: false

        if (isFavorite) {
            fragmentListBinding?.let { setSwipe(it) }
        }

        viewModel?.getDataList(isFavorite)?.observe(viewLifecycleOwner) { dataItems: List<DataItem?>? ->
            strategyAdapter?.updateItems(dataItems)
        }
    }

    fun setViewModel(viewModel: TabsViewModel) {
        this.viewModel = viewModel
    }

    private fun setSwipe(binding: FragmentListBinding) {
        val swipeHandler: SwipeToDeleteCallback =
            object : SwipeToDeleteCallback(binding.root.context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    try {

                        val isFavorite = arguments?.getBoolean(ARG_IS_FAVORITE) ?: false

                        viewModel?.getDataList(isFavorite)?.value?.get(viewHolder.adapterPosition)?.run {
                            viewModel!!.removeFromFavorites(this)
                        }

                    } catch (e: Exception) {
                        Log.w("FavListFragment", e)
                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(fragmentListBinding!!.recyclerView)
    }

    companion object {

        private const val ARG_IS_FAVORITE = "ARG_IS_FAVORITE"

        @JvmStatic
        fun newInstance(isFavorite: Boolean): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_FAVORITE, isFavorite)
                }
            }
        }

    }
}