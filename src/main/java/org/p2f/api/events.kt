package org.p2f.api

data class OrderCreatedEvent(
    val orderId: String,
    val price: Double,
    val qty: Int,
    val productId: String
)

data class ProductAddedEvent(
    val productId: String,
    val price: Double,
    val stock: Int,
    val description: String
)

data class ProductUpdatedEvent(
    val productId: String,
    val stock: Int,
    val price: Double,
    val description: String
)

data class ProductStockUpdatedEvent(
    val productId: String,
    val stock: Int
)

data class ProductPriceUpdatedEvent(
    val productId: String,
    val price: Double
)
data class ProductDeletedEvent(val productId: String)