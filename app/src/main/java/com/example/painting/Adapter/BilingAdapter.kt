package com.example.painting.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import com.example.painting.BilingActivity
import com.example.painting.R

class BilingAdapter(
    val app: BilingActivity,
    val billingClient: BillingClient,
    val list: MutableList<ProductDetails> = mutableListOf()
):RecyclerView.Adapter<BilingAdapter.ProductInAppViewHolder>() {
    fun loadData(list : List<ProductDetails>){
        if (list != null && list.size>0){
            this.list.clear()
            this.list.addAll(list)
        }

    }

    class ProductInAppViewHolder(view : View): RecyclerView.ViewHolder(view) {
         val txtMony: TextView by lazy { view.findViewById<TextView>(R.id.txt_mony) }
         val btnBuyGoi: TextView by lazy { view.findViewById<TextView>(R.id.btn_buy_goi) }
         val txtName: TextView by lazy { view.findViewById<TextView>(R.id.txt_Name) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInAppViewHolder {
        return ProductInAppViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_biling,parent,false))
    }

    override fun onBindViewHolder(holder: ProductInAppViewHolder, position: Int) {
        val item = list[position]
        holder.txtName.text = item.title
        holder.txtMony.text = item.oneTimePurchaseOfferDetails!!.formattedPrice
        holder.btnBuyGoi.setOnClickListener {
            val params = BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(item).build()
            val list = mutableListOf<BillingFlowParams.ProductDetailsParams>()
            list.add(params)
            val billingFlowParams : BillingFlowParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(list).build()
            val res : Int = billingClient.launchBillingFlow(app,billingFlowParams).responseCode

            when(res){
                BillingClient.BillingResponseCode.BILLING_UNAVAILABLE->{
                    Toast.makeText(app, "BILLING_UNAVAILABLE", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.DEVELOPER_ERROR->{
                    Toast.makeText(app, "DEVELOPER_ERROR", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED->{
                    Toast.makeText(app, "FEATURE_NOT_SUPPORTED", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED->{
                    Toast.makeText(app, "ITEM_ALREADY_OWNED", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.SERVICE_DISCONNECTED->{
                    Toast.makeText(app, "SERVICE_DISCONNECTED", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.SERVICE_TIMEOUT->{
                    Toast.makeText(app, "SERVICE_TIMEOUT", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}