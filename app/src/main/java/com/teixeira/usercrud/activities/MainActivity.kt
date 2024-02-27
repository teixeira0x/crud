package com.teixeira.usercrud.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.teixeira.usercrud.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  private var _binding: ActivityMainBinding? = null
  private val binding: ActivityMainBinding
    get() = checkNotNull(_binding) { "Activity has been destroyed!" }

  override fun bindView(): View {
    _binding = ActivityMainBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)

    val navHostFragment =
      supportFragmentManager.findFragmentById(binding.navHostFragment.id)
        as NavHostFragment
    NavigationUI.setupWithNavController(
      binding.toolbar,
      navHostFragment.navController
    )
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
