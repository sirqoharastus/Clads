package com.decagonhq.clads.fragments.profilemanagement
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.adapters.SpecialtyListAdapter
import com.decagonhq.clads.databinding.FragmentResourceBinding
import com.decagonhq.clads.databinding.FragmentSpecialtyBinding
import com.decagonhq.clads.dialogs.DialogFragments
import com.decagonhq.clads.models.Specialty
import com.decagonhq.clads.viewmodels.EditProfileViewModel

class SpecialtyFragment : Fragment(), FragmentResultListener {

    private lateinit var fragmentTablayoutSpecialtyBinding: FragmentSpecialtyBinding
    private lateinit var adapter: SpecialtyListAdapter
    private val viewModel: EditProfileViewModel by viewModels({ requireActivity() })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentResourceBinding.inflate(inflater).root
        fragmentTablayoutSpecialtyBinding = FragmentSpecialtyBinding.inflate(inflater)
        return fragmentTablayoutSpecialtyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setUpUI()
    }

    private fun setUpUI() {
        adapter = SpecialtyListAdapter(
            requireActivity(),
            mutableListOf(
                Specialty("Yoruba attires", true),
                Specialty("Hausa attires", true),
                Specialty("Embroidery", false),
                Specialty("School uniforms", true),
                Specialty("Military and paramiltary Uniforms", false),
                Specialty("Igbo attires", false),
                Specialty("Kaftans", false),
                Specialty("Contemporary", false),
                Specialty("Western fashion", false),
                Specialty("Cops", false)

            )
        )
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        fragmentTablayoutSpecialtyBinding.fragmentSpecialtyRecyclerView.apply {
            this.layoutManager = layoutManager
            adapter = this@SpecialtyFragment.adapter
        }

        fragmentTablayoutSpecialtyBinding.fragmentSpecialtyAddNewSpecialtyButton.apply {
            setOnClickListener {
                DialogFragments.createFragment(R.layout.add_specialty_dialog_fragment_layout)
                    .show(requireActivity().supportFragmentManager, null)
            }
        }

        fragmentTablayoutSpecialtyBinding.fragmentSpeccialtyDeliveryTimeResponse.apply {
            setOnClickListener {
                DialogFragments.createFragment(R.layout.delivery_lead_time_fragment_layout)
                    .show(requireActivity().supportFragmentManager, null)
            }
        }
        fragmentTablayoutSpecialtyBinding.fragmentSpecialtyQualityAssuranceResponseTextView.apply {
            setOnClickListener {
                DialogFragments.createFragment(R.layout.obioma_trained_dialog_fragment_layout)
                    .show(requireActivity().supportFragmentManager, null)
            }
        }
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {

        adapter.updateList<Specialty>(
            Specialty(result.get("SPECIALTY") as String, false)
        )
        adapter.notifyDataSetChanged()
    }

    private fun setObservers() {
        viewModel.specialty.observe(
            viewLifecycleOwner,
            {
                adapter.updateList<Specialty>(it)
                adapter.notifyDataSetChanged()
            }
        )
        viewModel.deliveryLeadTime.observe(
            viewLifecycleOwner,
            {
                fragmentTablayoutSpecialtyBinding.fragmentSpeccialtyDeliveryTimeResponse.text = it
            }
        )

        viewModel.obiomaTrained.observe(
            requireActivity(),
            {
                fragmentTablayoutSpecialtyBinding.fragmentSpecialtyQualityAssuranceResponseTextView.text = it
            }
        )
    }
}
