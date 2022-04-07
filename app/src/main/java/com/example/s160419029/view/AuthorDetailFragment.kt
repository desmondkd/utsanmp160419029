package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.s160419029.R
import com.example.s160419029.util.loadImage
import com.example.s160419029.viewmodel.AuthorDetailViewModel
import com.example.s160419029.viewmodel.AuthorViewModel
import kotlinx.android.synthetic.main.fragment_author_detail.*

class AuthorDetailFragment : Fragment() {
    private lateinit var viewModel: AuthorDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AuthorDetailViewModel::class.java)
        viewModel.fetch(AuthorDetailFragmentArgs.fromBundle(requireArguments()).authorid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.authorLiveData.observe(viewLifecycleOwner){
            val author = it

            imageAuthorDetailPhoto.loadImage(author.photoUrl, progressLoadDetailAuthor)

            textIdDetailAuthor.setText(author.id.toString())
            textNameDetailAuthor.setText(author.name)
            textDescriptionDetailAuthor.setText(author.desc)
        }
    }
}