package com.example.mvvm.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.model.CardImageModel
import com.example.mvvm.ui.main.adapter.GridAdapter

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var listCard = ArrayList<CardImageModel>()
    private var adapter : GridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.fetchAllPosts()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
        val grid = view.findViewById<GridView>(R.id.gridview)
        adapter = GridAdapter(
            context = requireContext(),
            data = listCard,
            onImageClick =  { it ->
                viewModel.getSelectedItem(it)
                val fm = requireActivity().supportFragmentManager
                try {
                    fm.beginTransaction()
                        .add(R.id.container, DetailFragment.newInstance())
                        .commit()
                } catch (e: java.lang.InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        )
        grid.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listCardImage.observe(viewLifecycleOwner
        ) { ls ->
            if (!ls.isNullOrEmpty()) {
                listCard.addAll(ls)
                adapter?.notifyDataSetChanged()
            }
        }
    }

}