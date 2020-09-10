package ge.baqar.myapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Fruit(
    val name: String,
    val price: MutableState<Double> = mutableStateOf(2.0),
    var quantity: MutableState<Int> = mutableStateOf(0)
) {

    val totalPrice: Double
        get() = price.value * quantity.value

}