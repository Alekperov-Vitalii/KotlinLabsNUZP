import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlin.math.ln
import kotlin.math.tanh

fun seed(): String = "Alekperov-Vitalii"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun iCalculate(): Double {
    val x0 = 111
    val x1 = -76
    val x2 = 20
    val x0Cube = x0.toDouble() * x0 * x0
    val x1Cube = x1.toDouble() * x1 * x1
    val x2Cube = x2.toDouble() * x2 * x2
    return ln(x0Cube + x1Cube + x2Cube)
}

fun dCalculate(): Double {
    val x0 = 85.8
    val x1 = -33.0
    val x2 = 30.69
    return tanh(x0 * x1 * x2)
}

fun strCalculate(x0: String, x1: String): Int {
    require(x0.length == x1.length) { "Рядки повинні мати однакову довжину" }
    require(x0.isNotEmpty()) { "Рядки не повинні бути порожніми" }

    var penalty = 0
    val halfIndex = (x0.length + 1) / 2

    var i = 0
    while (i < x0.length) {
        if (x0[i] != x1[i]) {
            penalty += if (i < halfIndex) 1 else 2
        }
        i += 2
    }
    return penalty
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")
    startTestUi(seed(), labNumber())
}