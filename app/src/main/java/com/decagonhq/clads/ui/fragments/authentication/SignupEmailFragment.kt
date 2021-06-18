package com.decagonhq.clads.ui.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentSignupEmailBinding
import com.decagonhq.clads.models.User
import com.decagonhq.clads.utils.Resource
import com.decagonhq.clads.utils.SignUpEmailFragmentValidator
import com.decagonhq.clads.viewmodels.UserRegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupEmailFragment : Fragment() {

    private var _binding: FragmentSignupEmailBinding? = null
    private val binding get() = _binding!!
    private val userRegistrationViewModel: UserRegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment and return the view
        _binding = FragmentSignupEmailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setUpObservers()
    }

    // bind the views
    private fun setUpUI() {

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.email_sign_up_fragment_choose_account_category_array,
            R.layout.signup_email_autotextview
        ).also { adapter -> // Specifies the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.emailSignUpFragmentAccountCategoryAutoTextView.setAdapter(adapter) // Applies the adapter to the spinner
        }

        binding.emailSignUpFragmentSignUpButton.apply {
            setOnClickListener {
                if (validateFields()) {

                    userRegistrationViewModel.registerUser(
                        User(
                            category = "Tailor",
                            deliveryAddress = "address",
                            email = "Jonesbenklins@gmail.com",
                            firstName ="Jones",
                            lastName = "Omoyibo",
                            password =  "Benklins123",
                            phoneNumber = "08168105123",
                            role = "Tailor",
                            gender="Nigeria")
                    )

                }
            }
        }

        binding.emailSignUpFragmentLoginTextView.apply {
            setOnClickListener{
                findNavController().navigate(R.id.action_signup_email_fragment_to_fragment_login_screen)
            }
        }

        // register a text change listener on the fields to clear error meesages
        binding.emailSignUpFragmentEmailAddressTextInputEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailSignUpFragmentEmailAddressTextInputLayout.error = ""
        }
        binding.emailSignUpFragmentPasswordTextInputEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailSignUpFragmentPasswordTextInputLayout.error = ""
        }
        binding.emailSignUpFragmentConfirmPasswordTextInputEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.error = ""
        }
        binding.emailSignUpFragmentFirstnameTextInputEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailSignUpFragmentFirstnameTextInputLayout.error = ""
        }
        binding.emailSignUpFragmentLastnameTextInputEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailSignUpFragmentLastnameTextInputLayout.error = ""
        }
        binding.emailSignUpFragmentAccountCategoryAutoTextView.apply {
            doOnTextChanged { _, _, _, _ ->
                binding.emailSignUpFragmentAccountCategoryTextInputLayout.error = ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Validation of fields
    private fun validateFields(): Boolean {

        var isFieldValidated = true

        when {
            (!SignUpEmailFragmentValidator.nameValidator(binding.emailSignUpFragmentFirstnameTextInputEditText.text.toString())) -> {
                binding.emailSignUpFragmentFirstnameTextInputLayout.error =
                    "Field is empty or invalid name format entered"
                isFieldValidated = false
            }
            else -> {
                binding.emailSignUpFragmentFirstnameTextInputLayout.error = ""
            }
        }

        if (!SignUpEmailFragmentValidator.nameValidator(binding.emailSignUpFragmentLastnameTextInputEditText.text.toString())) {
            binding.emailSignUpFragmentLastnameTextInputLayout.error =
                "Field is empty or invalid name format entered"
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.emailValidator(
                binding.emailSignUpFragmentEmailAddressTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentEmailAddressTextInputLayout.error =
                "Field is empty or Invalid email address entered"
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.accountCategoryValidator(binding.emailSignUpFragmentAccountCategoryAutoTextView.text.toString())) {

            binding.emailSignUpFragmentAccountCategoryTextInputLayout.error =
                "Select an account type"
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordValidator(
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentPasswordTextInputLayout.error =
                "Password must not be less than 6 characters"
            binding.emailSignUpFragmentPasswordTextInputLayout.errorIconDrawable = null
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordValidator(
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.error =
                "Password must not be less than 6 characters"
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.errorIconDrawable = null
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordsMatcher(
                binding.emailSignUpFragmentPasswordTextInputEditText.text.toString(),
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )

        ) {

            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.error =
                "Passwords do not match"
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.errorIconDrawable = null
            isFieldValidated = false
        }

        return isFieldValidated
    }

    private fun setUpObservers() {
        userRegistrationViewModel.user.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is Resource.Success -> {
                        val result = it.value.payload

                        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_signupEmailFragment_to_emailVerificationFragment)
                    }

                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "NOT WORRRKIING", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

//    private fun createUser() = User(
//            category = "Tailor",
//            deliveryAddress = "address",
//            email = "Jonesbenklins@gmail.com",
//            firstName ="Jones",
//            lastName = "Omoyibo",
//            password =  "Benklins@123",
//            phoneNumber = "08168105123",
//            role = "Tailor",
//            gender="Nigeria")

}
