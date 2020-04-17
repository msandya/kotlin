package com.sandya.demo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sandya.demo.ProductDetailAdapter
import com.sandya.demo.R
import com.sandya.demo.model.ProductFamily


class ProductDetailFragment : Fragment() {

    lateinit var product: ProductFamily

    companion object {
        const val KEY_PRODUCT = "KEY_PRODUCT"

        fun newInstance(productFamily: ProductFamily): ProductDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_PRODUCT, productFamily)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { product = it.getSerializable(KEY_PRODUCT) as ProductFamily }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager =
            LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)
        var productDetailAdapter: ProductDetailAdapter = ProductDetailAdapter(product)
        recyclerView.adapter = productDetailAdapter

    }
}