package com.alma.pokedex.presentation.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alma.pokedex.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state


    private var currentPage = 0
    private val pageSize = 20  // o el tamaño que uses

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val result = getPokemonListUseCase(limit = 20, offset = 0)
                _state.value = _state.value.copy(
                    pokemonList = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Error desconocido",
                    isLoading = false
                )
            }
        }

    }


}