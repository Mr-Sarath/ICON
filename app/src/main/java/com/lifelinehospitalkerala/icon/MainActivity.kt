package com.lifelinehospitalkerala.icon

import android.content.ComponentName
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lifelinehospitalkerala.icon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews() {

        binding?.btnFirst?.setOnClickListener {
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, FirstActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, SecondActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, ThirdActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )

            Toast.makeText(
                this@MainActivity,
                "Launcher one has been applied successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding?.btnSecond?.setOnClickListener {
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, FirstActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, SecondActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, ThirdActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            Toast.makeText(
                this@MainActivity,
                "Launcher three has been applied successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding?.btnThird?.setOnClickListener {
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, FirstActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, SecondActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(this@MainActivity, ThirdActivity::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            Toast.makeText(
                this@MainActivity,
                "Launcher two has been applied successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}