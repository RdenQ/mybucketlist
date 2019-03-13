package com.rdenq.bucketlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.databinding.CountryItemBinding
import com.rdenq.bucketlist.ui.CountryListFragmentDirections

class CountryListAdapter(private var items: MutableList<Country> = arrayListOf()) :
    RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = items[position]
        holder.apply {
            bind(createOnClickListener(country.name), country)
            itemView.tag = country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // applying the data binding in code (COUNTRY_item_row.xml has a <layout> and CountryItemRowBinding gets generated)
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    private fun createOnClickListener(countryName: String): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(countryName)
            it.findNavController().navigate(direction)
        }
    }

    inner class ViewHolder(private val dataBinding: CountryItemBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(listener: View.OnClickListener, item: Country) {
            dataBinding.apply {
                clickListener = listener
                country = item
                executePendingBindings()
            }
        }
    }

    fun add(list: MutableList<Country>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}