package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s160419029.R
import com.example.s160419029.viewmodel.PublisherViewModel
import kotlinx.android.synthetic.main.fragment_publisher_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [PublisherListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublisherListFragment : Fragment() {
    private lateinit var viewModel: PublisherViewModel
    private val publisherListAdapter = PublisherListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publisher_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PublisherViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = publisherListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.publisherLiveData.observe(viewLifecycleOwner){
            publisherListAdapter.updateStudentList(it)
        }
        viewModel.publisherLoadErrorLiveData.observe(viewLifecycleOwner){
            textError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else{
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}