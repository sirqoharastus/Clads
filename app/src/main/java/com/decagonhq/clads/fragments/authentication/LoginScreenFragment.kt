package com.decagonhq.clads.fragments.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.activities.DashboardActivity
import com.decagonhq.clads.databinding.FragmentLoginScreenBinding
import com.decagonhq.clads.fragments.authentication.SignupChoicesFragment.Companion.REQUEST_SIGN_IN
import com.decagonhq.clads.utils.LoginScreenFragmentValidator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText

class LoginScreenFragment : Fragment() {
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var newUserTextview: TextView
    private lateinit var loginButton: Button
    private lateinit var googleSignInClient: GoogleSignInClient

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

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (LoginScreenFragmentValidator.validateEmail(email) && LoginScreenFragmentValidator.validatePassword(
                    password
                )
            ) {
                startActivity(Intent(requireContext(), DashboardActivity::class.java))
            } else {
                when {
                    !LoginScreenFragmentValidator.validatePassword(password) -> passwordEditText.error = "Please input correct password"

                    !LoginScreenFragmentValidator.validateEmail(email) -> emailEditText.error = "Please input correct Email"
                }
            }
        }

        binding.fragmentLoginScreenLoginWithGoogleMaterialButton.setOnClickListener {
//            googleSignIn()
            signIn()
        }
    }

//    private fun googleSignIn() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, SignupChoicesFragment.REQUEST_SIGN_IN)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == SignupChoicesFragment.REQUEST_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
//                Timber.d("firebaseAuthWithGoogle: ${account.id}")
// //                account.email?.let { updateUi(it) }
//                if (account != null) {
//                    val intent = Intent(requireActivity(), DashboardActivity::class.java)
//                    startActivity(intent)
//                }
//            } catch (e: ApiException) {
//                Toast.makeText(
//                    requireContext(), "Login Failed: ${e.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }

//    override fun onStart() {
//        super.onStart()
//        val intent = Intent(requireActivity(), DashboardActivity::class.java)
//        startActivity(intent)
//    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        val intent = Intent(requireActivity(), DashboardActivity::class.java)
        if (account != null) {
            intent.putExtra("name", account.displayName)
            binding.fragmentLoginScreenLoginWithGoogleMaterialButton.visibility = View.GONE
            startActivity(intent)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == REQUEST_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
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
