package com.decagonhq.clads.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads.fragments.profilemanagement.ClientAccountFragment
import com.decagonhq.clads.fragments.profilemanagement.DeliveryAddressListFragment
import com.decagonhq.clads.fragments.profilemanagement.MeasurementFragment

class AddClientViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }
    // the viewpager will have 3 fragments
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ClientAccountFragment()
            }
            1 -> {
                MeasurementFragment()
            }
            else -> { DeliveryAddressListFragment() }
        }
    }
}
