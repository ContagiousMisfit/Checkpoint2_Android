package br.com.fiap.download

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

object CoroutineFactory {

    private val listNumbers: List<Int> = listOf(5)
    private val listDelays: List<Long> = listOf(500L)

    suspend fun getRandomNumbers(): Int {
        return withContext(Dispatchers.Default) {
            this.async {
                listNumbers[(listNumbers.indices).random()].also {
                    Log.d("RANDOM NUMBER", it.toString())
                }
            }
        }.await()
    }

    suspend fun getRandomDelays(): Long {
        return withContext(Dispatchers.Default) {
            this.async {
                listDelays[(listDelays.indices).random()].also {
                    Log.d("RANDOM delay", it.toString())
                }
            }
        }.await()
    }

}