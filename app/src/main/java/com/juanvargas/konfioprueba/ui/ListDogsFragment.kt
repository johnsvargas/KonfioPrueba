package com.juanvargas.konfioprueba.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.juanvargas.konfioprueba.DogApplication
import com.juanvargas.konfioprueba.R
import com.juanvargas.konfioprueba.databinding.FragmentListOfDogsBinding
import com.juanvargas.konfioprueba.ui.adapters.DogAdapters
import com.juanvargas.konfioprueba.viewmodel.ListDogsModelFactory
import com.juanvargas.konfioprueba.viewmodel.ListDogsViewModel

class ListDogsFragment: Fragment()  {
    private lateinit var binding: FragmentListOfDogsBinding

    private val viewModel: ListDogsViewModel by viewModels {
        val application =  activity?.application as DogApplication
        ListDogsModelFactory(application.database.dogDao())
    }


    private val adapter by lazy{ DogAdapters() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_of_dogs,
            container,false)

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.run {
            toolbarListOfDogs.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            rvListOfDogs.adapter = adapter

            viewModel.listOfDogs.observe(viewLifecycleOwner, {state->
                state.fold({
                    if(it.isNotEmpty()) {
                        adapter.setListOfDogs(it)
                        viewModel.clearListOfDogs()
                    }
                }, { e, _ ->
                    Log.e(ListDogsFragment::class.java.simpleName ,e.message.toString())
                })
            })

            viewModel.isLanding.observe(viewLifecycleOwner){
                pbListOfDogs.isVisible = it
                Log.d(ListDogsFragment::class.java.simpleName ,it.toString())
            }
        }

        viewModel.getListOfDogs()
    }
}