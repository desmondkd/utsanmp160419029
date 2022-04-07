package com.example.s160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s160419029.R
import com.example.s160419029.viewmodel.AuthorViewModel
import kotlinx.android.synthetic.main.fragment_author_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [AuthorListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthorListFragment : Fragment() {
    private lateinit var viewModel: AuthorViewModel
    private val authorListAdapter = AuthorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
        viewModel.refresh()

        recViewAuthor.layoutManager = LinearLayoutManager(context)
        recViewAuthor.adapter = authorListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewAuthor.visibility = View.GONE
            textError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.authorLiveData.observe(viewLifecycleOwner){
            authorListAdapter.updateStudentList(it)
        }
        viewModel.authorLoadErrorLiveData.observe(viewLifecycleOwner){
            textError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recViewAuthor.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else{
                recViewAuthor.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}