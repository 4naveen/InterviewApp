package com.naveen.interviewapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*
import android.graphics.Typeface
import android.view.WindowManager
import android.os.Build




class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT :Long= 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        text1.text="Welcome to Interview Q & A"
        text2.text="A Way to Success"
        val tf1 = Typeface.createFromAsset(assets, "fonts/roboto.light-italic.ttf")
        val tf2 = Typeface.createFromAsset(assets, "fonts/roboto-bold.ttf")
        changeStatusBarColor()
        text1.setTypeface(tf2)
        text2.setTypeface(tf1)
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val i = Intent(this@SplashActivity, QuesListActivity::class.java)
            startActivity(i)
            finish()

        }, SPLASH_TIME_OUT)
    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.rgb(0, 118, 188)
        }
    }
}
