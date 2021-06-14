package com.decagonhq.clads.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads.fragments.profilemanagement.AccountFragment
import com.decagonhq.clads.fragments.profilemanagement.PaymentMethodFragment
import com.decagonhq.clads.fragments.profilemanagement.SecurityFragment
import com.decagonhq.clads.fragments.profilemanagement.SpecialtyFragment

class EditProfilePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AccountFragment()
            }
            1 -> {
                SpecialtyFragment()
            }
            2 -> {
                PaymentMethodFragment()
            }
            else -> {
                SecurityFragment()
            }
        }
    }
}
