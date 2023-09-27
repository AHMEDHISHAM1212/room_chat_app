package com.example.roomchatapp.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityHomeBinding
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.addRoom.AddRoomActivity
import com.example.roomchatapp.ui.login.LoginActivity
import com.example.roomchatapp.ui.model.Room
import com.example.roomchatapp.ui.showDialog
import com.example.roomchatapp.ui.showLoadingProgressBar

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
        viewModel.messageLiveData.observe(this, ::handelMessageLiveData)
        viewModel.loadingLiveData.observe(this, ::handelLoadingEvent)
        viewModel.events.observe(this, ::handelEvents)
        viewModel.roomsLiveData.observe(this, ::handelRoomsData)

    }

    private fun handelMessageLiveData(message: Message?) {
        showDialog(
            message = message?.message ?: "",
            posActionName = message?.posActionName,
            posAction = message?.posActionClick,
            negActionName = message?.negActionName,
            negAction = message?.negActionClick,
            isCancelable = message?.isCancelable ?: true
        )
    }

    private var loadingDialog: AlertDialog? = null
    private fun handelLoadingEvent(message: Message?) {
        if (message == null) {
            //hide
            loadingDialog?.dismiss()
            loadingDialog = null
            return
        }

        loadingDialog = showLoadingProgressBar(
            message = message.message ?: "Some thing went wrong ",
            isCancelable = message.isCancelable
        )
        loadingDialog?.show()


    }

    private fun handelRoomsData(rooms: List<Room>?) {
        adapter.changeRoomData(rooms)
    }

    private fun handelEvents(homeViewEvent: HomeViewEvent?) {
        when (homeViewEvent) {
            HomeViewEvent.NavigateToAddRoomActivity -> navigateToAddRoomActivity()

            HomeViewEvent.NavigateToLoginActivity -> navigateToLogin()

            else -> {}
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))

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