package com.example.gatitos.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.gatitos.MainActivity
import com.example.gatitos.databinding.FragmentSecondBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var _binding: FragmentSecondBinding
    private val myViewModel: MyViewModel by activityViewModels()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = if (position == 1) "Stats" else "Information"
        }.attach()
        myViewModel.selectedGato.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).supportActionBar?.title = it.name
            Glide.with(this).load(it.image?.url).into(binding.imageView2)

            var wikipedia = it.wikipediaUrl
            binding.fab.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(wikipedia))
                startActivity(intent)
            }
        }


    }
}
