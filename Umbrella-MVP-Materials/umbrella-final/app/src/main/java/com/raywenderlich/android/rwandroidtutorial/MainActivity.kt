/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.rwandroidtutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView

// 1
class MainActivity : AppCompatActivity(), MainContract.View {
  internal lateinit var imageView: ImageView
  internal lateinit var button: Button

  // 2
  internal lateinit var presenter: MainContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageView = findViewById(R.id.imageView)
    button = findViewById(R.id.button)

    // 3
    setPresenter(MainPresenter(this, DependencyInjectorImpl()))
    presenter.onViewCreated()

    // 4
    button.setOnClickListener { presenter.onLoadWeatherTapped() }
  }

  // 5
  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  // 6
  override fun setPresenter(presenter: MainContract.Presenter) {
    this.presenter = presenter
  }

  // 7
  override fun displayWeatherState(weatherState: WeatherState) {
    val drawable = resources.getDrawable(weatherDrawableResId(weatherState),
        applicationContext.getTheme())
    this.imageView.setImageDrawable(drawable)
  }

  fun weatherDrawableResId(weatherState: WeatherState) : Int {
    return when (weatherState) {
      WeatherState.SUN -> R.drawable.ic_sun
      WeatherState.RAIN -> R.drawable.ic_umbrella
    }
  }
}



