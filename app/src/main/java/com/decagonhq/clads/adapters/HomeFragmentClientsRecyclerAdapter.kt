package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.decagonhq.clads.databinding.FragmentDashboardClientsRecyclerviewItemBinding
import com.decagonhq.clads.models.ClientsList

class HomeFragmentClientsRecyclerAdapter(private var clientList: ArrayList<ClientsList>) :
    RecyclerView.Adapter<HomeFragmentClientsRecyclerAdapter.ClientViewHolder>() {

    inner class ClientViewHolder(val binding: FragmentDashboardClientsRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clientName = binding.clientsRecyclerViewItemClientNameTextView
        var clientLocation = binding.clientsRecyclerViewItemLocationTextView
        var clientInitials = binding.homeFragmentClientInitialTextview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding = FragmentDashboardClientsRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val model = clientList[position]
        holder.itemView.apply {
            with(holder) {
                with(clientList[position]) {
                    val fullName = "$firstName $lastName"
                    clientName.text = fullName
                    clientLocation.text = location
                    val generator: ColorGenerator = ColorGenerator.MATERIAL
                    val color = generator.randomColor
                    val drawable = TextDrawable.builder().beginConfig()
                        .width(150)
                        .height(150)
                        .fontSize(55)
                        .endConfig()
                        .buildRound(
                            "${model.firstName.substring(0, 1)}${
                            model.lastName.substring(
                                0,
                                1
                            )
                            }",
                            color
                        )
                    clientInitials.setImageDrawable(drawable)
                }
            }
        }
    }

    override fun getItemCount(): Int = clientList.size
}
