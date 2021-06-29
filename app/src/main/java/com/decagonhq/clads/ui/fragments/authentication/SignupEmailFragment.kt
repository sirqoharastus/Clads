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
import com.decagonhq.clads.utils.handleApiErrors
import com.decagonhq.clads.utils.snackbar
import com.decagonhq.clads.viewmodels.UserRegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class SignupEmailFragment : Fragment() {

    private var _binding: FragmentSignupEmailBinding? = null
    private val binding get() = _binding!!
    private val userRegistrationViewModel: UserRegistrationViewModel by viewModels()
    @Inject
    lateinit var retrofit: Retrofit

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
                        createUser()
                    )
                }
            }
        }

        binding.emailSignUpFragmentLoginTextView.apply {
            setOnClickListener {
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
                    resources.getString(R.string.Field_is_empty_or_invalid_name_format_entered)
                isFieldValidated = false
            }
            else -> {
                binding.emailSignUpFragmentFirstnameTextInputLayout.error = ""
            }
        }

        if (!SignUpEmailFragmentValidator.nameValidator(binding.emailSignUpFragmentLastnameTextInputEditText.text.toString())) {
            binding.emailSignUpFragmentLastnameTextInputLayout.error =
                resources.getString(R.string.Field_is_empty_or_invalid_name_format_entered)
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.emailValidator(
                binding.emailSignUpFragmentEmailAddressTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentEmailAddressTextInputLayout.error =
                resources.getString(R.string.Field_is_empty_or_Invalid_email_address_entered)
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.accountCategoryValidator(binding.emailSignUpFragmentAccountCategoryAutoTextView.text.toString())) {

            binding.emailSignUpFragmentAccountCategoryTextInputLayout.error =
                resources.getString(R.string.Select_an_account_type)
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordValidator(
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentPasswordTextInputLayout.error =
                resources.getString(R.string.Password_must_not_be_less_than_6_characters)
            binding.emailSignUpFragmentPasswordTextInputLayout.errorIconDrawable = null
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordValidator(
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )
        ) {
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.error =
                resources.getString(R.string.Password_must_not_be_less_than_6_characters)
            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.errorIconDrawable = null
            isFieldValidated = false
        }

        if (!SignUpEmailFragmentValidator.passwordsMatcher(
                binding.emailSignUpFragmentPasswordTextInputEditText.text.toString(),
                binding.emailSignUpFragmentConfirmPasswordTextInputEditText.text.toString()
            )

        ) {

            binding.emailSignUpFragmentConfirmPasswordTextInputLayout.error =
                resources.getString(R.string.Passwords_do_not_match)
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
                        requireView().snackbar(getString(R.string.registration_successful_text))

                        findNavController().navigate(R.id.action_signupEmailFragment_to_emailVerificationFragment)
                    }

                    is Resource.Failure -> {
                        handleApiErrors(it, retrofit, requireView())
                    }
                }
            }
        )
    }

    private fun createUser() = User(
        category = binding.emailSignUpFragmentAccountCategoryAutoTextView.text.toString(),
        deliveryAddress = resources.getString(R.string.delivery_address),
        email = binding.emailSignUpFragmentEmailAddressTextInputEditText.text.toString(),
        firstName = binding.emailSignUpFragmentFirstnameTextInputEditText.text.toString(),
        lastName = binding.emailSignUpFragmentLastnameTextInputEditText.text.toString(),
        password = binding.emailSignUpFragmentPasswordTextInputEditText.text.toString(),
        phoneNumber = resources.getString(R.string.phone_number),
        role = resources.getString(R.string.role),
        gender = resources.getString(R.string.gender),
        country = resources.getString(R.string.country),
    )
}
