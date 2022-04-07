package com.example.s160419029.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s160419029.R
import com.example.s160419029.model.Author
import com.example.s160419029.util.loadImage
import kotlinx.android.synthetic.main.author_list_item.view.*
import kotlinx.android.synthetic.main.book_list_item.view.*


class AuthorListAdapter(val authorList:ArrayList<Author>):RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {
    class AuthorViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.author_list_item, parent, false)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authorList[position]
        with(holder.view){
            textID.text = author.id.toString()
            Log.d("error1",author.id.toString())
            textNameAuthor.text = author.name

            buttonAuthorDetail.setOnClickListener {
                val action = AuthorListFragmentDirections.actionAuthorDetail(author.id.toString())
                Navigation.findNavController(it).navigate(action)
            }

            imageAuthorPhoto.loadImage(author.photoUrl, progressLoadingAuthorPhoto)
        }
    }

    override fun getItemCount() = authorList.size

    fun updateStudentList(newAuthorList: ArrayList<Author>){
        authorList.clear()
        authorList.addAll(newAuthorList)
        notifyDataSetChanged()
    }
}