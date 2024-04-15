package com.ulan.newsapp.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ulan.newsapp.R
import com.ulan.newsapp.core.Resource
import com.ulan.newsapp.data.model.Article
import com.ulan.newsapp.databinding.FragmentHomeBinding
import com.ulan.newsapp.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { HomeAdapter(this::onItemClick) }
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onItemClick(model: Article) {
        val bundle = Bundle()
        bundle.putSerializable("model", model)
        findNavController().navigate(R.id.detailFragment, bundle)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNews.adapter = adapter
        viewModel.getNews("Bitcoin")
        viewModel.liveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    adapter.submitList(it.data?.articles)
                }
                is Resource.Error -> it.message?.let { it1 -> Log.e("home", it1) }
            }
        }
    }
}