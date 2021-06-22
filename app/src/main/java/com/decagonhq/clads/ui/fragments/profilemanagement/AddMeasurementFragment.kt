package com.decagonhq.clads.ui.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.decagonhq.clads.adapters.MeasurementAdapter
import com.decagonhq.clads.adapters.MeasurementAdapter.OnItemClickListener
import com.decagonhq.clads.databinding.FragmentAddMeasurementBinding
import com.decagonhq.clads.ui.dialogs.AddMeasurementDialogFragment
import com.decagonhq.clads.ui.dialogs.EditMeasurementDialogFragment
import com.decagonhq.clads.utils.MeasurementData
import com.decagonhq.clads.utils.clientsDataMeasurement
import com.decagonhq.clads.utils.temporaryMeasurement
import com.decagonhq.clads.viewmodels.MeasurementViewModel

class AddMeasurementFragment : Fragment(), OnItemClickListener {

    private var preBinding: FragmentAddMeasurementBinding? = null
    private val binding: FragmentAddMeasurementBinding get() = preBinding!!

    // initializing the recyclerview and adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MeasurementAdapter
    private val viewModel: MeasurementViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preBinding = FragmentAddMeasurementBinding.inflate(layoutInflater, container, false)
        val view = preBinding!!.root

        recyclerView = binding.addMeasurementFragmentRecyclerView
        adapter = MeasurementAdapter(clientsDataMeasurement, this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.addMeasurementFragmentAddButton.setOnClickListener {
            var addMeasurementDialogFragment = AddMeasurementDialogFragment()
            addMeasurementDialogFragment.show(
                childFragmentManager,
                "add measurement dialog fragment"
            )
        }

// observer to update the recycler view of any change in the measurement list
        viewModel.clientMeasurementLiveData.observe(
            viewLifecycleOwner,
            Observer {

                // to avoid duplication of measurements
                if (!clientsDataMeasurement.contains(it)) {
                    adapter.addNewMeasurement(it)

                    // setting the visibility of the vies to toggle depending on the condition
                    if (clientsDataMeasurement.isNotEmpty()) {
                        binding.addMeasurementFragmentRecyclerView.visibility = View.VISIBLE
                        binding.addMeasurementNoMeasurementTextView.visibility = View.GONE
                    } else {
                        binding.addMeasurementNoMeasurementTextView.visibility = View.VISIBLE
                    }

                    binding.addMeasurementFragmentRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        )

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preBinding = null
    }

    // actions to be taken when the measurement is clicked.
    override fun onMeasurementClick(position: Int, measurementList: ArrayList<MeasurementData>) {

        // measurement is stored in a temporary value
        temporaryMeasurement = clientsDataMeasurement[position]

        // measurement is captured and bundled
        val measurementToBeEdited = MeasurementData(
            measurementList[position].measurementName,
            measurementList[position].measurementValue
        )
        val bundle = bundleOf("measurementToEdit" to measurementToBeEdited, "position" to position)

        // measurement is deleted from the list and recyclerview
        measurementList.removeAt(position)
        adapter.notifyDataSetChanged()

        // open the edit text dialog
        EditMeasurementDialogFragment(bundle).show(
            childFragmentManager,
            "edit measurement dialog fragment"
        )
    }

    // removes from the list and updates the adapter ondelete click
    override fun onDeleteCLick(position: Int, measurementList: ArrayList<MeasurementData>) {
        measurementList.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}
