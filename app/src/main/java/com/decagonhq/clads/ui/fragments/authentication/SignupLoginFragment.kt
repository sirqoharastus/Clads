package com.decagonhq.clads.ui.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentSignupLoginBinding

class SignupLoginFragment : Fragment() {
    // Declare binding variable
    private var _binding: FragmentSignupLoginBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentSignupSignupButton.setOnClickListener {
            val action =
                SignupLoginFragmentDirections.actionSignupLoginFragmentToSignupChoicesFragment()
            findNavController().navigate(action)
        }
        binding.fragmentSignupLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signup_login_fragment_to_fragment_login_screen)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
