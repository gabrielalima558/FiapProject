package com.example.scaniaapp.vehicleinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.database.Feedback
import com.example.scaniaapp.database.FeedbackDatabase
import com.example.scaniaapp.databinding.FragmentVehicleInfoBinding

class VehicleInfoFragment : Fragment() {
    private lateinit var vehicleInfoViewModel: VehicleInfoViewModel
    private var _binding: FragmentVehicleInfoBinding? = null
    private val binding get() = _binding!!
    private var name: String = ""
    private var email: String = ""
    private var identification: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retrievePersonInfo()
        _binding = FragmentVehicleInfoBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = FeedbackDatabase.getInstace(application).feedbackDao
        val viewModelFactory = VehicleInfoViewModelFactory(dataSource)

        vehicleInfoViewModel =
            ViewModelProvider(this, viewModelFactory).get(VehicleInfoViewModel::class.java)
        val view = binding.root

        binding.nextStep.setOnClickListener {
            verifyVehicleInformations()
        }
        return view
    }

    private fun retrievePersonInfo() {
        name = arguments?.getString("name").toString()
        email = arguments?.getString("email").toString()
        identification = arguments?.getString("identification").toString()
    }

    private fun verifyVehicleInformations() {
        val type = binding.editType.text.toString()
        val year = binding.editYear.text.toString()
        val mileage = binding.editMileage.text.toString()
        val description = binding.editDescription.text.toString()
        if (type.isNullOrEmpty()) {
            Toast.makeText(context, "Modelo não está preenchido!", Toast.LENGTH_LONG).show()
        } else if (year.isNullOrEmpty()) {
            Toast.makeText(context, "Ano não está preenchido!", Toast.LENGTH_LONG).show()
        } else if (mileage.isNullOrEmpty()) {
            Toast.makeText(context, "Quilometragem não está preenchida!", Toast.LENGTH_LONG).show()
        } else if (description.isNullOrEmpty()) {
            Toast.makeText(context, "Descrição não está preenchida!", Toast.LENGTH_LONG).show()
        } else {
            insertFeedback(
                Feedback(
                    personName = name,
                    email = email,
                    personIdentification = identification,
                    vehicleModel = type,
                    vehicleFabricationYear = year,
                    vehicleMilesTravled = mileage,
                    description = description
                )
            )
        }
    }

    private fun insertFeedback(feedback: Feedback) {
        vehicleInfoViewModel.startInsertFeedback(feedback)
        findNavController().navigate(VehicleInfoFragmentDirections.actionVehicleInfoFragmentToFeedbackListFragment())
    }

}