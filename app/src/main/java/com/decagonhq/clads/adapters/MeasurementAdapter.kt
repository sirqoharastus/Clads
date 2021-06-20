package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.utils.MeasurementData

class MeasurementAdapter(
    var measurementList: ArrayList<MeasurementData>,
    val listener: OnItemClickListener,
    val listener2: OnItemClickListener

) : RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>() {

    var list = arrayListOf<MeasurementData>()

    inner class MeasurementViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val measurementNameTextView: TextView =
            itemView.findViewById(R.id.measurement_item_row_layout_measurement_name_text_view)
        val measurementValueTextView: TextView =
            itemView.findViewById(R.id.measurement_item_row_layout_measurement_value_text_view)

        val delete: ImageView =
            itemView.findViewById(R.id.measurement_item_row_layout_delete_measurement_image_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onMeasurementClick(position, measurementList)
                notifyDataSetChanged()
            }
        }

        fun bind(measurement: MeasurementModel) {

            measurementValueTextView.text = measurement[adapterPosition].measurementName
            measurementNameTextView.text = measurement[adapterPosition].measurementValue
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.measurement_item_row_layout, parent, false)
        return MeasurementViewHolder(view)
    }

    // function to add new measurement to the list and update the adapter
    fun addNewMeasurement(measurement: MeasurementData) {
        measurementList.add(0, measurement)
        notifyDataSetChanged()
    }

    fun removeMeasurement(measurement: MeasurementData) {
        val index = measurementList.indexOf(measurement)
        measurementList.removeAt(index)
        notifyDataSetChanged()
    }

    fun editMeasurement(measurement: MeasurementData) {
        val index = measurementList.indexOf(measurement)
        measurementList.replaceAll { measurement }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val currentItem = measurementList[position]

        holder.measurementNameTextView.text = currentItem.measurementName
        holder.measurementValueTextView.text = currentItem.measurementValue

        holder.delete.setOnClickListener {
            listener2.onDeleteCLick(position, measurementList)
        }

        holder.measurementNameTextView.setOnClickListener {
            listener.onMeasurementClick(position, measurementList)
        }
    }

    override fun getItemCount() = measurementList.size

    // interface for the click to edit(measurement click) and the delete
    interface OnItemClickListener {
        fun onMeasurementClick(position: Int, measurementList: ArrayList<MeasurementData>)
        fun onDeleteCLick(position: Int, measurementList: ArrayList<MeasurementData>)
    }

    fun updateList(measurement: ArrayList<MeasurementData>) {
        list.addAll(measurement)
        notifyDataSetChanged()
    }
}
