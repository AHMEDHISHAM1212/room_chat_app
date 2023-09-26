package com.example.roomchatapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.SingleLiveEvent
import com.example.roomchatapp.ui.firestoreDB.RoomsDao
import com.example.roomchatapp.ui.model.Room

class HomeViewModel : ViewModel() {
    val messageLiveData = SingleLiveEvent<Message>()
    val events = SingleLiveEvent<HomeViewEvent>()
    val roomsLiveData = MutableLiveData<List<Room>>()


    fun navigateToCreateARoomActivity() {
        events.postValue(HomeViewEvent.NavigateToAddRoomActivity)
    }

    fun loadRooms() {
        RoomsDao.getAllRooms { task ->
            if (!task.isSuccessful) {
                //show message
                return@getAllRooms
            }
            val rooms = task.result.toObjects(Room::class.java)
            roomsLiveData.postValue(rooms)

        }

    }

}