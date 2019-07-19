package com.itaewonproject

import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.ExecutionException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test(){
        var num :Int =0
        for (i in 1..10) num += i
        val n = 55
        assertEquals(num,n)
    }

    @Test
    fun test2(){

        var str="{\n" +
                "   \"html_attributions\" : [],\n" +
                "   \"result\" : {\n" +
                "      \"name\" : \"Pl de Carles Buïgas\",\n" +
                "      \"rating\" : 4.3\n" +
                "   },\n" +
                "   \"status\" : \"OK\"\n" +
                "}"
        var str2=""
        try{
            str2 = WebParser.execute().get()

        }catch(e:InterruptedException){
            e.printStackTrace()
        }catch(e: ExecutionException){
            e.printStackTrace()
        }
        assertEquals(str,str2)

        assert(false)
    }

}
