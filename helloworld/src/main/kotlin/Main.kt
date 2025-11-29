import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlinx.coroutines.*
import java.net.URL
import kotlin.math.pow

fun seed(): String = "Alekperov-Vitalii"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

suspend fun getNumberFromServer(message: String): Int {
    return withContext(Dispatchers.IO) {
        val url = URL("http://diacht.2vsoft.com/api/send-number?message=$message")
        val connection = url.openConnection()
        connection.connect()
        val input = connection.getInputStream()
        val buffer = ByteArray(128)
        val bytesRead = input.read(buffer)
        input.close()
        String(buffer, 0, bytesRead).toInt()
    }
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val deferredNumbers = strList.map { message ->
        async { getNumberFromServer(message) }
    }

    val numbers = deferredNumbers.awaitAll()

    val minValue = numbers.minOrNull() ?: throw IllegalStateException("Список чисел порожній")

    val result = if (minValue >= 0) {
        minValue.toDouble().pow(1.0 / 3.0)
    } else {
        -(-minValue.toDouble()).pow(1.0 / 3.0)
    }

    result
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    startTestUi(seed(), labNumber())

}