package com.example.colorvalue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.colorvalue.databinding.FragmentListBinding

class ColorListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ColorVM by activityViewModels()
    private lateinit var adapter: ColorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ColorAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.allColors.observe(viewLifecycleOwner) { colors ->
            adapter.submitList(colors)
        }

        binding.fab.setOnClickListener {
            // TODO: navigate to AddColorFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}