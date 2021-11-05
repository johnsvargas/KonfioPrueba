package com.juanvargas.konfioprueba.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanvargas.konfioprueba.data.model.Dog
import com.juanvargas.konfioprueba.databinding.ItemDogBinding

class DogAdapters: RecyclerView.Adapter<DogAdapters.ItemDogVideoHolder>() {
    private val list:ArrayList<Dog> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDogVideoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemDogBinding.inflate(layoutInflater, parent, false)
        return ItemDogVideoHolder(view)
    }

    override fun onBindViewHolder(holder: ItemDogVideoHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()= list.size

    fun setListOfDogs(list:ArrayList<Dog>){

        val initialPosition = this.list.size
        this.list.addAll(list)
        notifyItemRangeChanged(initialPosition,this.list.size)
    }

    inner class ItemDogVideoHolder(private val binding: ItemDogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dog:Dog){
            with(binding){
                this.dog = dog
            }
        }
    }

}