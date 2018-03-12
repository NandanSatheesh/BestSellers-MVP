package com.bestsellers.bestSellers.bookDetails

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
class BookDetailsActivityTest : BaseTest() {

    private val robot: BookDetailsRobot = BookDetailsRobot()

    @Before
    fun init() {
        setup()
        robot.initDetailsView()
    }

    @Test
    fun checkBookData() {
        with(robot) {
            checkBookTitle()
            checkBookWriter()
            checkBookDescription()
            checkBookPosition()
        }
    }

    @Test
    fun checkFavoriteButton_showMessageChangeStatus() {
        with(robot) {
            selectFavoriteButton()
            checkFavoriteMessageDisplayed()
            selectFavoriteButton()
            checkUnfavoriteMessageDisplayed()
        }
    }

    @Test
    fun selectBuyButton_openAmozonUrl() {
        robot.selectBuyButton()
    }


}