package com.oztasibrahimomer.retrofitkotlin2

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CryptoApi {


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")

    fun getData():Observable<List<CryptoModel>>
}