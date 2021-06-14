package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.adapters.EditProfileViewPagerAdapter
import com.decagonhq.clads.databinding.FragmentEditProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class EditProfileFragment : Fragment() {
    var _binding: FragmentEditProfileBinding? = null
    val binding get() = _binding!!

    private lateinit var editProfileBinding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI() {

        val viewpager = binding.fragmentEditProfileViewPager
        val tablayout = binding.fragmentEditProfileTablayout
        val adapter = EditProfileViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewpager.adapter = adapter
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.edit_profile_fragment_account_text)
                1 -> tab.text = getString(R.string.edit_profile_fragment_specialty_text)
                2 -> tab.text = getString(R.string.edit_profile_fragment_payment_method_text)
                3 -> tab.text = getString(R.string.edit_profile_fragment_security_text)
            }
        }.attach()
    }
}
