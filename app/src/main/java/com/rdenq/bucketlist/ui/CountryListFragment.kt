package com.rdenq.bucketlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rdenq.bucketlist.R
import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.databinding.FragmentCountryListBinding
import com.rdenq.bucketlist.ui.adapters.CountryListAdapter
import com.rdenq.bucketlist.ui.viewmodels.CountryListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country_list.*
import javax.inject.Inject

class CountryListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CountryListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CountryListViewModel::class.java)
    }
    val adapter: CountryListAdapter by lazy {
        CountryListAdapter(
            arrayListOf()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCountryListBinding = inflate(inflater, R.layout.fragment_country_list, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            countryList.observe(this@CountryListFragment, Observer {
                initView(it)
            })
            error.observe(this@CountryListFragment, Observer {
                progressBar_home.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView(it: MutableList<Country>?) {
        rv_main_home.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        rv_main_home.adapter = adapter
        progressBar_home.visibility = View.GONE
        if (it!!.isNotEmpty()) {
            adapter.clear()
            adapter.add(it)
        } else {
            Toast.makeText(context, context?.getString(R.string.empty_list), android.widget.Toast.LENGTH_LONG).show()
        }
    }
}