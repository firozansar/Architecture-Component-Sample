package info.firozansari.architecture_component.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.firozansari.architecture_component.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}