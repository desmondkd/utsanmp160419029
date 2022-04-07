package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.s160419029.R
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonBook.setOnClickListener {
            val action = MenuFragmentDirections.actionBookList()
            Navigation.findNavController(it).navigate(action)
        }
        buttonPublisher.setOnClickListener {
            val action = MenuFragmentDirections.actionPublisherList()
            Navigation.findNavController(it).navigate(action)
        }
        buttonAuthor.setOnClickListener {
            val action = MenuFragmentDirections.actionAuthorList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}