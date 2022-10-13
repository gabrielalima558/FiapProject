package com.example.scaniaapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.scaniaapp.database.Feedback
import com.example.scaniaapp.database.FeedbackDatabase
import com.example.scaniaapp.databinding.FragmentFeedbackDetailBinding

class FeedbackDetailFragment : Fragment() {
    private var _binding: FragmentFeedbackDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var feedbackDetailViewModel: FeedbackDetailViewModel
    private var personName: String? = null
    private var feedback: Feedback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        personName = arguments?.getString("person_name").toString()
        _binding = FragmentFeedbackDetailBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = FeedbackDatabase.getInstace(application).feedbackDao
        val viewModelFactory = FeedbackDetailViewModelFactory(dataSource)
        feedbackDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(FeedbackDetailViewModel::class.java)
        populateFeedback()
        startFinishChanges()

        return binding.root
    }

    private fun populateFeedback() {
        feedbackDetailViewModel.startGetFeedback(personName!!)
        feedbackDetailViewModel.feedback.observe(viewLifecycleOwner) {
            feedback = it
            populateDetailInfo()
        }
    }

    private fun populateDetailInfo() {
        binding.editName.setText(feedback?.personName ?: "")
        binding.editEmail.setText(feedback?.email ?: "")
        binding.editIdentification.setText(feedback?.personIdentification ?: "")
        binding.editType.setText(feedback?.vehicleModel ?: "")
        binding.editYear.setText(feedback?.vehicleFabricationYear ?: "")
        binding.editMileage.setText(feedback?.vehicleMilesTravled ?: "")
        binding.editDescription.setText(feedback?.description ?: "")
    }

    private fun startFinishChanges() {
        binding.saveBtn.setOnClickListener {
            retrieveNewValues()
            feedbackDetailViewModel.startUpdateFeedback(feedback!!)
            findNavController().navigate(FeedbackDetailFragmentDirections.actionFeedbackDetailFragmentToFeedbackListFragment())
        }
    }

    private fun retrieveNewValues() {
        feedback?.personName = binding.editName.text.toString()
        feedback?.email = binding.editEmail.text.toString()
        feedback?.personIdentification = binding.editIdentification.text.toString()
        feedback?.vehicleModel = binding.editType.text.toString()
        feedback?.vehicleFabricationYear = binding.editYear.text.toString()
        feedback?.vehicleMilesTravled = binding.editMileage.text.toString()
        feedback?.description = binding.editDescription.text.toString()

    }
}