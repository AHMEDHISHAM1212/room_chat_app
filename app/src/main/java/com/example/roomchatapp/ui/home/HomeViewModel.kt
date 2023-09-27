package com.example.roomchatapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.SessionProvider
import com.example.roomchatapp.ui.SingleLiveEvent
import com.example.roomchatapp.ui.firestoreDB.RoomsDao
import com.example.roomchatapp.ui.model.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {
    val messageLiveData = SingleLiveEvent<Message>()
    val loadingLiveData = SingleLiveEvent<Message?>()
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

    fun logout() {
        messageLiveData.postValue(
            Message(
                message = "Are you sure to logout?",
                posActionName = "yes",
                posActionClick = {
                    loadingLiveData.postValue(
                        Message(
                            message = "SignOut..",
                            isCancelable = false
                        )
                    )
                    //signOut
                    Firebase.auth.signOut()
                    //delete user details from SessionProvider
                    SessionProvider.user = null
                    //navigate
                    events.postValue(HomeViewEvent.NavigateToLoginActivity)
                },
                negActionName = "Cancel"
            )
        )

    }

}