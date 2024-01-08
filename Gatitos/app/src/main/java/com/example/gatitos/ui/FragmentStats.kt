package com.example.gatitos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gatitos.R
import com.example.gatitos.databinding.FragmentStatsBinding

class FragmentStats : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    val charModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charModel.selectedGato.observe(viewLifecycleOwner) {gatos ->
            binding.adaptability.text = gatos.adaptability.toString()
            binding.affectionLevel.text = gatos.affectionLevel.toString()
            binding.childFriendly.text = gatos.childFriendly.toString()
            binding.dogFriendly.text = gatos.dogFriendly.toString()
            binding.energyLevel.text = gatos.energyLevel.toString()
            binding.grooming.text = gatos.grooming.toString()
            binding.healthIssues.text = gatos.healthIssues.toString()
            binding.intelligence.text = gatos.intelligence.toString()
            binding.sheddingLevel.text = gatos.sheddingLevel.toString()
            binding.socialNeeds.text = gatos.socialNeeds.toString()
            binding.strangerFriendly.text = gatos.strangerFriendly.toString()
        }
    }
}