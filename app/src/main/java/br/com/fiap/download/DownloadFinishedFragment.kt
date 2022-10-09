package br.com.fiap.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.download.databinding.FragmentDownloadFinishedBinding
import kotlinx.coroutines.Job

class DownloadFinishedFragment: Fragment() {

    private lateinit var binding: FragmentDownloadFinishedBinding
    private var launch: Job? = null
    //private var isDownloadPaused = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadFinishedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun setupViews() {
        binding?.buttonGoBack?.setOnClickListener { view ->
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

}