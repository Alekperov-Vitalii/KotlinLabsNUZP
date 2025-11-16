import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlin.math.ln
import kotlin.math.tanh

fun seed(): String = "Alekperov-Vitalii"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun iCalculate(
    x0: Int = 111,
    x1: Int = -76,
    x2: Int = 20
): Double {
    val x0d = x0.toDouble()
    val x1d = x1.toDouble()
    val x2d = x2.toDouble()
    return ln(x0d * x0d * x0d + x1d * x1d * x1d + x2d * x2d * x2d)
}

fun dCalculate(
    x0: Double = 85.8,
    x1: Double = -33.0,
    x2: Double = 30.69
): Double {
    return tanh(x0 * x1 * x2)
}

fun strCalculate(x0: String, x1: String): Int {
    require(x0.length == x1.length && x0.isNotEmpty()) { "Рядки повинні мати однакову довжину" }

    var penalty = 0
    val halfIndex = x0.length / 2

    for (i in x0.indices) {
        if (x0[i] == 'A' || x0[i] == 'C') {
            if (x0[i] != x1[i]) {
                penalty += if (i < halfIndex) 1 else 2
            }
        }
    }
    return penalty
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    startTestUi(seed(), labNumber())
}