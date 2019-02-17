import java.io.File
import java.lang.StringBuilder
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.math.pow

const val password0="abc"
//const val password0="0012"
const val password1="202cb962ac59075b964b07152d234b70" //1234 found with method 1
const val password2="570a90bfbf8c7eab5dc5d4e26832d5b1" //fred found with method 3
const val password3="f78f2477e949bee2d12a2c540fb6084f" //tigger found with method 3
const val password4="09408af74a7178e95b8ddd4e92ea4b0e" //Dragon+Hunter found with method 4
const val password5="2034f6e32958647fdff75d265b455ebf" //secretpassword found with method 4 no punctuation
const val password6="9b3af42d61cde121f40b96097fb77d3e"
var totalGuesses = 0

const val whichPassword = 5

fun main() {

    val file = File(ClassLoader.getSystemResource("passwords.txt").file)
//    println(file.absolutePath)

    var foundIt = false
    if(!foundIt)
        foundIt = searchMethodThree(file)
    if(!foundIt)
        foundIt = searchMethodFour(file)
    if(!foundIt)
        foundIt = searchMethodOne(1)
    if(!foundIt)
        foundIt = searchMethodOne(2)
    if(!foundIt)
        foundIt = searchMethodOne(3)
    if(!foundIt)
        foundIt = searchMethodOne(4)
    if(!foundIt)
        foundIt = searchMethodTwo(6)
    if(!foundIt)
        foundIt = searchMethodOne(7)
    if(!foundIt)
        foundIt = searchMethodOne(8)



//    System.out.println("helloworld".md5())
//    println("50".leadingZeros(4))
////    val v = "00034".padStart(20, '0')
    //println(v)
    //println("'$v'")

//    if(searchMethodOne(0, 2))
//        println("Found the password!!")

//    val blah = "HelloWorld".toCharArray()
//    println("This is 0: ${blah[0]}")
//
//    if(searchMethodTwo(0, 3))
//        println("FOUND THE PASSWORD!!!")

//    when {
//        true ->
//            {println("1st lineasdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//            println("1+2")}
//        true -> println("second line")
//        false -> println("third line")
//    }
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

fun checkUserPassword(password: String): Boolean{
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
/*
This doesn't work for passwords longer than 10 characters, because of the limitations of int.
Also won't work if the password is longer than the number of digits given to the method.

Probably possible to make it a little more robust...
 */
fun searchMethodOne(numDigets: Int): Boolean{
    val start = System.currentTimeMillis()
    var guess: String
    var result = false
    var numTests = 0
    var stillSearching = true
    val maxNum = 10.toDouble().pow(numDigets).toInt()
    var a = 0

    while (stillSearching && a<maxNum){
        guess = a.toString().leadingZeros(numDigets)
//        println(guess)
        numTests+=1
        totalGuesses+=1
        if(checkUserPassword(guess)){
            println("Success! Password $whichPassword is $guess")
            stillSearching = false
            result = true
        }
        a+=1
    }
    val searchTime = System.currentTimeMillis() - start
    println("Time to search was: ${searchTime/1000/60} minutes ${searchTime/1000%60} seconds")
    println("Time in milliseconds: $searchTime")
    return result
}

/*
Funtion seems to ignore spaces?  so if you had a password: "ab cd"  then it wouldn't be able to guess it.
Also not sure what the purpose of the space is in the wheel variable? seems like the checking function ignores the space
 */
fun searchMethodTwo(numPassWheels: Int): Boolean{
    val wheel = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()

    var result = false
    val start = System.currentTimeMillis()
    var numTests = 0
    var stillSearching = true

    if(numPassWheels > 8)
        stillSearching = false

    var passWheelArray = intArrayOf(1,0,0,0,0,0,0,0,0)

    while(stillSearching){
        var guess = ""
        for (i in 0..numPassWheels){
            if (passWheelArray[i] > 0)
                guess = wheel[passWheelArray[i]] + guess

        }
//        println(guess)
        if (checkUserPassword(guess)){
            println("Success! Password $whichPassword is $guess")
            stillSearching = false
            result = true
        }

        numTests+=1
        totalGuesses+=1

        var carry = 1
        for (i in 0..numPassWheels){
            passWheelArray[i] = passWheelArray[i] + carry
            carry = 0
            if (passWheelArray[i] > 62){
                passWheelArray[i] = 1
                carry = 1
                if (i == (numPassWheels-1))
                    stillSearching = false
            }
        }

    }

    val searchTime = System.currentTimeMillis() - start
    println("Time to search was: ${searchTime/1000/60} minutes ${searchTime/1000%60} seconds")
    println("Time in milliseconds: $searchTime")
    return result
}

fun searchMethodThree(file: File): Boolean {
    var result = false

    val words = file.bufferedReader().readLines()

    val start = System.currentTimeMillis()
    var stillSearching = true
    var numTests = 0
    var wordCount = 0
    var guess: String
    while (stillSearching){
        guess = words[wordCount]

        when{
            checkUserPassword(guess)->{
                println("Method 3 lowercase Success! Password $whichPassword is $guess")
                stillSearching = false
                result = true
            }
            checkUserPassword(guess.capitalize()) -> {
                println("Method 3 capitalize Success! Password $whichPassword is ${guess.capitalize()}")
                stillSearching = false
                result = true
            }
        }

        numTests+= 1
        totalGuesses+=1
        wordCount+=1
        if(wordCount >= words.size)
            stillSearching = false


    }

    val searchTime = System.currentTimeMillis() - start
    println("Time to search was: ${searchTime/1000/60} minutes ${searchTime/1000%60} seconds")
    println("Time in milliseconds: $searchTime")
    return result
}

fun searchMethodFour(file: File): Boolean{
    var result = false

    val words = file.bufferedReader().readLines()

    val start = System.currentTimeMillis()
    var stillSearching = true
    var numTests = 0
    var wordCount = 0
    var wordCount2 = 0
    var guess: String
    var punCount = 0

    val punctuation = "~!@#$%^&*()_-+={}[]:<>,./X".toCharArray()

    while (stillSearching){

        when{
            //If at the end of the punction leave it out
            'X' == punctuation[punCount] -> {
                if(checkUserPassword(words[wordCount] + words[wordCount2])){
                    stillSearching = false
                    result = true
                    numTests+=1
                    totalGuesses+=1
                    println("No punctuation Success! Password $whichPassword is ${words[wordCount] + words[wordCount2]}")
                }
            }
            //Try words and punctuation
            checkUserPassword(words[wordCount] + punctuation[punCount] + words[wordCount2]) -> {
                guess = words[wordCount] + punctuation[punCount] + words[wordCount2]
                stillSearching = false
                result = true
                numTests+=1
                totalGuesses+=1
                println("Punctuation Success! Password $whichPassword is $guess")
            }
            //Try 1st word capitlized and punctuation
            checkUserPassword(words[wordCount].capitalize() + punctuation[punCount] + words[wordCount2]) -> {
                guess = words[wordCount].capitalize() + punctuation[punCount] + words[wordCount2]
                stillSearching = false
                result = true
                numTests+=1
                totalGuesses+=1
                println("1st word Cap Success! Password $whichPassword is $guess")
            }
            //Try 2nd word capitalized and punctuation
            checkUserPassword(words[wordCount] + punctuation[punCount] + words[wordCount2].capitalize()) -> {
                guess = words[wordCount] + punctuation[punCount] + words[wordCount2].capitalize()
                stillSearching = false
                result = true
                numTests+=1
                totalGuesses+=1
                println("2nd word Cap Success! Password $whichPassword is $guess")
            }
            //Try both words capitalized and punctuation
            checkUserPassword(words[wordCount].capitalize() + punctuation[punCount] + words[wordCount2].capitalize()) -> {
                guess = words[wordCount].capitalize() + punctuation[punCount] + words[wordCount2].capitalize()
                stillSearching = false
                result = true
                numTests+=1
                totalGuesses+=1
                println("Both words Cap Success! Password $whichPassword is $guess")
            }
        }
        wordCount+=1
        if (wordCount >= words.size){
            wordCount = 0
            punCount+=1
            if (punCount >= punctuation.size){
                punCount = 0
                wordCount2+=1
                if (wordCount2 >= words.size){
                    stillSearching = false
                }
            }
        }

    }

    val searchTime = System.currentTimeMillis() - start
    println("Time to search was: ${searchTime/1000/60} minutes ${searchTime/1000%60} seconds")
    println("Time in milliseconds: $searchTime")

    return result
}