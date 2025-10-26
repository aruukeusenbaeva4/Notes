package com.example.notes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        pref = Pref(this)
        AppKey.pref = Pref(this)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val navGraph = navHost.navController.navInflater.inflate(R.navigation.nav_host)

        navGraph.setStartDestination(
            if (pref.isUserSeen()){
                R.id.mainFragment
            }else {
                R.id.onBoardFragment
            }
        )
        navHost.navController.graph = navGraph

    }
}