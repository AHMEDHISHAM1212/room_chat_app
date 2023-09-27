package com.example.roomchatapp.ui.addRoom

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityAddRoomBinding
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.home.HomeActivity
import com.example.roomchatapp.ui.showDialog
import com.example.roomchatapp.ui.showLoadingProgressBar

class AddRoomActivity : AppCompatActivity() {
    var viewBinding: ActivityAddRoomBinding? = null
    private val viewModel: AddRoomViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() {
        viewModel.messageLiveData.observe(this, ::handelMessageLiveData)
        viewModel.event.observe(this, ::handelEvents)
        viewModel.loadingLiveData.observe(this, ::handelLoadingEvent)
    }

    private fun handelMessageLiveData(message: Message?) {
        showDialog(
            message = message?.message ?: "Some thing went wrong ",
            posActionName = message?.posActionName,
            posAction = message?.posActionClick,
            negActionName = message?.negActionName,
            negAction = message?.negActionClick,
            isCancelable = message?.isCancelable ?: true
        )
    }

    private var loadingDialog: ProgressDialog? = null
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

    private fun handelEvents(addRoomViewEvent: AddRoomViewEvent?) {
        when (addRoomViewEvent) {
            AddRoomViewEvent.NavigateToHomeAndFinish -> {
//                navigateToHomeAct()
                finish()
            }

            else -> {}
        }
    }

    private fun navigateToHomeAct() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_add_room)

        viewBinding?.lifecycleOwner = this
        viewBinding?.vm = viewModel

        initSpinner()


    }

    private lateinit var categoryAdapter: RoomCategoryAdapter
    private fun initSpinner() {
        categoryAdapter = RoomCategoryAdapter(viewModel.categories)

        viewBinding?.contentAddRoom
            ?.spinnerCategory
            ?.adapter = categoryAdapter

        //selected of the item on the spinner
        viewBinding
            ?.contentAddRoom
            ?.spinnerCategory
            ?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    itemView: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.onCategorySelected(position)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }
}