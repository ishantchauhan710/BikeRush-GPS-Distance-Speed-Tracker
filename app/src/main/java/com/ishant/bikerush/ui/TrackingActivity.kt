package com.ishant.bikerush.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.gms.maps.GoogleMap
import com.ishant.bikerush.R
import com.ishant.bikerush.databinding.ActivityTrackingBinding
import com.ishant.bikerush.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.ishant.bikerush.services.TrackingService

class TrackingActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrackingBinding

    // Google Map Instance
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // Set up mapView
        binding.mapView.onCreate(savedInstanceState)

        binding.mapView.getMapAsync {
            map = it // Get map asynchronously and assign the result to our map (google map instance) we created on top
        }

        binding.btnStartService.setOnClickListener {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }


    }

    // This function will start our service and send an action to it
    private fun sendCommandToService(action: String) = Intent(this,TrackingService::class.java).also {
        it.action = action
        startService(it)
    }


    // Following are the functions to handle the lifecycle of our map. Removing these functions may cause the app to crash.
    override fun onResume() {
        super.onResume()
        binding.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView?.onSaveInstanceState(outState)
    }


}