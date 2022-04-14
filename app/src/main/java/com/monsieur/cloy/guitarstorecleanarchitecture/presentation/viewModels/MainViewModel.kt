package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.common.ConfirmOrderResult
import com.monsieur.cloy.domain.models.*
import com.monsieur.cloy.domain.usecase.*
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.filters.FiltersSettings
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.filters.Sort
import com.monsieur.cloy.guitarstorecleanarchitecture.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val addToBasketUseCase: AddToBasketUseCase,
    private val changeQuantityBasketItemUseCase: ChangeQuantityBasketItemUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val deleteBasketItemByIdUseCase: DeleteBasketItemByIdUseCase,
    private val getAllFirmsUseCase: GetAllFirmsUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllProductTypesUseCase: GetAllProductTypesUseCase,
    private val getBasketItemsByUserIdUseCase: GetBasketItemsByUserIdUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : AndroidViewModel(application) {

    val currentUser = MutableLiveData<User?>()

    val isUserRegistered = MutableLiveData<Boolean>()

    private var allProducts = ArrayList<Product>()
    val filteredProducts = MutableLiveData<List<Product>>()
    var filtersSettings: FiltersSettings =
        FiltersSettings(Sort.ByPopularity, 0, 99999999, ArrayList(), ArrayList())
        private set

    val allFirms = MutableLiveData<List<Firm>>()
    val allProductTypes = MutableLiveData<List<ProductType>>()

    val basketItems = MutableLiveData<List<BasketItem>>()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getAllProductsUseCase.execute().collect {
                allProducts = it as ArrayList
                filteredProducts.postValue(filterProducts(allProducts))
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getAllFirmsUseCase.execute().collect {
                allFirms.postValue(it)
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getAllProductTypesUseCase.execute().collect {
                allProductTypes.postValue(it)
            }
        }
    }


    fun confirmOrder(address: String): MutableLiveData<ConfirmOrderResult> {
        val liveData = MutableLiveData<ConfirmOrderResult>()
        viewModelScope.launch(Dispatchers.Default) {
            if(currentUser.value != null){
                confirmOrderUseCase.execute(currentUser.value!!.id, address).first {
                    liveData.postValue(it)
                    true
                }
            }
        }
        return liveData
    }

    fun deleteBasketItemById(basketItemId: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteBasketItemByIdUseCase.execute(basketItemId)
        }
    }

    fun findBasketItems() {
        if (currentUser.value != null) {
            viewModelScope.launch(Dispatchers.Default) {
                getBasketItemsByUserIdUseCase.execute(currentUser.value!!.id).collect {
                    if (it.isEmpty()) {
                        basketItems.postValue(ArrayList())
                    } else {
                        basketItems.postValue(it)
                    }
                }
            }
        }
    }

    fun changeQuantityBasketItem(productId: Int, quantity: Int) {
        if (currentUser.value != null) {
            viewModelScope.launch(Dispatchers.Default) {
                changeQuantityBasketItemUseCase.execute(currentUser.value!!.id, productId, quantity)
            }
        }
    }

    fun addToBasket(productId: Int): Boolean {
        if (currentUser.value == null) {
            return false
        } else {
            viewModelScope.launch(Dispatchers.Default) {
                addToBasketUseCase.execute(currentUser.value!!.id, productId)
            }
            return true
        }
    }

    private fun filterProducts(products: List<Product>): List<Product> {
        if (products.isEmpty()) {
            return ArrayList()
        } else {
            var list = products.filter { product ->
                product.price >= filtersSettings.priceFrom &&
                        product.price <= filtersSettings.priceTo &&
                        product.quantity > 0
            }
            if (filtersSettings.listTypesId.isNotEmpty()) {
                list = list.filter { product ->
                    filtersSettings.listTypesId.contains(product.typeId)
                }
            }
            if (filtersSettings.listFirmsId.isNotEmpty()) {
                list = list.filter { product ->
                    filtersSettings.listFirmsId.contains(product.firmId)
                }
            }

            list = when (filtersSettings.sortBy) {
                Sort.ByName -> list.sortedBy { product -> product.firm.firmName + product.model }
                Sort.ByPopularity -> list.sortedByDescending { product -> product.popularity }
                Sort.ByPriceAscending -> list.sortedBy { product ->
                    Product.calculatePrice(
                        product.price,
                        product.discount
                    )
                }
                Sort.ByPriceDescending -> list.sortedByDescending { product ->
                    Product.calculatePrice(
                        product.price,
                        product.discount
                    )
                }
            }
            return list
        }
    }

    fun setFilters(filtersSettings: FiltersSettings) {
        this.filtersSettings = filtersSettings
        viewModelScope.launch(Dispatchers.IO) {
            if (filteredProducts.value != null) {
                filteredProducts.postValue(filterProducts(allProducts))
            }
        }
    }

    fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            loginUseCase.execute(login, password).first {
                if (it.isDone && it.user != null) {
                    currentUser.postValue(it.user)
                } else {
                    currentUser.postValue(null)
                }
                true
            }
        }
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.Default) {
            registerUserUseCase.execute(user).first {
                if (it.isDone && it.user != null) {
                    isUserRegistered.postValue(it.isDone)
                } else {
                    isUserRegistered.postValue(it.isDone)
                }
                true
            }
        }
    }

    fun logout(){
        currentUser.postValue(null)
        basketItems.postValue(ArrayList())
    }
}