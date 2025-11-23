import com.diacht.ktest.compose.startTestUi
import com.diacht.ktest.juicefactory.JuiceFactory
import org.example.helloworld.BuildConfig


fun seed(): String = "Alekperov-Vitalii"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun getSimulationObject() = JuiceFactory()

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    startTestUi(seed(), labNumber())
}