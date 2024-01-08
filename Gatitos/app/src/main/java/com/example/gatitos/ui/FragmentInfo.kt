package com.example.gatitos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gatitos.databinding.FragmentInfoBinding

class FragmentInfo : Fragment() {

    private lateinit var _binding: FragmentInfoBinding
    private val myViewModel: MyViewModel by activityViewModels()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel.selectedGato.observe(viewLifecycleOwner) {
            binding.weightinfo.text = it.weight?.metric + "kg."
            binding.temperamentinfo.text = it.temperament
            binding.origininfo.text = it.origin
            binding.lifeinfo.text = it.lifeSpan
            binding.descriptioninfo.text = it.description
        }
    }

}