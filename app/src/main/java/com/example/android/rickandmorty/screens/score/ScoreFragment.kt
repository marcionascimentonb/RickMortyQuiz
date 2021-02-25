package com.example.android.rickandmorty.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.rickandmorty.R
import com.example.android.rickandmorty.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_score, container, false
        )

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        binding.scoreString.text = args.scoreMsg

        return binding.root
    }

}