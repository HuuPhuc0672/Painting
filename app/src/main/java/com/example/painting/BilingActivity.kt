package com.example.painting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.painting.Adapter.BilingAdapter
import com.example.painting.R
private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"
class BilingActivity : AppCompatActivity(), ProductDetailsResponseListener,

    PurchasesUpdatedListener {
    private val rcvView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcvView) }
    private var listDetails = mutableListOf<ProductDetails>()
    private lateinit var billingClient: BillingClient
    private lateinit var adapter: BilingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biling)

        buildingBillingFunc()
        getListItemAny()
        adapter = BilingAdapter(this,billingClient,listDetails)
        rcvView.layoutManager = LinearLayoutManager(this)
        rcvView.adapter = adapter
    }

    private fun getListItemAny() {
        val list = mutableListOf<QueryProductDetailsParams.Product>()
        list.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(inapp_type_1)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        list.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(inapp_type_2)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        list.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(inapp_type_3)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        list.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(inapp_type_4)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        list.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(inapp_type_5)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        val params = QueryProductDetailsParams.newBuilder().setProductList(list).build()
        billingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList: MutableList<ProductDetails> ->
            listDetails = productDetailsList
            adapter = BilingAdapter(this,billingClient,productDetailsList)
            rcvView.adapter = adapter
            adapter.loadData(listDetails)
        }

    }


    private fun buildingBillingFunc() {
        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, mutableList -> }
        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                TODO("Not yet implemented")
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    getListItemAny()
                }

            }
        })

    }

    private fun handleItemAlrealyPurchases(purchases: List<Purchase>) {
        getListItemAny()
    }

    override fun onProductDetailsResponse(p0: BillingResult, p1: MutableList<ProductDetails>) {

    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {
        if (p0.getResponseCode() == BillingClient.BillingResponseCode.OK && p1 != null)
            handleItemAlrealyPurchases(p1)
    }
}