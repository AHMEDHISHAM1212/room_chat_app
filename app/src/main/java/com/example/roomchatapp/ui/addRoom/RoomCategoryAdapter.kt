package com.example.roomchatapp.ui.addRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.roomchatapp.databinding.ItemSpListBinding
import com.example.roomchatapp.ui.model.Category


class RoomCategoryAdapter(val item: List<Category>) : BaseAdapter() {

    override fun getItemId(position: Int): Long {
        return item[position].id.toLong()
    }

    override fun getItem(postition: Int): Any {
        return item[postition]
    }

    override fun getCount(): Int {
        return item.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder

        if (view == null) {
            //create view holder
            val itemBinding = ItemSpListBinding
                .inflate(
                    LayoutInflater.from(parent?.context),
                    parent, false
                )
            viewHolder = ViewHolder(itemBinding)
            // to attach the view holder with the view by the tag of the rootView
            itemBinding.root.tag = viewHolder


        } else {
            // return view
            viewHolder = view.tag as ViewHolder
        }

        // bind
        viewHolder.bind(item[position])

        return viewHolder.itemBinding.root


    }

    class ViewHolder(val itemBinding: ItemSpListBinding) {
        fun bind(item: Category) {
            itemBinding.tvTitleListItem.text = item.title
            itemBinding.ivCategory.setImageResource(item.imageResID)
        }
    }

}