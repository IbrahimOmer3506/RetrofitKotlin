package com.oztasibrahimomer.retrofitkotlin2


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oztasibrahimomer.retrofitkotlin2.databinding.RecyclerRowBinding

class CryptoAdapter(val cryptoModels:ArrayList<CryptoModel>):RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    class CryptoHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {

        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CryptoHolder(binding)

    }

    override fun getItemCount(): Int {

        return cryptoModels.size

    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {

        holder.binding.currencyId.text=cryptoModels[position].currency
        holder.binding.priceId.text=cryptoModels[position].price


    }
}