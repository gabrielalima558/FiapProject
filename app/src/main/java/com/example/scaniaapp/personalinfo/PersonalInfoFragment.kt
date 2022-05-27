package com.example.scaniaapp.personalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.R
import com.example.scaniaapp.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {
    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextStep.setOnClickListener {
            findNavController().navigate(R.id.action_personalInfoFragment_to_secondPersonalInfoFragment)
        }
        return view
    }

}