package com.example.ecom

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecom.model.product
import com.example.ecom.service.productApiInterface
import com.example.ecom.service.productApiService
import com.example.ecom.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment(), productClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product_list.layoutManager = GridLayoutManager(this.context,2)
        getProductData { products: List<product> ->
            product_list.adapter = productAdapter(products, this)
        }

    }



    private fun getProductData(callback: (List<product>) -> Unit){
        val apiService = productApiService.getInstance().create(productApiInterface::class.java)
        apiService.getProductList().enqueue(object : Callback<List<product>>{
            override fun onFailure(call: Call<List<product>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<List<product>>, response: Response<List<product>>) {
                return callback(response.body()!!)
            }
        })
    }

    override fun onClick(products: product) {

        val intent = Intent(getActivity()?.getApplicationContext(), detailActivity::class.java )
        intent.putExtra("name",products.title)
        intent.putExtra("id", products.id)
        intent.putExtra("description",products.description)
        intent.putExtra("category", products.category)
        intent.putExtra("image", products.image)
        intent.putExtra("price", products.price)
        startActivity(intent)
    }
}