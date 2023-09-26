package com.example.roomchatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityHomeBinding
import com.example.roomchatapp.ui.addRoom.AddRoomActivity
import com.example.roomchatapp.ui.model.Room

class HomeActivity : AppCompatActivity() {
    private var viewBinding: ActivityHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadRooms()
    }

    private fun subscribeToLiveData() {
        viewModel.events.observe(this, ::handelEvents)
        viewModel.roomsLiveData.observe(this, ::handelRoomsData)
    }

    private fun handelRoomsData(rooms: List<Room>?) {
        adapter.changeRoomData(rooms)
    }

    private fun handelEvents(homeViewEvent: HomeViewEvent?) {
        when (homeViewEvent) {
            HomeViewEvent.NavigateToAddRoomActivity -> navigateToAddRoomActivity()


            else -> {}
        }
    }

    private fun navigateToAddRoomActivity() {
        startActivity(Intent(this, AddRoomActivity::class.java))
    }

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_home)


        viewBinding?.lifecycleOwner = this
        viewBinding?.vm = viewModel

        initRecyclerView()

    }

    private val adapter = RoomsRecyclerAdapter()
    private fun initRecyclerView() {
        viewBinding
            ?.contentHome
            ?.rvRooms?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}