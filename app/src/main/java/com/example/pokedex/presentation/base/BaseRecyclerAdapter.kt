package com.example.pokedex.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

abstract class BaseRecyclerAdapter<V, K : RecyclerView.ViewHolder> : RecyclerView.Adapter<K>() {

    protected val mData: MutableList<V> = ArrayList()

    var onItemClickListener: ((item: V) -> Unit)? = null

    var data: MutableList<V>
        get() = mData
        set(data) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }

    val isEmpty: Boolean
        get() = mData.isEmpty()

    abstract override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): K

    abstract override fun onBindViewHolder(k: K, position: Int)

    override fun getItemCount(): Int {
        return mData.size
    }

}
