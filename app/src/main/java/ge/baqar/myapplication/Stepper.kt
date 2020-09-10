package ge.baqar.myapplication

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FruitRow(fruit: Fruit) {
    Column(modifier = Modifier.gravity(Alignment.CenterVertically)) {

        Text(text = "${fruit.name} ($${fruit.totalPrice})", style = MaterialTheme.typography.body1)
        Stepper(count = fruit.quantity.value, onCountChange = { fruit.quantity.value = it })
    }
}

@Composable
fun Stepper(count: Int, onCountChange: (Int) -> Unit) {
    val onRemoveOne = { onCountChange(count - 1) }
    val onAddOne = { onCountChange(count + 1) }

    Row {
        Button(
            onClick = {
                if (count > 0) onRemoveOne.invoke()
            }, modifier = Modifier.gravity(
                Alignment.CenterVertically
            ),
            backgroundColor = Color.Blue
        ) {
            Text(text = "-", style = MaterialTheme.typography.body1, color = Color.White)
        }

        Spacer(modifier = Modifier.width(24.dp))
        Stack(modifier = Modifier.width(100.dp)) {
            Text(text = "$count")
        }
        Spacer(modifier = Modifier.width(24.dp))

        Button(
            onClick = onAddOne, modifier = Modifier.gravity(
                Alignment.CenterVertically
            ),
            backgroundColor = Color.Yellow
        ) {
            Text(text = "+")
        }
    }
}