package com.techskill.jetpackcomposecleanarchitechture.data.mapper

import com.techskill.jetpackcomposecleanarchitechture.data.local.QuoteEntity
import com.techskill.jetpackcomposecleanarchitechture.data.remote.dto.QuoteDto
import com.techskill.jetpackcomposecleanarchitechture.domain.model.Quote

fun QuoteEntity.toQuote(): Quote {
    return Quote(
        quote = quote,
        author = author
    )
}

fun Quote.toQuoteEntity():QuoteEntity{
    return QuoteEntity(
        quote = quote,
        author = author
    )
}

fun QuoteDto.toQuote():Quote{
    return Quote(
        quote = q,
        author = a
    )
}