package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentMeasurementBinding
import com.decagonhq.clads.databinding.FragmentSecurityBinding

class SecurityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMeasurementBinding.inflate(inflater).root
        return FragmentSecurityBinding.inflate(inflater).root
    }
}
