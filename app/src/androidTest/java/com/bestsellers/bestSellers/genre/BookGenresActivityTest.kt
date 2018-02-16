package com.bestsellers.bestSellers.genre

import android.support.test.runner.AndroidJUnit4
import com.bestsellers.bestSellers.base.BaseTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Rafaela Araujo
 * on 15/11/2017.
 */

@RunWith(AndroidJUnit4::class)
class BookGenresActivityTest : BaseTest() {

    private val robot: BookGenresRobot = BookGenresRobot()

    @Before
    fun init() {
        setup()
    }

    @Test
    fun selectedFirstItem_displayBestSellersList() {
        with(robot) {
            checkFirstItemText()
            selectListFirstItem()
            checkDisplayBestSellersList()
        }
    }

    @Test
    fun scrollToBottom_scrollToTop() {
        with(robot) {
            scrolltoBottom()
            scrolltoTop()
        }
    }

    @Test
    fun selectLastItem_displayBestSellersList() {
        with(robot) {
            scrolltoBottom()
            checkLastItemText()
            selectLastItem()
            checkDisplayBestSellersList()
        }
    }

    @Test
    fun selectSearchButtonAndMakeSearch_filterList() {
        with(robot) {
            selectSearchButton()
            addTextToSearchView()
            checkItemSearchDisplayed()
        }
    }

}