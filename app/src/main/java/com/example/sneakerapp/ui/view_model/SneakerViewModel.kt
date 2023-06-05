package com.example.sneakerapp.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sneakerapp.Utils
import com.example.sneakerapp.model.Sneaker

class SneakerViewModel : ViewModel() {
    val selectedSneaker = MutableLiveData<Sneaker>()
    val sneakersAddedInCart = MutableLiveData<List<Sneaker>>()
    private lateinit var sneakerList: MutableList<Sneaker>
    private var sneakerSubTotal = 0

    fun addSneakerToCart(sneaker: Sneaker) {
        if (this::sneakerList.isInitialized) sneakerList.add(sneaker)
        else sneakerList = mutableListOf(sneaker)
        sneakersAddedInCart.value = sneakerList
    }

    fun setSelectedSneaker(sneaker: Sneaker) {
        selectedSneaker.value = sneaker
    }

    fun clearAllData() {
        if (this::sneakerList.isInitialized) {
            sneakerSubTotal = 0
            sneakerList.clear()
        }
    }

    fun getSubtotal(): String {
        return if (this::sneakerList.isInitialized) {
            val priceList = sneakerList.map { it.retailPrice }
            sneakerSubTotal = Utils.getTotal(priceList)
            "Subtotal : $$sneakerSubTotal"
        } else {
            ""
        }
    }

    fun getTotal(): String {
        return if (this::sneakerList.isInitialized) {
            "$" + (sneakerSubTotal + 40)
        } else {
            ""
        }
    }
}