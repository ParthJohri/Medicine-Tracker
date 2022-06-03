package com.examples.medicinetracker.affirmations.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.examples.medicinetracker.R
import com.examples.medicinetracker.databinding.AffirmationsActivityBinding
import com.examples.medicinetracker.databinding.MainFragmentBinding

class AffirmationsFragment : Fragment() {

    private val viewModel: AffirmationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)
//        val binding = AffirmationsActivityBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
//        val adapter1 = PhotoGridAdapter()
//        val adapter2 = ImagesGridAdapter()
//        val concatAdapter = ConcatAdapter(adapter1, adapter2)
//        binding.photosGrid.adapter = concatAdapter

        return binding.root
    }

}