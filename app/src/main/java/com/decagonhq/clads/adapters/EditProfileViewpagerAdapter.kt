package com.decagonhq.clads.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads.fragments.profilemanagement.TablayoutAccountFragment
import com.decagonhq.clads.fragments.profilemanagement.TablayoutPaymentMethodFragment
import com.decagonhq.clads.fragments.profilemanagement.TablayoutSecurityFragment
import com.decagonhq.clads.fragments.profilemanagement.TablayoutSpecialtyFragment

class EditProfileViewpagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    // returns the count of the fragments in the tab
    override fun getItemCount(): Int {
        return 4
    }
    // sets fragments to their respective positions
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TablayoutAccountFragment()
            1 -> TablayoutSpecialtyFragment()
            2 -> TablayoutPaymentMethodFragment()
            3 -> TablayoutSecurityFragment()
            else -> Fragment()
        }
    }
}
