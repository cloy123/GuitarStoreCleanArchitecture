package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentProductBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.LoginFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.arrayImages
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.replaceFragment
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductFragment(val product: Product): Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, product.firm.firmName + " " + product.model)
    }

    @SuppressLint("SetTextI18n")
    private fun initFunc() {
        initSliderAdapter()
        binding.productFirm.text = product.firm.firmName
        binding.productModel.text = product.model
        binding.productType.text = product.type.typeName
        binding.productName.text = product.firm.firmName + " " + product.model
        binding.productPrice.text = Product.calculatePrice(product.price, product.discount).toString() + " р."
        binding.productSpecification.text = product.specification
        if(product.quantity > 0){
            binding.productQuantity.text = product.quantity.toString()
        }else{
            binding.addToBasket.text = getString(R.string.not_available)
            binding.addToBasket.isEnabled = false
            binding.productQuantity.text = "0"
        }
        binding.addToBasket.setOnClickListener {
            if(viewModel.addToBasket(product.id)){

                showToast("Добавлено в корзину")
            }else{
                replaceFragment(LoginFragment())
            }
        }
    }

    private fun initSliderAdapter(){
        sliderAdapter = SliderAdapter(requireContext())
        binding.imageSlider.setSliderAdapter(sliderAdapter)
        sliderAdapter.setItems(arrayImages[product.imagesId] as MutableList<Int>)
    }
}