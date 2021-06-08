package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.adapters.EditProfileViewpagerAdapter
import com.decagonhq.clads.databinding.FragmentEditProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class EditProfileFragment : Fragment() {
    var _binding: FragmentEditProfileBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewpager = binding.editProfileViewpager2
        val tablayout = binding.editProfileTablayout
        val adapter = EditProfileViewpagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewpager.adapter = adapter
        TabLayoutMediator(tablayout, viewpager){ tab, position ->
            when (position) {
                0 -> tab.text = "Account"
                1 -> tab.text = "Specialty"
                2 -> tab.text = "Payment method"
                3 -> tab.text = "Security"
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
