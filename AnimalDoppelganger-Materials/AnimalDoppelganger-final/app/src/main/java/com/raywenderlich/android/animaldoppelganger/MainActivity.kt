/*
 * Copyright (c) 2020 Razeware LLC
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
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.animaldoppelganger

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private lateinit var doppelgangerNamesArray: Array<String>

  //TODO:4 Define page change callback here
  private var doppelgangerPageChangeCallback = object : OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
      Toast.makeText(this@MainActivity,
          "Selected position: $position",
          Toast.LENGTH_SHORT).show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    doppelgangerNamesArray = resources.getStringArray(R.array.doppelganger_names)

    //TODO:3 Wire DoppelgangerAdapter with ViewPager2 here
    val doppelgangerAdapter = DoppelgangerAdapter(this, doppelgangerNamesArray.size)
    doppelgangerViewPager.adapter = doppelgangerAdapter

    //TODO:5 Register page change callback here
    doppelgangerViewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)

    //TODO:7 Change ViewPager2 orientation here
//    doppelgangerViewPager.orientation = ORIENTATION_VERTICAL

    //TODO:10 Connect TabLayout and ViewPager2 here
    TabLayoutMediator(tabLayout, doppelgangerViewPager) { tab, position ->
      //To get the first name of doppelganger celebrities
      tab.text = doppelgangerNamesArray[position].substringBefore(' ')
    }.attach()

    //TODO:11 Force to RTL mode
//    doppelgangerViewPager.layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL
//    tabLayout.layoutDirection = View.LAYOUT_DIRECTION_RTL
  }

  override fun onDestroy() {
    super.onDestroy()
    //TODO:6 Unregister page change callback here
    doppelgangerViewPager.unregisterOnPageChangeCallback(doppelgangerPageChangeCallback)
  }
}
