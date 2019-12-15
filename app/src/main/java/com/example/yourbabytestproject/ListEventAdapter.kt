package com.example.yourbabytestproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yourbabytestproject.databinding.TextItemViewBinding

class ListEventAdapter(val clickListener: ItemClickListener) :
    ListAdapter<ListItem, ListEventAdapter.ViewHolder>(ListItemDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: TextItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ListItem,
            clickListener: ItemClickListener
        ) {
            Log.i("Recycler", "BINDING")
            binding.item = item
            binding.title.text = item.title
            binding.shortDescription.text = item.shortDescription
            binding.clickListener = clickListener
            binding.itemIc.setImageResource(
                when (item.eventId) {
                    1 -> R.drawable.ic_u1
                    2 -> R.drawable.ic_u2
                    3 -> R.drawable.ic_u3
                    4 -> R.drawable.ic_doctor
                    5 -> R.drawable.ic_hypoderm_red
                    6 -> R.drawable.ic_hypoderm_yellow
                    else -> R.drawable.ic_doctor
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.eventId == newItem.eventId
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    class ItemClickListener(val clickListener: (eventId: Int) -> Unit) {
        fun onClick(item: ListItem) = clickListener(item.eventId)
    }


}