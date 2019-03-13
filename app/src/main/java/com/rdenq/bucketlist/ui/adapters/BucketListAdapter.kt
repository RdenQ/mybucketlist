package com.rdenq.bucketlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries
import com.rdenq.bucketlist.databinding.BucketCountryItemBinding
import com.rdenq.bucketlist.ui.BucketListFragmentDirections
import com.rdenq.bucketlist.ui.viewmodels.CountryAndBucketCountriesViewModel

class BucketListAdapter :
    ListAdapter<CountryAndBucketCountries, BucketListAdapter.ViewHolder>(BucketCountryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BucketCountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countries = getItem(position)
        holder.apply {
            bind(createOnClickListener(countries.country.name), countries)
            itemView.tag = countries
        }
    }

    private fun createOnClickListener(countryName: String): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                BucketListFragmentDirections.actionBucketListFragmentToCountryDetailsFragment(countryName)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: BucketCountryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, countries: CountryAndBucketCountries) {
            with(binding) {
                clickListener = listener
                viewModel = CountryAndBucketCountriesViewModel(itemView.context, countries)
                executePendingBindings()
            }
        }
    }
}

private class BucketCountryDiffCallback : DiffUtil.ItemCallback<CountryAndBucketCountries>() {

    override fun areItemsTheSame(
        oldItem: CountryAndBucketCountries,
        newItem: CountryAndBucketCountries
    ): Boolean {
        return oldItem.country.name == newItem.country.name
    }

    override fun areContentsTheSame(
        oldItem: CountryAndBucketCountries,
        newItem: CountryAndBucketCountries
    ): Boolean {
        return oldItem.country == newItem.country
    }
}
