package com.memolinares.karma_androidpf.viewModel

import androidx.lifecycle.ViewModel
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.repository.FavorRepository

class FavorViewModel: ViewModel() {
    var favorRepository = FavorRepository()

    fun askFavor(favor: Favor) = favorRepository.askFavor(favor)
}