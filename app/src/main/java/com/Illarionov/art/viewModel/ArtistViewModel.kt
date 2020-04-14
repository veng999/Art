package com.Illarionov.art.viewModel

import android.content.Intent
import android.view.MenuItem
import com.Illarionov.art.MainActivity
import com.Illarionov.art.R
import com.Illarionov.art.view.ArtistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ArtistViewModel() : BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_newsFeed -> {

                /*val intent = Intent(this, ArtistFragment::class.java)*/
                return true
            }


            else -> false
        }
    }



}



