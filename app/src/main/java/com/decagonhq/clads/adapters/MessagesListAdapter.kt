package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.FragmentDashboardMessagesRecyclerviewItemBinding
import com.decagonhq.clads.models.MessagesList

class MessagesListAdapter(private var messagesList: ArrayList<MessagesList>) : RecyclerView.Adapter<MessagesListAdapter.MessagesViewHolder>() {

    inner class MessagesViewHolder(val binding: FragmentDashboardMessagesRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clientName = binding.fragmentDashboardMessagesRecyclerViewItemName
        var clientMessages = binding.fragmentDashboardMessagesRecyclerViewItemBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val binding = FragmentDashboardMessagesRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.itemView.apply {
            with(holder) {
                with(messagesList[position]) {
                    val fullName = "$firstName $lastName"
                    clientName.text = fullName
                    clientMessages.text = messages
                }
            }
        }
    }

    override fun getItemCount(): Int = messagesList.size
}
