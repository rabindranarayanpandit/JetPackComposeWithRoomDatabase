package com.techskill.jetpackcomposecleanarchitechture.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.techskill.jetpackcomposecleanarchitechture.presentation.QuotesList.QuotesListEvent
import com.techskill.jetpackcomposecleanarchitechture.presentation.QuotesList.QuotesListItem
import com.techskill.jetpackcomposecleanarchitechture.presentation.viewmodel.QuotesListViewModel

@Composable
fun QuotesListScreen(
    viewModel: QuotesListViewModel = hiltViewModel()) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.onEvent(QuotesListEvent.Refresh)
    }) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.quotes){quote ->
                QuotesListItem(quote = quote)
            }
        }
    }


}