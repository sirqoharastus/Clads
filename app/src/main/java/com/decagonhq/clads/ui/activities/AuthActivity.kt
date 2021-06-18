package com.decagonhq.clads.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.decagonhq.clads.databinding.ActivityCladsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCladsBinding.inflate(layoutInflater).root)
    }
}
