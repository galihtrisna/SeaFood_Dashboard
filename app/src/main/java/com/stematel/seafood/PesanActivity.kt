package com.stematel.seafood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PesanActivity : AppCompatActivity() {

    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        hideSystemUI()

        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        buttonNavigationView.selectedItemId = R.id.pesan

        buttonNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.setting -> {
                    startActivity(
                        Intent(
                            applicationContext, SettingsActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.pesanan -> {
                    startActivity(
                        Intent(
                            applicationContext, PesananActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    startActivity(
                        Intent(
                            applicationContext, HomeActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.pesan -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        val back = findViewById<ImageButton>(R.id.back)

        back.setOnClickListener {
            Intent(this@PesanActivity,HomeActivity::class.java).also { intent ->
                startActivity(intent)
                finish()
                overridePendingTransition(0, 0)}
        }
    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(baseContext, "Tekan lagi untuk kembali", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()
    }
}