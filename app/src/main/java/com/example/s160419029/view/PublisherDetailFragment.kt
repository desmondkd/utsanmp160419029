package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.s160419029.R
import com.example.s160419029.util.loadImage
import com.example.s160419029.viewmodel.PublisherDetailViewModel
import com.example.s160419029.viewmodel.PublisherViewModel
import kotlinx.android.synthetic.main.fragment_publisher_detail.*

class PublisherDetailFragment : Fragment() {
    private lateinit var viewModel: PublisherDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publisher_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PublisherDetailViewModel::class.java)
        viewModel.fetch(PublisherDetailFragmentArgs.fromBundle(requireArguments()).publisherid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.publisherLiveData.observe(viewLifecycleOwner){
            val publisher = it

            imagePublisherDetailPhoto.loadImage(publisher.photoUrl, progressLoadDetailPublisher)

            textIdDetailPublisher.setText(publisher.id.toString())
            textNameDetailPublisher.setText(publisher.name)
            textDescriptionDetailPublisher.setText(publisher.desc)
        }
    }
}