package com.oztasibrahimomer.retrofitkotlin2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.oztasibrahimomer.retrofitkotlin2.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding


    val Base_Url="https://raw.githubusercontent.com/"

    private var compositeDisposable:CompositeDisposable?=null


    private var cryptoModels:ArrayList<CryptoModel>?=null

    var myAdapter:CryptoAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json


        compositeDisposable=CompositeDisposable()

        binding.recyclerView.layoutManager=GridLayoutManager(this,2)
        loadData()
    }


    private fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CryptoApi::class.java)


        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handlerResponse))


    }
    private fun handlerResponse(cryptoList:List<CryptoModel>){


        cryptoModels= ArrayList(cryptoList)

        cryptoModels?.let {

            myAdapter=CryptoAdapter(it)


            myAdapter?.let {

                binding.recyclerView.adapter=myAdapter
            }





        }
    }
}