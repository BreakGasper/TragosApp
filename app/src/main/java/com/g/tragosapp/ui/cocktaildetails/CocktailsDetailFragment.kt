package com.g.tragosapp.ui.cocktaildetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.g.tragosapp.R
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

@AndroidEntryPoint
class CocktailsDetailFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(img_cocktail)
        cocktail_title.text = drink.name
        cocktail_desc.text = drink.description

        btn_save_cocktail.setOnClickListener {
            viewModel.saveCocktail(DrinkEntity(drink.cocktailId,drink.image,drink.name,drink.description,drink.hasAlcohol))
            Toast.makeText(requireContext(), "Cocktail saved to favorites", Toast.LENGTH_SHORT)
                .show()
        }
    }
}