package com.example.sneakerapp

import android.content.Context
import java.io.IOException

class Utils {

    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getTotal(priceList: List<Int>): Int {
            return priceList.stream().mapToInt(Integer::valueOf).sum()
        }
    }
}