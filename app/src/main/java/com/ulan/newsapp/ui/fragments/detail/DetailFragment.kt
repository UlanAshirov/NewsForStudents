package com.ulan.newsapp.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ulan.newsapp.R
import com.ulan.newsapp.data.model.Article
import com.ulan.newsapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val model = arguments?.getSerializable("model") as Article
            Glide.with(requireContext()).load(model.urlToImage).centerCrop().into(binding.imgDetailImage)
            binding.tvDetailAuthor.text = model.author
            binding.tvDetailDate.text = model.publishedAt
            binding.tvDetailTitle.text = model.title
            val contentText = "${model.description}\n${model.content}"
            binding.tvDetailContent.text = contentText
        }
    }
}