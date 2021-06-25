package com.decagonhq.clads.ui.fragments.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentLoginScreenBinding
import com.decagonhq.clads.models.UserLogin
import com.decagonhq.clads.models.UserRole
import com.decagonhq.clads.ui.activities.DashboardActivity
import com.decagonhq.clads.ui.dialogs.ProgressBarDialogFragment
import com.decagonhq.clads.ui.fragments.authentication.SignupChoicesFragment.Companion.REQUEST_SIGN_IN
import com.decagonhq.clads.utils.LoginScreenFragmentValidator
import com.decagonhq.clads.utils.Resource
import com.decagonhq.clads.utils.SharedPreferenceManager
import com.decagonhq.clads.viewmodels.UserLoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginScreenFragment : Fragment() {
    // declaring variables to be initialized
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var newUserTextview: TextView
    private lateinit var loginButton: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private val userLoginViewModel: UserLoginViewModel by viewModels()
    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)

        // getting the reference of the user input edit text
        emailEditText = binding.fragmentLoginScreenEmailAddressTextInputEditText
        passwordEditText = binding.fragmentLoginScreenPasswordTextInputEditText
        loginButton = binding.fragmentLoginScreenLoginButton
        newUserTextview = binding.fragmentLoginScreenNewUserTextView
        textSpan()

        /* Onclick of the login button the user's input is converted to string and validated and if the user's
        *  input is correct the next fragment is launched else the user is notified which field is not
        * field properly
        */
        // setting login button on click listener to validate user credentials and login the user
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val user = UserLogin(email, password)
            if (LoginScreenFragmentValidator.validateEmail(email) && LoginScreenFragmentValidator.validatePassword(
                    password
                )
            ) {
                loginUser(user)
            } else {
                when {
                    !LoginScreenFragmentValidator.validatePassword(password) ->
                        passwordEditText.error =
                            "Please input correct password"

                    !LoginScreenFragmentValidator.validateEmail(email) ->
                        emailEditText.error =
                            "Please input correct Email"
                }
            }
        }
        // setting the login with google button on click listener to get user credentials and login the user
        binding.fragmentLoginScreenLoginWithGoogleMaterialButton.setOnClickListener {
            signIn()
        }

        newUserTextview.setOnClickListener {

            findNavController().navigate(R.id.action_fragment_login_screen_to_signup_choices_fragment)
        }

        binding.fragmentLoginScreenForgotPasswordTextView.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
        }
    }
    // function to login the user with email
    private fun loginUser(user: UserLogin) {
        userLoginViewModel.loginUser(user)
        val progressBarDialogFragment = ProgressBarDialogFragment()
        progressBarDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.login_fragment_progress_bar_text))
        userLoginViewModel.loginResponseLiveData.observe(
            viewLifecycleOwner,
            {
                when (it) {
                    is Resource.Success -> {
                        val result = it.value.payload
                        sharedPreferenceManager.saveToSharedPreference(getString(R.string.login_frament_login_token_text), result)
                        Toast.makeText(requireContext(), getString(R.string.login_fragment_login_successful_text), Toast.LENGTH_SHORT).show()
                        progressBarDialogFragment.dismiss()
                        val intent = Intent(requireContext(), DashboardActivity::class.java)
                        startActivity(intent)
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT)
                            .show()
                        progressBarDialogFragment.dismiss()
                    }
                }
            }
        )
    }

    // function to start the google sign in activity
    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_SIGN_IN)
    }

    // Result returned from launching the Intent from GoogleSignInClient
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    // handles the event of the result from launching the google sign in intent
    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = GoogleSignIn.getLastSignedInAccount(activity)
            if (account != null) {
                val tokenFromGoogle = account.idToken.toString()

                userLoginViewModel.loginWithGoogle("Bearer $tokenFromGoogle", UserRole("Tailor"))
                val progressBarDialog = ProgressBarDialogFragment()
                progressBarDialog.show(requireActivity().supportFragmentManager, getString(R.string.login_fragment_progress_bar_text))

                userLoginViewModel.loginWithGoogleLiveData.observe(
                    viewLifecycleOwner,
                    {
                        when (it) {
                            is Resource.Success -> {
                                if (it.value.status == 200) {
                                    val tokenFromEndpoint = it.value.payload
                                    sharedPreferenceManager.saveToSharedPreference(getString(R.string.login_frament_login_token_text), tokenFromEndpoint)
                                    Toast.makeText(
                                        requireContext(),
                                        tokenFromEndpoint,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Timber.d("$it")
                                    progressBarDialog.dismiss()
                                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.login_fragment_login_successful_text),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(intent)
                                } else {
                                    progressBarDialog.dismiss()
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.login_fragment_invalid_email_and_password_text),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            is Resource.Failure -> {
                                Toast.makeText(
                                    requireContext(),
                                    it.errorBody.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBarDialog.dismiss()
                                Timber.d("${it.errorBody}")
                            }
                        }
                    }
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_fragment_email_does_not_exist_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: ApiException) {
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    // this function is used to programmatically change the color style of a text in the layout file
    private fun textSpan() {
        val spannableString = SpannableString("New User? SignUp for free")
        val fcolor = ForegroundColorSpan(Color.WHITE)
        spannableString.setSpan(fcolor, 10, 25, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        newUserTextview.text = spannableString
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
