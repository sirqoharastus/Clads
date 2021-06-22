package com.decagonhq.clads.ui.fragments.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentEmailVerificationBinding

class EmailVerificationFragment : Fragment() {

    // using binding
    private var preBinding: FragmentEmailVerificationBinding? = null
    private val binding: FragmentEmailVerificationBinding get() = preBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        preBinding = FragmentEmailVerificationBinding.inflate(inflater, container, false)
        return preBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // to navigate to the login activity
        binding.emailVerificationFragmentVerifyEmailAddressButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.apply {
                addCategory(Intent.CATEGORY_APP_EMAIL)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            startActivity(Intent.createChooser(intent, "Email"))
            // findNavController().navigate(R.id.action_emailVerificationFragment_to_loginFragment)
        }
    }
}
