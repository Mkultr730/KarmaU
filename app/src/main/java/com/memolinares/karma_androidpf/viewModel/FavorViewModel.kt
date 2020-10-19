package com.memolinares.karma_androidpf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.model.User
import com.memolinares.karma_androidpf.repository.FavorRepository

class FavorViewModel: ViewModel() {
    var userLiveData = MutableLiveData<User>()

    var favorRepository = FavorRepository()

    fun askFavor(favor: Favor) = favorRepository.askFavor(favor)
}