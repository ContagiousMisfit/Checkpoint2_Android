package br.com.fiap.download

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.fiap.download.databinding.FragmentDownloadBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DownloadFragment : Fragment() {

    private lateinit var binding: FragmentDownloadBinding
    private var launch: Job? = null
    private var currentProgress = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        with(binding) {
            buttonStartProgress.setOnClickListener {
                currentProgress = 0
                startDownload()
            }
            buttonPauseProgress.setOnClickListener {
                if (buttonPauseProgress.text == "PAUSAR") {
                    buttonPauseProgress.text = "RETOMAR";
                    stopDownload()
                } else if (buttonPauseProgress.text == "RETOMAR") {
                    buttonPauseProgress.text = "PAUSAR"
                    startDownload()
                }
            }
        }
    }

    private fun startDownload() {
        var countDelay = 0L
        var countDelayTotal = 0L

        launch = lifecycleScope.launch(Dispatchers.Main) {
            while (currentProgress < 100) {
                currentProgress += CoroutineFactory.getRandomNumbers()
                countDelay = CoroutineFactory.getRandomDelays()
                Log.d("RANDOM FINISH", "RANDOM FINISH")
                countDelayTotal += countDelay
                binding.launchProgressBar.progress = currentProgress
                binding.labelPercentage.text = "$currentProgress%"
                if (currentProgress < 100) {
                    delay(countDelay)
                    Log.d("APOS DELAY", "APOS DELAY")
                }
            }
            navigateToDownloadFinished()
        }
    }

   private fun navigateToDownloadFinished() {
        findNavController().navigate(R.id.action_to_DownloadFinishedFragment)
    }

    private fun stopDownload() {
        launch?.cancel()
    }
}


