package com.vum.themoviedbapptest.data.di

import com.vum.themoviedbapptest.ui.viewModels.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { TopRatedViewModel(get()) }
}