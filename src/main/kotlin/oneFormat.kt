import java.io.File

/*
permutations tried.
All words uppercase ---no luck
1st word lower case, last 2 uppercase ---password3.txt file no luck yet

Tying over night
all words, first and last capitalized -- no luck
all words, first and second capitalized -- no luck
all words, middle word capitalized -- no luck
just 1st word capitlaized
 */

fun main() {

    val file = File(ClassLoader.getSystemResource("passwords1.txt").file)

    if(searchMethodFourPlusOneFormat(file))
        println("FINNALLY FOUND IT!!!")

//    if(logicTest(file))
//        println("LOGIC WORKS!!!!")
    else
        println("NO LUCK")

//    val fileNew = "passwordsCapitalized.txt"

//    val words = file.bufferedReader().readLines()

//    var wordCount = 0
//
//    while(wordCount <= words.size){
//        File(fileNew).appendText(words[wordCount].capitalize()+ "\n")
//        println(words[wordCount].capitalize())
//        wordCount+=1
//    }


}

fun searchMethodFourPlusOneFormat(file:File): Boolean{

    val words = file.bufferedReader().readLines()

    var wordCount = 0
    var wordCount2 = 0
    var wordCount3 = 0
    var punCount = 0
    var punCount2 = 0
    var stillSearching = true
    var result = false
    var guess: String

    val punctuation = "~!@#$%^&*()_-+={}[]:<>,./X".toCharArray()


    while(stillSearching){
//        println(words[wordCount]+punctuation[punCount] +words[wordCount2]+words[wordCount3])
//        println((words[wordCount] + punctuation[punCount] + words[wordCount2] + words[wordCount3]).md5())
           if( (words[wordCount] + punctuation[punCount] + words[wordCount2].toLowerCase() + punctuation[punCount2] + words[wordCount3].toLowerCase()).md5() == password7){
               guess = words[wordCount] + punctuation[punCount] + words[wordCount2].toLowerCase() + punctuation[punCount2] + words[wordCount3].toLowerCase()
               println("FINALLY FOUND IT: $guess")
               stillSearching = false
               result = true
           }
//            if( (words[wordCount] + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3].toLowerCase()).md5() == password7){
//                guess = words[wordCount] + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3].toLowerCase()
//                println("FINALLY FOUND IT: $guess")
//                stillSearching = false
//                result = true
//            }
//            if( (words[wordCount].toLowerCase() + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3].toLowerCase()).md5() == password7){
//                guess = words[wordCount].toLowerCase() + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3].toLowerCase()
//                println("FINALLY FOUND IT: $guess")
//                stillSearching = false
//                result = true
//            }
        wordCount+=1
        if(wordCount >= words.size){
            wordCount = 0
            punCount+=1
            if(punCount >= punctuation.size){
                punCount = 0
                wordCount2+=1
                if (wordCount2 >= words.size){
                    wordCount2 = 0
                    punCount2+=1
                    if (punCount2 >= punctuation.size){
                        punCount2 = 0
                        wordCount3+=1
                        if(wordCount3 < words.size)
                            println("WordCount3 is on: ${words[wordCount3]}")
                        if(wordCount3 >= words.size){
                            stillSearching = false
                        }
                    }
                }
            }
        }
    }


    return result
}

fun logicTest(file: File): Boolean{
    val testString = "Albert!Billy(Phantom".md5()

    val words = file.bufferedReader().readLines()

    var wordCount = 0
    var wordCount2 = 0
    var wordCount3 = 0
    var punCount = 0
    var punCount2 = 0
    var stillSearching = true
    var result = false
    var guess: String

    val punctuation = "~!@#$%^&*()_-+={}[]:<>,./X".toCharArray()


    while(stillSearching){
//        println(words[wordCount]+punctuation[punCount] +words[wordCount2]+words[wordCount3])
//        println((words[wordCount] + punctuation[punCount] + words[wordCount2] + words[wordCount3]).md5())
        if( (words[wordCount] + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3]).md5() == testString){
            guess = words[wordCount] + punctuation[punCount] + words[wordCount2] + punctuation[punCount2] + words[wordCount3]
            println("FINALLY FOUND IT: $guess")
            stillSearching = false
            result = true
        }
        wordCount+=1
        if(wordCount >= words.size){
            wordCount = 0
            punCount+=1
            if(punCount >= punctuation.size){
                punCount = 0
                wordCount2+=1
                if (wordCount2 >= words.size){
                    wordCount2 = 0
                    punCount2+=1
                    if (punCount2 >= punctuation.size){
                        punCount2 = 0
                        wordCount3+=1
                        if(wordCount3<=words.size)
                            println("WordCount3 is on: ${words[wordCount3]}")
                        if(wordCount3 >= words.size){
                            stillSearching = false
                        }
                    }
                }
            }
        }
    }


    return result

}