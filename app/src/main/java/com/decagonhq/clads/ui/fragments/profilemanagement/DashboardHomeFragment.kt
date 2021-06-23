package com.decagonhq.clads.ui.fragments.profilemanagement

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.adapters.HomeFragmentClientsRecyclerAdapter
import com.decagonhq.clads.databinding.FragmentDashboardHomeBinding
import com.decagonhq.clads.models.ClientsList
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class DashboardHomeFragment : Fragment() {

    // Declare binding variable
    private var _binding: FragmentDashboardHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var clientList: ArrayList<ClientsList>
    private lateinit var clientsAdapter: HomeFragmentClientsRecyclerAdapter
    private lateinit var clientsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clientsRecyclerView = binding.homeFragmentClientListRecyclerView
        populateClient()
        chartData()
        clientsAdapter = HomeFragmentClientsRecyclerAdapter(clientList)
        clientsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        clientsRecyclerView.adapter = clientsAdapter
    }

    private fun populateClient() {
        clientList = arrayListOf(
            ClientsList("Lord", "Pacific", "Lagos"),
            ClientsList("Lion", "King", "Benin"),
            ClientsList("Netflix", "Fastlane", "Abeokuta"),
            ClientsList("Adebayo", "Kings", "Lagos"),
            ClientsList("Abdul", "Salawu", "Benin"),
            ClientsList("Emeka", "Paul", "Abeokuta"),
            ClientsList("Ruth", "Ife", "Abeokuta"),
            ClientsList("Jesse", "Lord", "Abeokuta"),
            ClientsList("Runo", "Baby", "Abeokuta"),
            ClientsList("Toju", "Abolade", "Abeokuta"),
            ClientsList("Adebayo", "Prof", "Abeokuta")
        )
    }

    private fun chartData() {
        val chart = binding.lineChart
        val entries = ArrayList<String>()
        entries.add("Jan")
        entries.add("Feb")
        entries.add("Mar")
        entries.add("Apr")
        entries.add("May")
        entries.add("Jun")
        entries.add("Jul")
        entries.add("Aug")
        entries.add("Sept")
        entries.add("Oct")
        entries.add("Nov")
        entries.add("Dec")
        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(30f, 0))
        lineEntry.add(Entry(50f, 1))
        lineEntry.add(Entry(40f, 2))
        lineEntry.add(Entry(35f, 3))
        lineEntry.add(Entry(40f, 4))
        lineEntry.add(Entry(35f, 5))
        lineEntry.add(Entry(49f, 6))
        lineEntry.add(Entry(45f, 7))
        lineEntry.add(Entry(49f, 8))
        lineEntry.add(Entry(40f, 9))
        lineEntry.add(Entry(55f, 10))
        lineEntry.add(Entry(65f, 11))
        val mFillColor = Color.argb(100, 0, 102, 245)
        val lineDataSet = LineDataSet(lineEntry, "Clients")
        lineDataSet.color = resources.getColor(R.color.teal_200)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.lineWidth = 2f
        lineDataSet.fillColor = mFillColor
        lineDataSet.setCircleColor(Color.BLUE)
        lineDataSet.color = R.color.red
        lineDataSet.setDrawFilled(true)
        lineDataSet.setCircleColorHole(R.color.teal_200)
        lineDataSet.fillAlpha = 58
        val data = LineData(entries, lineDataSet)
        chart.data = data
        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateXY(3000, 3000)
        chart.setDescription(null)
        chart.setGridBackgroundColor(R.color.deep_sky_blue)
        chart.axisRight.isEnabled = false
        chart.setTouchEnabled(false)
        chart.setDrawGridBackground(false)
        chart.setNoDataText("No forex yet!")
        val xAxis = chart.xAxis
        val yAxis = chart.axisLeft
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 6F
        xAxis.labelRotationAngle = -90f
        xAxis.axisLineColor = R.color.deep_sky_blue
        yAxis.setDrawGridLines(false)
        yAxis.axisLineColor = R.color.deep_sky_blue
        yAxis.textSize = 6f
        yAxis.mAxisMaximum = 100F
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
