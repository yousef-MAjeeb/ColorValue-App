package com.example.colorvalue

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.colorvalue.databinding.FragmentAddColorBinding

class AddColorFragment : Fragment() {

    private var _binding: FragmentAddColorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ColorVM by activityViewModels()

    private var red = 0
    private var green = 0
    private var blue = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddColorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar?.id) {
                    R.id.seekBarR -> red = progress
                    R.id.seekBarG -> green = progress
                    R.id.seekBarB -> blue = progress
                }
                updatePreview()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        binding.seekBarR.setOnSeekBarChangeListener(seekBarListener)
        binding.seekBarG.setOnSeekBarChangeListener(seekBarListener)
        binding.seekBarB.setOnSeekBarChangeListener(seekBarListener)

        binding.btnAddColor.setOnClickListener {
            val name = binding.etColorName.text.toString()
            viewModel.addColor(name, red, green, blue)
            findNavController().popBackStack()
        }
    }

    private fun updatePreview() {
        val hex = String.format("#%02X%02X%02X", red, green, blue)
        binding.colorPreview.setBackgroundColor(Color.rgb(red, green, blue))
        binding.tvHexPreview.text = hex
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}