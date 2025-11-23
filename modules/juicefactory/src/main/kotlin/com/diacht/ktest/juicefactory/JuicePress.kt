package com.diacht.ktest.juicefactory

import com.diacht.ktest.Machine
import com.diacht.ktest.ProductType
import com.diacht.ktest.Receipt
import com.diacht.ktest.Storage

class JuicePress(storage: Storage) : Machine(storage) {
    private val recipes = mapOf(
        ORANGE_JUICE to OrangeJuiceReceipt,
        APPLE_JUICE to AppleJuiceReceipt,
        APPLE_CARROT_JUICE to AppleCarrotJuiceReceipt,
        TOMATO_CARROT_JUICE to TomatoCarrotJuiceReceipt,
        TOMATO_JUICE to TomatoJuiceReceipt
    )

    fun getRecipe(productType: ProductType): Receipt? {
        return recipes[productType]
    }
}