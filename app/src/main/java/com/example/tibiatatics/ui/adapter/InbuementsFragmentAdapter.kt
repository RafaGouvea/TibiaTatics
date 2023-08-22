package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tibiatatics.R
import com.example.tibiatatics.model.InbuementModel

class InbuementsFragmentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var inbuement : List<InbuementModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return InbuementsViewHolder(
            LayoutInflater.from(
                parent.context).inflate(R.layout.item_inbuements_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return inbuement.size
    }

    fun setDataSet(inbuements: List<InbuementModel>){

        this.inbuement = inbuements

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder){
            is InbuementsViewHolder -> {
                holder.bind(inbuement[position])
            }
        }

    }

    class InbuementsViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        private val inbuementType =  itemView.findViewById<TextView>(R.id.type_inbuement)
        private val name = itemView.findViewById<TextView>(R.id.name_inbuement)
        private val iconInbuement = itemView.findViewById<ImageView>(R.id.icon_inbuement)
        private val urlImgItem1 = itemView.findViewById<ImageView>(R.id.url_item_1)
        private val nameItem1 = itemView.findViewById<TextView>(R.id.name_item_1)
        private val urlImgItem2 = itemView.findViewById<ImageView>(R.id.url_item_2)
        private val nameItem2 = itemView.findViewById<TextView>(R.id.name_item_2)
        private val urlImgItem3 = itemView.findViewById<ImageView>(R.id.url_item_3)
        private val nameItem3 = itemView.findViewById<TextView>(R.id.name_item_3)

        fun bind(inbuementModel: InbuementModel){

            inbuementType.text = inbuementModel.inbuementType
            name.text = inbuementModel.name
            nameItem1.text = inbuementModel.nameItem1
            nameItem2.text = inbuementModel.nameItem2
            nameItem3.text = inbuementModel.nameItem3

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(inbuementModel.iconInbuement)
                .into(iconInbuement)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(inbuementModel.urlImgItem1)
                .into(urlImgItem1)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(inbuementModel.urlImgItem2)
                .into(urlImgItem2)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(inbuementModel.urlImgItem3)
                .into(urlImgItem3)



        }

    }

}