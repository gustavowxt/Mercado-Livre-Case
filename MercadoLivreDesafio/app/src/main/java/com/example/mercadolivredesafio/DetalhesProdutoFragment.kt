package com.example.mercadolivredesafio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.utils.ViewState
import androidx.fragment.app.viewModels

class DetalhesProdutoFragment : Fragment() {
    private val viewModel: DetalhesProdutoViewModel by viewModels()
    private lateinit var binding: FragmentDetalhesProdutoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalhesProdutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("produtoId")?.let { produtoId ->
            viewModel.obterDetalhesProduto(produtoId)
        }

        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.estadoProduto.observe(viewLifecycleOwner) { estado ->
            when (estado) {
                is ViewState.Loading -> mostrarCarregando()
                is ViewState.Success -> mostrarDetalhesProduto(estado.data)
                is ViewState.Error -> mostrarErro(estado.message)
            }
        }
    }

    private fun mostrarCarregando() {
        binding.progressBar.visible()
        binding.conteudoLayout.gone()
        binding.textoErro.gone()
    }

    private fun mostrarDetalhesProduto(produto: Produto) {
        binding.progressBar.gone()
        binding.conteudoLayout.visible()
        binding.textoErro.gone()

        with(binding) {
            Glide.with(requireContext())
                .load(produto.thumbnail)
                .into(imagemProduto)

            textoTitulo.text = produto.titulo
            textoPreco.text = getString(R.string.formato_preco, produto.preco)
            textoCondicao.text = produto.condicao
            textoQuantidadeDisponivel.text = getString(
                R.string.quantidade_disponivel,
                produto.quantidadeDisponivel
            )

            if (produto.envio.freteGratis) {
                textoEnvio.text = getString(R.string.frete_gratis)
                textoEnvio.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.verde)
                )
            } else {
                textoEnvio.text = getString(R.string.frete_pago)
            }

            // Configurar RecyclerView de atributos
            val adapterAtributos = AtributosProdutoAdapter()
            recyclerAtributos.adapter = adapterAtributos
            recyclerAtributos.layoutManager = LinearLayoutManager(requireContext())
            adapterAtributos.submitList(produto.atributos)
        }
    }

    private fun mostrarErro(mensagem: String) {
        binding.progressBar.gone()
        binding.conteudoLayout.gone()
        binding.textoErro.visible()
        binding.textoErro.text = mensagem
    }
}