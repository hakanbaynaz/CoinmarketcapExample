package com.example.coinmarketcapexample.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinmarketcapexample.viewmodel.CoinDetailViewModel
import com.example.coinmarketcapexample.viewmodel.CoinListViewModel
import com.example.coinmarketcapexample.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    abstract fun profileViewModel(viewModel: CoinListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    abstract fun coinDetailViewModel(viewModel: CoinDetailViewModel): ViewModel
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
