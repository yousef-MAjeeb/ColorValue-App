package com.example.colorvalue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.colorvalue.databinding.FragmentColorDetailBinding

class ColorDetailFragment : Fragment() {

    private var _binding: FragmentColorDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ColorVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorId = arguments?.getInt("colorId") ?: return

        viewModel.allColors.observe(viewLifecycleOwner) { colors ->
            val color = colors.find { it.id == colorId }
            color?.let {
                binding.colorView.setBackgroundColor(android.graphics.Color.rgb(it.red, it.green, it.blue))
                binding.tvName.text = it.name
                binding.tvHex.text = it.hex
            }
        }

        // add delete-icon
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_delete -> {
                        val colorId = arguments?.getInt("colorId") ?: return false
                        viewModel.allColors.value?.find { it.id == colorId }?.let {
                            viewModel.deleteColor(it)
                            findNavController().popBackStack()
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}