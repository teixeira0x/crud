package com.teixeira.usercrud.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.R
import com.google.android.material.color.MaterialColors

abstract class BaseActivity : AppCompatActivity() {

  protected open val statusBarColor
    get() = MaterialColors.getColor(this, R.attr.colorSurface, 0)

  protected open val navigationBarColor
    get() = MaterialColors.getColor(this, R.attr.colorSurface, 0)

  override fun onCreate(savedInstanceState: Bundle?) {
    window?.apply {
      this.statusBarColor = this@BaseActivity.statusBarColor
      this.navigationBarColor = this@BaseActivity.navigationBarColor
    }
    super.onCreate(savedInstanceState)
    setContentView(bindView())
  }

  protected abstract fun bindView(): View
}
