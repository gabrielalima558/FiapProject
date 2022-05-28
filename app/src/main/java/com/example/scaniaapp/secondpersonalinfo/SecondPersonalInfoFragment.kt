package com.example.scaniaapp.secondpersonalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.R
import com.example.scaniaapp.databinding.FragmentSecondPersonlInfoBinding

class SecondPersonalInfoFragment : Fragment() {
    private var _binding: FragmentSecondPersonlInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondPersonlInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextStep.setOnClickListener {
            findNavController().navigate(SecondPersonalInfoFragmentDirections.actionSecondPersonalInfoFragmentToVehicleInfoFragment())
        }
        return view
    }

}