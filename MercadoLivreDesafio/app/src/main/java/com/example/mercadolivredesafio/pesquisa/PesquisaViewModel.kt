package com.example.mercadolivredesafio.pesquisa

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.mercadolivredesafio.data.repository.ProdutoRepository
import kotlinx.coroutines.launch

class PesquisaViewModel @ViewModelInject constructor(
    private val repository: ProdutoRepository
) : ViewModel() {
    private val _estadoPesquisa = MutableLiveData<ViewState<RespostaPesquisa>>()
    val estadoPesquisa: LiveData<ViewState<RespostaPesquisa>> = _estadoPesquisa

    fun pesquisarProdutos(query: String) {
        _estadoPesquisa.value = ViewState.Loading
        viewModelScope.launch {
            when (val resultado = repository.pesquisarProdutos(query)) {
                is Resource.Success -> {
                    _estadoPesquisa.value = ViewState.Success(resultado.data)
                }
                is Resource.Error -> {
                    _estadoPesquisa.value = ViewState.Error(resultado.message)
                }
            }
        }
    }
}