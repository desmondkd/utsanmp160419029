package com.example.s160419029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s160419029.R
import com.example.s160419029.model.Publisher
import com.example.s160419029.util.loadImage
import kotlinx.android.synthetic.main.author_list_item.view.*
import kotlinx.android.synthetic.main.publisher_list_item.view.*

class PublisherListAdapter(val publisherList:ArrayList<Publisher>) : RecyclerView.Adapter<PublisherListAdapter.PublisherViewHolder>()  {
    class PublisherViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.publisher_list_item, parent, false)
        return PublisherViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publisherList[position]
        with(holder.view){
            textNamePublisher.text = publisher.name
            textId.text = publisher.id.toString()

            buttonPublisherDetail.setOnClickListener {
                val action = PublisherListFragmentDirections.actionPublisherDetail(publisher.id.toString())
                Navigation.findNavController(it).navigate(action)
            }

            imagePublisherPhoto.loadImage(publisher.photoUrl, progressLoadingPublisherPhoto)
        }
    }

    override fun getItemCount() = publisherList.size

    fun updateStudentList(newStudentList: ArrayList<Publisher>){
        publisherList.clear()
        publisherList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}