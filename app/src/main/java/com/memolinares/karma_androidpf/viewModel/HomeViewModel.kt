package com.memolinares.karma_androidpf.viewModel

import androidx.lifecycle.ViewModel
import com.memolinares.karma_androidpf.repository.FavorRepository
import com.memolinares.karma_androidpf.repository.HomeRepository

class HomeViewModel: ViewModel()  {

    var homeRepository = HomeRepository()

    fun getNombre(email: String?) = homeRepository.getNombre(email)
}