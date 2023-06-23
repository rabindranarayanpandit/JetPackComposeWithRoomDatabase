package com.techskill.jetpackcomposecleanarchitechture.domain.repository

import com.techskill.jetpackcomposecleanarchitechture.domain.model.Quote
import com.techskill.jetpackcomposecleanarchitechture.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun getQuotesList(fetchFromRemote:Boolean): Flow<Resource<List<Quote>>>
}