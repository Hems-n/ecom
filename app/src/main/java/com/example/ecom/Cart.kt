package com.example.ecom

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecom.databinding.FragmentCartBinding
import com.example.ecom.model.product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_cart.productCart
import java.lang.reflect.Type

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Cart : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentCartBinding
    lateinit var pList: ArrayList<product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sh = requireActivity().applicationContext.getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val gson = Gson()
        val json = sh.getString("LIST", null)
        val type: Type = object : TypeToken<ArrayList<product?>?>() {}.type

        pList = gson.fromJson<Any>(json, type) as ArrayList<product>

        if (pList == null) {
            pList = ArrayList()
        }
        productCart.layoutManager = LinearLayoutManager(this.context)
        productCart.adapter = cartAdapter(pList)

    }
}