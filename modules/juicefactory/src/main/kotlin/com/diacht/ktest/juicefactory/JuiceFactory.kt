package com.diacht.ktest.juicefactory

import com.diacht.ktest.FactoryItf
import com.diacht.ktest.NONE
import com.diacht.ktest.Product
import com.diacht.ktest.ProductType

class JuiceFactory : FactoryItf() {
    private val storage = JuiceStorage()
    private val machine = JuicePress(storage)
    private var totalEarnings = 0

    override fun resetSimulation() {
        storage.resetSimulation()
        totalEarnings = 0
    }

    override fun loadProducts(productsFromSupplier: List<Product>) {
        productsFromSupplier.forEach { product ->
            storage.addProduct(product)
        }
    }

    override fun order(order: List<Pair<ProductType, Int>>): List<Product> {
        val result = mutableListOf<Product>()

        order.forEach { (drinkType, quantity) ->
            repeat(quantity) {
                val recipe = machine.getRecipe(drinkType)

                if (recipe == null) {
                    result.add(Product(NONE, 0))
                    return@repeat
                }

                try {
                    // Перевіряємо, чи є всі необхідні інгредієнти
                    val hasAllIngredients = recipe.products.all { ingredient ->
                        storage.checkProductCount(ingredient.type) >= ingredient.count
                    }

                    if (!hasAllIngredients) {
                        result.add(Product(NONE, 0))
                        return@repeat
                    }

                    machine.setReceipt(recipe)


                    val drink = machine.executeProcess()

                    result.add(drink)

                    totalEarnings += recipe.price

                } catch (e: IllegalStateException) {
                    result.add(Product(NONE, 0))
                }
            }
        }

        return result
    }

    override fun getLeftovers(): List<Product> {
        return machine.getLeftovers()
    }

    override fun getEarnings(): Int {
        return totalEarnings
    }
}