package de.rezkalla.todocleararchapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import dagger.Lazy

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T : ViewModel> @Inject constructor(private val viewModel: Lazy<T>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel.get() as T
}