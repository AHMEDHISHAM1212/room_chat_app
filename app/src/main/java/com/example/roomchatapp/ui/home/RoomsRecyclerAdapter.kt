package com.example.roomchatapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomchatapp.databinding.ItemRoomBinding
import com.example.roomchatapp.ui.model.Category
import com.example.roomchatapp.ui.model.Room

class RoomsRecyclerAdapter(var roomItem: List<Room>? = listOf()) :
    RecyclerView.Adapter<RoomsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = ItemRoomBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(roomItem?.get(position))

    }

    override fun getItemCount(): Int {
        return roomItem?.size ?: 0

    }

    fun changeRoomData(rooms: List<Room>?) {
        this.roomItem = rooms
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemRoomBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(room: Room?) {
            itemBinding.tvTitleListItem.text = room?.title
            itemBinding.ivCategory.setImageResource(
                Category.getCategoryImageById(room?.categoryId)
            )
        }
    }
}