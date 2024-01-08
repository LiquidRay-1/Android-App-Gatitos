package com.example.gatitos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gatitos.data.models.GatosItem
import com.example.gatitos.databinding.VistaCeldaBinding

class GatosAdapter
    (private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GatosAdapter.MiCelda>(), Filterable {

    private var gaticos = ArrayList<GatosItem>()
    private var gaticosCopia = ArrayList<GatosItem>()

    interface OnItemClickListener {
        fun onItemClick(personaje: GatosItem)
    }

    inner class MiCelda(val binding: VistaCeldaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaCeldaBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val gatos: GatosItem = gaticos.get(position)
        holder.binding.textView.text = gatos.name
        holder.binding.textView2.text = gatos.origin

        //Glide.with(holder.itemView).load(gatos.image).into(holder.binding.ivImage)

        holder.itemView.setOnClickListener {
            listener.onItemClick(gatos)
        }

    }

    override fun getItemCount(): Int {
        return gaticos.size
    }

    fun updateList(lista: List<GatosItem>) {
        gaticos.clear()
        gaticos.addAll(lista)
        gaticosCopia.clear()
        gaticosCopia.addAll(lista)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val busqueda = constraint.toString()

                gaticos = gaticosCopia.filter {
                    it.name?.lowercase()?.contains(busqueda.lowercase()) ?: false
                } as ArrayList<GatosItem>
                val filterResult = FilterResults()
                filterResult.values = gaticos
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                gaticos = results?.values as ArrayList<GatosItem>
                notifyDataSetChanged()
            }

        }
    }
}