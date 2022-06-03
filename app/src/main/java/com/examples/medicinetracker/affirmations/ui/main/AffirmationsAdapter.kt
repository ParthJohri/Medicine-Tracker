package com.examples.medicinetracker.affirmations.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examples.medicinetracker.affirmations.network.Affirmation
import com.examples.medicinetracker.affirmations.network.AffirmationImage
import com.examples.medicinetracker.affirmations.ui.main.AffirmationsAdapter
import com.examples.medicinetracker.databinding.GridViewItemBinding

class AffirmationsAdapter(
    val affirmations: List<Affirmation>,
    private val affirmationImages: List<AffirmationImage>
) : RecyclerView.Adapter<AffirmationsAdapter.AffirmationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AffirmationsAdapter.AffirmationViewHolder {
        return AffirmationViewHolder(
//            LayoutInflater.from(parent.context).inflate(GridViewItemBinding, parent, false)
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: AffirmationsAdapter.AffirmationViewHolder,
        position: Int
    ) {
        val affirmation = affirmations[position]
        val affirmationImage = affirmationImages[position]
        holder.bind(affirmation, affirmationImage)
    }

    override fun getItemCount(): Int {
        return affirmationImages.size
    }

    class AffirmationViewHolder(private val binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(affirmation: Affirmation, affirmationImage: AffirmationImage) {
            binding.affirmation = affirmation
            binding.affirmationImage = affirmationImage

            binding.executePendingBindings()
        }
    }
}