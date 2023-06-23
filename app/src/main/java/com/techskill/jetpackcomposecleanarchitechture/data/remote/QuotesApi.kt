package com.techskill.jetpackcomposecleanarchitechture.data.remote

import com.techskill.jetpackcomposecleanarchitechture.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuotesApi {

    @GET("/api/quotes")
    suspend fun getQuotesList() : List<QuoteDto>


    companion object{
        const val BASE_URL = "https://zenquotes.io/"
    }
}