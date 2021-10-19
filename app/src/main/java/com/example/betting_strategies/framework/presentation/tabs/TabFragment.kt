package com.example.betting_strategies.framework.presentation.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.betting_strategies.business.domain.model.DataItem
import com.example.betting_strategies.databinding.FragmentTabsBinding
import com.example.betting_strategies.framework.presentation.main.multifeature.MainViewModelFactory
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class TabFragment @Inject constructor(
    private val viewModelFactory: MainViewModelFactory
) : Fragment(), TabFragmentNavigator {

    private var fragmentTabsBinding: FragmentTabsBinding? = null

    val viewModel: TabsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTabsBinding = FragmentTabsBinding.inflate(inflater, container, false)
        return fragmentTabsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dasdas", "tab onViewCreated")
        val cntx = context ?: return
        val sectionsPagerAdapter = SectionsPagerAdapter(cntx, childFragmentManager, viewModel)
        val viewPager: ViewPager = fragmentTabsBinding?.viewPager ?: return
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = fragmentTabsBinding?.tabs ?: return
        tabs.setupWithViewPager(viewPager)
    }

    private fun navigateToDetails(dataItem: DataItem) = findNavController().navigate(
        TabFragmentDirections.actionTabsToDetails(dataItem)
    )

    override fun goToDetails(dataItem: DataItem) {
        navigateToDetails(dataItem)
    }
}