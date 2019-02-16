import java.lang.StringBuilder
import java.math.BigInteger
import java.security.MessageDigest

const val password0="a1b2"
const val password1="202cb962ac59075b964b07152d234b70"
const val password2="570a90bfbf8c7eab5dc5d4e26832d5b1"
const val password3="f78f2477e949bee2d12a2c540fb6084f"
const val password4="09408af74a7178e95b8ddd4e92ea4b0e"
const val password5="2034f6e32958647fdff75d265b455ebf"
const val password6="9b3af42d61cde121f40b96097fb77d3e"
var totalGuesses = 0

fun main() {

    System.out.println("helloworld".md5())
    println("50".leadingZeros(4))
//    val v = "00034".padStart(20, '0')
    //println(v)
    //println("'$v'")
}

fun String.md5(): String {
    val HEX_CHARS = "0123456789abcdef"
    val bytes = MessageDigest.getInstance("MD5").digest(toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }
    return result.toString()

}

fun String.leadingZeros(n: Int): String {

    return padStart(n, '0')
}

fun checkUserPassword(whichPassword: Int, password: String): Boolean{
    return when(whichPassword){
        0 -> password == password0
        1 -> password.md5() == password1
        2 -> password.md5() == password2
        3 -> password.md5() == password3
        4 -> password.md5() == password4
        5 -> password.md5() == password5
        6 -> password.md5() == password6
        else -> false

    }
}

fun searchMethodOne(numDigets: Int): Boolean{
    val start = System.currentTimeMillis()

    val searchTime = System.currentTimeMillis() - start

}