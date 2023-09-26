package com.example.roomchatapp.ui.addRoom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.SessionProvider
import com.example.roomchatapp.ui.SingleLiveEvent
import com.example.roomchatapp.ui.firestoreDB.RoomsDao
import com.example.roomchatapp.ui.model.Category

class AddRoomViewModel : ViewModel() {
    val loadingLiveData = SingleLiveEvent<Message?>()
    val event = SingleLiveEvent<AddRoomViewEvent>()
    val messageLiveData = SingleLiveEvent<Message>()
    val roomName = MutableLiveData<String>()
    val roomDesc = MutableLiveData<String>()

    val roomNameError = MutableLiveData<String>()
    val roomDescError = MutableLiveData<String>()

    val categories = Category.getCategories()
    var selectedCategory = categories[0]

    private fun validForm(): Boolean {

        var isValid = true
        if (roomName.value.isNullOrBlank()) {
            roomNameError.postValue("Enter Room Name")
            isValid = false
        } else {
            roomNameError.postValue("")
        }
        if (roomDesc.value.isNullOrBlank()) {
            roomDescError.postValue("Enter Room Name")
            isValid = false
        } else {
            roomDescError.postValue("")
        }

        return isValid
    }

    fun createRoom() {

        if (!validForm()) return

        // create your room
        loadingLiveData.postValue(
            Message(
                message = "Loading...",
                isCancelable = false
            )
        )
        RoomsDao.createRoom(
            title = roomName.value ?: "",
            desc = roomDesc.value ?: "",
            ownerId = SessionProvider.user?.id ?: "",
            selectedCategoryId = selectedCategory.id
        ) { task ->
            if (task.isSuccessful) {
                //hide loading
                loadingLiveData.postValue(null)
                //show message
                messageLiveData.postValue(
                    Message(
                        message = "Room created Successfully",
                        posActionName = "Ok",
                        posActionClick = {
                            event.postValue(AddRoomViewEvent.NavigateToHomeAndFinish)
                        }
                    )
                )
                return@createRoom
            }
            messageLiveData.postValue(
                Message(
                    message = "Something went wrong ",
                    posActionName = "OK"
                )
            )

        }

    }

    fun onCategorySelected(position: Int) {
        selectedCategory = categories[position]
    }
}