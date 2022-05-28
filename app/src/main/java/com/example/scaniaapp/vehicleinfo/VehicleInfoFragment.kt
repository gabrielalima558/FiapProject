package com.example.scaniaapp.vehicleinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.R
import com.example.scaniaapp.databinding.FragmentSecondPersonlInfoBinding
import com.example.scaniaapp.databinding.FragmentVehicleInfoBinding

class VehicleInfoFragment : Fragment() {
    private var _binding: FragmentVehicleInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehicleInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextStep.setOnClickListener {
            findNavController().navigate(VehicleInfoFragmentDirections.actionVehicleInfoFragmentToAdditionalInformationFragment())
        }
        return view
    }

}