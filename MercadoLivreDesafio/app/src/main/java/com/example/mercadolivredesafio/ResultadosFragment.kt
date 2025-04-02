package com.example.mercadolivredesafio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.utils.ViewState
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class ResultadosFragment : Fragment() {
    private val viewModel: ResultadosViewModel by viewModels()
    private lateinit var binding: FragmentResultadosBinding
    private lateinit var adapter: ProdutosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultadosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
        observarViewModel()

        arguments?.getString("query")?.let { query ->
            viewModel.pesquisarProdutos(query)
        }
    }

    private fun configurarRecyclerView() {
        adapter = ProdutosAdapter { produto ->
            findNavController().navigate(
                ResultadosFragmentDirections.actionResultadosFragmentToDetalhesProdutoFragment(produto.id)
            )
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ResultadosFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun observarViewModel() {
        viewModel.estadoProdutos.observe(viewLifecycleOwner) { estado ->
            when (estado) {
                is ViewState.Loading -> mostrarCarregando()
                is ViewState.Success -> mostrarProdutos(estado.data.resultados)
                is ViewState.Error -> mostrarErro(estado.message)
            }
        }
    }

    private fun mostrarCarregando() {
        binding.progressBar.visible()
        binding.recyclerView.gone()
        binding.textoErro.gone()
    }

    private fun mostrarProdutos(produtos: List<Produto>) {
        binding.progressBar.gone()
        binding.recyclerView.visible()
        binding.textoErro.gone()
        adapter.submitList(produtos)
    }

    private fun mostrarErro(mensagem: String) {
        binding.progressBar.gone()
        binding.recyclerView.gone()
        binding.textoErro.visible()
        binding.textoErro.text = mensagem
    }
}