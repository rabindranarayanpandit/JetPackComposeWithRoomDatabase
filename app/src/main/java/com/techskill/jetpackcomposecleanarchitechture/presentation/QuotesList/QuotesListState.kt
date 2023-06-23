package com.techskill.jetpackcomposecleanarchitechture.presentation.QuotesList

import com.techskill.jetpackcomposecleanarchitechture.domain.model.Quote

data class QuotesListState(val quotes:List<Quote> = emptyList(),
                           val isLoading:Boolean = false,
                           val isRefreshing:Boolean = false,
                           val error:String = "")
