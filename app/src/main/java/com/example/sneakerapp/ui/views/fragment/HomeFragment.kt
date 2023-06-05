package com.example.sneakerapp.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakerapp.R
import com.example.sneakerapp.Utils
import com.example.sneakerapp.databinding.FragmentHomeBinding
import com.example.sneakerapp.model.Sneaker
import com.example.sneakerapp.ui.view_model.SneakerViewModel
import com.example.sneakerapp.ui.views.adapter.SneakerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sneakerAdapter: SneakerAdapter
    private val sneakerViewModel: SneakerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Getting data from JSON file
        val sneakers = fetchData()

        //Adapter Initialization
        sneakerAdapter = SneakerAdapter(object :
            SneakerAdapter.OnItemClickListener {
            override fun onClick(sneaker: Sneaker) {
                sneakerViewModel.setSelectedSneaker(sneaker)

                val secondFragment = SneakerFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, secondFragment)
                transaction.commit()
            }
        })

        sneakerAdapter.submitList(sneakers)

        binding.run {
            rvSneaker.layoutManager = GridLayoutManager(requireContext(), 2)
            rvSneaker.adapter = sneakerAdapter
        }
    }

    private fun fetchData(): List<Sneaker> {
        val jsonFileString = Utils.getJsonDataFromAsset(this.requireContext(), "shoeList.json")
        val gson = Gson()
        val sneakersList = object : TypeToken<List<Sneaker>>() {}.type
        return gson.fromJson(jsonFileString, sneakersList)
    }
}