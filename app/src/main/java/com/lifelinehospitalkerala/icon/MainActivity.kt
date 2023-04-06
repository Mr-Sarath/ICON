package com.lifelinehospitalkerala.icon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import com.lifelinehospitalkerala.icon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        remoteConfig()
     }

    private fun remoteConfig() {
        try {
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 86400 //     24h
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
            remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Toast.makeText(this, "Config params updated: $updated", Toast.LENGTH_SHORT)
                            .show()
                        val jsonString = remoteConfig.getString("app_icon")
                        logThis(jsonString)
                        val model = Gson().fromJson(jsonString,IconModel::class.java)
                        logThis(model.name)

                        if (updated) {
                            when (model.name) {
                                "onam" -> {
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
                                "xmas"->{
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
                                "vishu"->{
                                    packageManager.setComponentEnabledSetting(
                                        ComponentName(this@MainActivity, SecondActivity::class.java),
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
                                        this@MainActivity, "Launcher two has been applied successfully", Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }


                        }
                    } else {
                        Toast.makeText(this, "Config params update failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


/*
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
                this@MainActivity, "Launcher one has been applied successfully", Toast.LENGTH_SHORT
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
                this@MainActivity, "Launcher two has been applied successfully", Toast.LENGTH_SHORT
            ).show()
        }

    }
*/




data class IconModel(
    val id: Int, val name: String
)

fun logThis(a:Any?){
    Log.d("logThis","    $a")
}
}