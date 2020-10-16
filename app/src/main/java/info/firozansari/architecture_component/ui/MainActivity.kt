package info.firozansari.architecture_component.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.firozansari.architecture_component.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

}