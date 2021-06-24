package com.decagonhq.clads.fragments.resourcemanagement

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentReadArticlesBinding

class ReadArticlesFragment : Fragment() {
    private var _binding: FragmentReadArticlesBinding? = null
    private val binding get() = _binding!!

    private val webView: WebView by lazy { binding.readArticlesFragmentWebView }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadArticlesBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.webViewClient = WebViewClient()

//        webView.webViewClient = object : WebViewClient() {}
        webView.loadUrl("https://www.google.com")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                isEnabled = false
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
