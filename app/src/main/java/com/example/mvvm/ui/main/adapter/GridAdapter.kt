package com.example.mvvm.ui.main.adapter



import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.model.CardImageModel


class GridAdapter(
    private val context : Context,
    private val data : ArrayList<CardImageModel>,
    private val onImageClick : (CardImageModel) -> Unit
    ) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position : Int, view: View?, viewGroup: ViewGroup?): View? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.grid_layout, null)
        val imageView = view!!.findViewById<ImageView>(R.id.imageView)
        if(!data[position].url.isNullOrBlank()) {
            Glide.with(context)
                .load(data[position].url)
                .into(imageView)
        }

        imageView.setOnClickListener { onImageClick(data[position]) }

        return view
    }
}