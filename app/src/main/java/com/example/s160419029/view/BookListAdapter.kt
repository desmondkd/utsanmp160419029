package com.example.s160419029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s160419029.R
import com.example.s160419029.model.Book
import com.example.s160419029.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view){
            textNamePublisher.text = book.title
            textId.text = book.date

            buttonDetail.setOnClickListener {
                val action = BookListFragmentDirections.actionBookDetail(book.id.toString())
                Navigation.findNavController(it).navigate(action)
            }

            imagePublisherPhoto.loadImage(book.photoUrl, progressLoadingBookPhoto)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newStudentList: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}