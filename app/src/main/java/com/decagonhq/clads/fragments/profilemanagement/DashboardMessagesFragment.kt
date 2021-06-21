package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.adapters.MessagesListAdapter
import com.decagonhq.clads.databinding.FragmentDashboardMessagesBinding
import com.decagonhq.clads.models.MessagesList

class DashboardMessagesFragment : Fragment() {
    // Binding varaible
    private var _binding: FragmentDashboardMessagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var messagesList: ArrayList<MessagesList>
    private lateinit var messagesListAdapter: MessagesListAdapter
    private lateinit var messageRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua..."
        messagesList = arrayListOf(
            MessagesList("Ruth", "Unoka", clientMessage),
            MessagesList("Olufemi", "Ogundipe", clientMessage),
            MessagesList("Adebayo", "Kings", clientMessage),
            MessagesList("Abdul", "Salawu", clientMessage),
            MessagesList("Hope", "Omoruyi", clientMessage)
        )
        // Recyclerview layout manager
        messageRecyclerView = binding.fragmentDashboardMessagesRecyclerview
        messagesListAdapter = MessagesListAdapter(messagesList)
        messageRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        messageRecyclerView.adapter = messagesListAdapter
    }
}
