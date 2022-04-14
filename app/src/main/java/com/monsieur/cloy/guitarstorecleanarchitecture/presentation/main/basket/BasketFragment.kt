package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.main.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.guitarstorecleanarchitecture.databinding.FragmentBasketBinding
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.changeToolBar
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BasketFragment: Fragment() {
    var _binding: FragmentBasketBinding? = null
    val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    lateinit var recyclerAdapter: BasketItemRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter.setItems(null)
    }

    private fun initFunk() {
        initRecyclerAdapter()
        viewModel.findBasketItems()
        viewModel.basketItems.observe(viewLifecycleOwner, Observer { list ->
            if(recyclerAdapter.basketItems == null){
                recyclerAdapter.setItems(list as ArrayList<BasketItem>)
            }
            setTotalPrice()
        })
        binding.toOrderButton.setOnClickListener {
            if(recyclerAdapter.itemCount == 0){
                showToast("Козина пуста")
            }else{
                if(viewModel.currentUser.value != null){
                    viewModel.confirmOrder(binding.address.text.toString() + " ").observe(viewLifecycleOwner, Observer {
                        if(it != null){
                            if(it.isDone){
                                showToast("Заказ оформлен")
                                recyclerAdapter.setItems(ArrayList())
                                setTotalPrice()
                            }else{
                                showToast("Ошибка при оформлении заказа")
                            }
                        }
                    })


                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTotalPrice(){
        if(recyclerAdapter != null && !recyclerAdapter.basketItems.isNullOrEmpty()){
            var totalPrice = 0
            recyclerAdapter.basketItems!!.forEach { totalPrice += it.quantity * Product.calculatePrice(it.product!!.price, it.product!!.discount) }
            binding.totalPrice.text = "$totalPrice р."
        }else{
            binding.totalPrice.text = "0 р."
        }
    }

    private fun initRecyclerAdapter() {
        recyclerAdapter = BasketItemRecyclerAdapter(requireContext())
        binding.basketRecycler.adapter = recyclerAdapter
        recyclerAdapter.setOnDeleteListener {
            viewModel.deleteBasketItemById(it.id)
            setTotalPrice()
        }
        recyclerAdapter.setOnMinusOneListener {
            viewModel.changeQuantityBasketItem(it.productId, -1)
            setTotalPrice()
        }
        recyclerAdapter.setOnPlusOneListener {
            viewModel.changeQuantityBasketItem(it.productId, 1)
            setTotalPrice()
        }
    }


    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, "Корзина")
    }
}