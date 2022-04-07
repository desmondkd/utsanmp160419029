package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.s160419029.R
import com.example.s160419029.util.loadImage
import com.example.s160419029.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).bookid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            val book = it

            imageDetailPhoto.loadImage(book.photoUrl, progressLoadDetail)

            textTitleDetail.setText(book.title)
            textDateDetail.setText(book.date)
            textAuthorDetail.setText(book.author)
            textPubliherDetail.setText(book.publisher)
        }
    }

}