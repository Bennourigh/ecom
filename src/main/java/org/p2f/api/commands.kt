package org.p2f.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
data class CreateOrderCommand(
    @TargetAggregateIdentifier
    val orderId: String,
    val price: Double,
    val qty: Int,
    val productId: String
)

data class AddProductCommand(
    @TargetAggregateIdentifier
    val productId: String,
    val price: Double,
    val stock: Int,
    val description: String
)
data class UpdateProductCommand(
    @TargetAggregateIdentifier
    val productId: String,
    val price: Double,
    val stock: Int,
    val description: String
)

data class UpdateProductStockCommand(
    @TargetAggregateIdentifier
    val productId: String,
    val stock: Int,
)

data class UpdateProductPriceCommand(
    @TargetAggregateIdentifier
    val productId: String,
    val price: Double,
)

data class DeleteProductCommand(
    @TargetAggregateIdentifier
    val productId: String
)