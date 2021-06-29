package com.liusaprian.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.utils.EspressoIdlingResource
import org.junit.*

class MainActivityTest {

    private val dummyMoviesLength = 20
    private val dummyTvShowsLength = 20

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_catalogue)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMoviesLength))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_catalogue)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowsLength))
    }

    @Test
    fun loadDetail() {
        onView(withId(R.id.rv_catalogue)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.back_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.rv_catalogue)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_btn)).perform(click())
        onView(withId(R.id.back_btn)).perform(click())
        onView(withId(R.id.favorite_page)).perform(click())
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_btn)).perform(click())
        onView(withId(R.id.back_btn)).perform(click())
        onView(withId(R.id.no_fav_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_catalogue)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_btn)).perform(click())
        onView(withId(R.id.back_btn)).perform(click())
        onView(withId(R.id.favorite_page)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_btn)).perform(click())
        onView(withId(R.id.back_btn)).perform(click())
        onView(withId(R.id.no_fav_movies)).check(matches(isDisplayed()))
    }
}