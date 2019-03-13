package com.rdenq.bucketlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.rdenq.bucketlist.R
import com.rdenq.bucketlist.databinding.FragmentCountryDetailsBinding
import com.rdenq.bucketlist.ui.viewmodels.CountryDetailsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CountryDetailsFragment : DaggerFragment() {

    private lateinit var countryName: String
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var countryDetailsViewModel: CountryDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        arguments?.let {
            val safeArgs = CountryDetailsFragmentArgs.fromBundle(it)
            countryName = safeArgs.countryName
        }
        countryDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(CountryDetailsViewModel::class.java)
        countryDetailsViewModel.getCountryData(countryName)

        val dataBinding: FragmentCountryDetailsBinding =
            inflate(inflater, R.layout.fragment_country_details, container, false)
        with(dataBinding) {
            viewModel = countryDetailsViewModel
            setLifecycleOwner(this@CountryDetailsFragment)
            fab.setOnClickListener { view ->
                countryDetailsViewModel.addCountryToBucketList(countryName.text.toString())
                Snackbar.make(view, R.string.added_country_to_bucket_list, Snackbar.LENGTH_LONG).show()
            }
        }
        return dataBinding.root
    }
}