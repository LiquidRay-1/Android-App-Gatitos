package com.example.gatitos.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gatitos.MainActivity
import com.example.gatitos.R
import com.example.gatitos.data.models.GatosItem
import com.example.gatitos.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var adapter: GatosAdapter
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModeFactory(requireContext())
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar2)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerview
        val llm = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)

        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.swipe.setColorSchemeColors(Color.YELLOW, Color.BLUE)
        binding.swipe.setColorSchemeResources(R.color.black, R.color.black)
        binding.swipe.setSize(SwipeRefreshLayout.LARGE)

        binding.swipe.setOnRefreshListener {
            myViewModel.getGato()
        }

        myViewModel.gatosLiveData.observe(viewLifecycleOwner) {
            binding.swipe.isRefreshing = false
            if (it != null)
                adapter.updateList(it)
        }

        recyclerView.layoutManager = llm


        adapter = GatosAdapter(object : GatosAdapter.OnItemClickListener {
            override fun onItemClick(personaje: GatosItem) {
                myViewModel.selectedGato.value = personaje
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
        recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        binding.votos.setOnClickListener {

            findNavController().navigate(R.id.action_FirstFragment_to_fragmentVotos)

        }

        myViewModel.getGato()
        myViewModel.gatosLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.updateList(it)
            }
        }

   //    requireActivity().addMenuProvider(object : MenuProvider{
   //        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
   //            TODO("Not yet implemented")
   //        }

   //        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
   //            TODO("Not yet implemented")
   //        }

   //    })
//
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}