package com.decagonhq.clads.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.SpecialtyListLayoutBinding
import com.decagonhq.clads.models.Specialty

class SpecialtyListAdapter(val context: Activity, var specialityList: MutableList<Specialty>) : RecyclerView.Adapter<SpecialtyListAdapter.SpecialtyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyListViewHolder {

        return SpecialtyListViewHolder(SpecialtyListLayoutBinding.inflate(context.layoutInflater).specialty)
    }

    override fun onBindViewHolder(holder: SpecialtyListViewHolder, position: Int) {

        holder.itemView.findViewById<CheckBox>(R.id.specialty_checkbox).apply {
            text = specialityList[holder.adapterPosition].specialty
        }
    }

    override fun getItemCount(): Int {
        return specialityList.size
    }

    inner class SpecialtyListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun <T : Specialty> updateList(specialty: Specialty) {
        specialityList.add(specialty)
    }
}
