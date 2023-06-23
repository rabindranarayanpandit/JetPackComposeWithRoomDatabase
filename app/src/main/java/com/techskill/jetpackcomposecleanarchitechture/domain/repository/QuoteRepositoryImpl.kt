package com.techskill.jetpackcomposecleanarchitechture.domain.repository

import com.techskill.jetpackcomposecleanarchitechture.data.local.QuoteDatabase
import com.techskill.jetpackcomposecleanarchitechture.data.mapper.toQuote
import com.techskill.jetpackcomposecleanarchitechture.data.mapper.toQuoteEntity
import com.techskill.jetpackcomposecleanarchitechture.data.remote.QuotesApi
import com.techskill.jetpackcomposecleanarchitechture.domain.model.Quote
import com.techskill.jetpackcomposecleanarchitechture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val api: QuotesApi,
    private val db : QuoteDatabase
) :QuoteRepository{
    private val dao = db.dao

    override suspend fun getQuotesList(fetchFromRemote: Boolean): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading(true))
            val localQuotesList = dao.getStoredQuotesList()
            emit(Resource.Success(
                data = localQuotesList.map {it.toQuote()}
            ))

            val isDbEmpty = localQuotesList.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteQuotesList = try {
                val response = api.getQuotesList()
                response.map { it.toQuote() }
            }catch (e: IOException){
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            }catch (e: HttpException){
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            }

            remoteQuotesList?.let { quotes ->
                dao.deleteQuoteList()
                dao.insertQuotesList(quotes.map { it.toQuoteEntity() })
                emit(Resource.Success(dao.getStoredQuotesList().map { it.toQuote() }))
                emit(Resource.Loading(false))

            }
        }
    }
}