package com.diacht.ktest.juicefactory

import com.diacht.ktest.Product
import com.diacht.ktest.ProductType
import com.diacht.ktest.Storage

class JuiceStorage : Storage {
    private val products = mutableMapOf<ProductType, Int>()

    override fun addProduct(product: Product) {
        val currentCount = products.getOrDefault(product.type, 0)
        products[product.type] = currentCount + product.count
    }

    override fun checkProductCount(type: ProductType): Int {
        return products.getOrDefault(type, 0)
    }

    override fun getProduct(productType: ProductType, count: Int): Product {
        val availableCount = checkProductCount(productType)

        if (availableCount < count) {
            throw IllegalStateException(
                "Недостатньо продукту $productType. Доступно: $availableCount, потрібно: $count"
            )
        }

        products[productType] = availableCount - count
        return Product(type = productType, count = count)
    }

    override fun getLeftovers(): List<Product> {
        return products.map { (type, count) -> Product(type = type, count = count) }
    }

    override fun resetSimulation() {
        products.clear()
    }
}