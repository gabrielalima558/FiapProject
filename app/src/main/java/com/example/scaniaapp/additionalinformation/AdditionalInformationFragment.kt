package com.example.scaniaapp.additionalinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.R
import com.example.scaniaapp.databinding.FragmentAdditionalInformationBinding
import com.example.scaniaapp.databinding.FragmentVehicleInfoBinding

class AdditionalInformationFragment : Fragment() {
    private var _binding: FragmentAdditionalInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdditionalInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.finishFeedback.setOnClickListener {
            requireActivity().finish()
        }
        return view
    }

}