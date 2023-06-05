package com.example.sneakerapp

import android.annotation.SuppressLint
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilsTest {

    @Mock
    private var priceList = mutableListOf<Int>()

    @Before
    fun createList() {
        priceList = mutableListOf(1000, 500, 400)
    }

    @SuppressLint("CheckResult")
    @Test
    fun testTotal() {
        assertEquals(1900, Utils.getTotal(priceList))
    }
}