package com.examples.medicinetracker.affirmations.ui.main.facts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.examples.medicinetracker.affirmations.network.Affirmation
import com.examples.medicinetracker.affirmations.network.AffirmationImage
import com.examples.medicinetracker.affirmations.network.Fact
import com.examples.medicinetracker.databinding.FactsViewItemBinding

class FactsAdapter(
    private val facts: List<Fact>
) : RecyclerView.Adapter<FactsAdapter.FactViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FactsAdapter.FactViewHolder {
        return FactViewHolder(
            FactsViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: FactsAdapter.FactViewHolder,
        position: Int
    ) {
        val fact = facts[position].fact
        holder.bind(fact)
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    class FactViewHolder(private val binding: FactsViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: String) {
            binding.fact.text = fact
            binding.executePendingBindings()
        }
    }
}