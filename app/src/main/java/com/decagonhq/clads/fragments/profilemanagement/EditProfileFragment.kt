package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.adapters.EditProfileViewPagerAdapter
import com.decagonhq.clads.databinding.FragmentEditProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class EditProfileFragment : Fragment() {

    private lateinit var editProfileBinding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        editProfileBinding = FragmentEditProfileBinding.inflate(inflater)
        return editProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI() {

        editProfileBinding.editProfileViewPager.apply {

            adapter = EditProfileViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        }

        TabLayoutMediator(
            editProfileBinding.editProfileLayout, editProfileBinding.editProfileViewPager
        ) { tab, position ->

            when (position) {
                0 -> tab.text = "Account"
                1 -> tab.text = "Specialty"
                2 -> tab.text = "Payment method"
                else -> tab.text = "Security"
            }
        }.attach()
    }
}
