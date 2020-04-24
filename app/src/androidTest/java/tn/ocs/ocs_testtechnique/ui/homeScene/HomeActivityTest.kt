package tn.ocs.ocs_testtechnique.ui.homeScene

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule var activityTestRule = ActivityTestRule(HomeActivity::class.java)
    private lateinit var homeActivity: HomeActivity

    @Before
    fun setUp() {
        homeActivity = activityTestRule.activity
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testViews() {
        val viewModel = homeActivity.viewModel
        assertNotNull(viewModel)
    }

}