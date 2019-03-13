package com.rdenq.bucketlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rdenq.bucketlist.R
import com.rdenq.bucketlist.databinding.FragmentBucketListBinding
import com.rdenq.bucketlist.ui.adapters.BucketListAdapter
import com.rdenq.bucketlist.ui.viewmodels.BucketListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BucketListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: BucketListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BucketListViewModel::class.java)
    }
    val adapter: BucketListAdapter by lazy { BucketListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentBucketListBinding = inflate(inflater, R.layout.fragment_bucket_list, container, false)
        with(binding) {
            bucketList.adapter = adapter
            setLifecycleOwner(this@BucketListFragment)
        }

        with(viewModel) {
            bucketCountryList.observe(this@BucketListFragment, Observer { countries ->
                binding.hasCountries = (countries != null && countries.isNotEmpty())
            })
            countryAndBucketCountryList.observe(this@BucketListFragment, Observer { result ->
                if (result != null && result.isNotEmpty())
                    adapter.submitList(result)
            })
        }

        return binding.root
    }
}