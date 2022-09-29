package com.example.scaniaapp.personalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {
    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private var name: String? = null
    private var email: String? = null
    private var identication: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextStep.setOnClickListener {
            verifyPersonInfo()
        }
        binding.showList.setOnClickListener {
            findNavController().navigate(PersonalInfoFragmentDirections.actionPersonalInfoFragmentToFeedbackListFragment())
        }
        return view
    }

    private fun verifyPersonInfo() {
        name = binding.editName.text.toString()
        email = binding.editEmail.text.toString()
        identication = binding.editIdentification.text.toString()
        if (name.isNullOrEmpty()) {
            Toast.makeText(context, "Nome não está preenchido!", Toast.LENGTH_LONG).show()
        } else if (email.isNullOrEmpty()) {
            Toast.makeText(context, "Email não está preenchido!", Toast.LENGTH_LONG).show()
        } else if (identication.isNullOrEmpty()) {
            Toast.makeText(context, "Identicação não está preenchido!", Toast.LENGTH_LONG).show()
        } else {
            callVehicleInfo(name ?: "", email ?: "", identication ?: "")
        }
    }

    private fun callVehicleInfo(name: String, email: String, identification: String) {
        findNavController().navigate(
            PersonalInfoFragmentDirections.actionPersonalInfoFragmentToVehicleInfoFragment(
                name,
                email,
                identification
            )
        )
    }

}