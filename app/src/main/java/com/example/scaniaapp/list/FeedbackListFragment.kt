package com.example.scaniaapp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scaniaapp.database.FeedbackDatabase
import com.example.scaniaapp.databinding.FragmentFeedbackListBinding
import com.example.scaniaapp.list.adapter.FeedbackAdapter
import com.example.scaniaapp.list.adapter.FeedbackViewHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FeedbackListFragment : Fragment() {
    private lateinit var feedbackViewModel: FeedbackListViewModel
    private var _binding: FragmentFeedbackListBinding? = null
    private val binding get() = _binding!!
    private var feedbackJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = FeedbackDatabase.getInstace(application).feedbackDao
        val viewModelFactory = FeedbackViewModelFactory(dataSource)
        _binding = FragmentFeedbackListBinding.inflate(inflater, container, false)

        feedbackViewModel =
            ViewModelProvider(this, viewModelFactory).get(FeedbackListViewModel::class.java)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getList()
        initSwipeToDelete()
    }

    private fun getList() {
        val adapter = FeedbackAdapter()
        binding.feedbackList.adapter = adapter
        binding.feedbackList.layoutManager = LinearLayoutManager(this.activity)

        feedbackJob?.cancel()
        feedbackJob = lifecycleScope.launch {
            feedbackViewModel.list.collectLatest { adapter.submitData(it.map { itens -> itens }) }

        }
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val  feedbackViewHolder = viewHolder as FeedbackViewHolder
                return if (feedbackViewHolder.feedback != null) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    makeMovementFlags(0, 0)
                }
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as FeedbackViewHolder).feedback?.let {
                    feedbackViewModel.startDeleteFeedback(it)
                }
            }
        }).attachToRecyclerView(binding.feedbackList)
    }
}