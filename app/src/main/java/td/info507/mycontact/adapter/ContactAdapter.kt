package td.info507.mycontact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.mycontact.model.Contact
import td.info507.mycontact.R

abstract class ContactAdapter(val contacts : List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactHolder>(){

        class ContactHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val photo = itemView.findViewById<ImageView>(R.id.contact_photo)
            val name = itemView.findViewById<TextView>(R.id.contact_name)
        }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
            view.setOnClickListener{view -> onItemClick(view) }
            view.setOnLongClickListener{ view -> onLongItemClick(view) }
            return ContactHolder(view)
        }

        override fun onBindViewHolder(holder: ContactHolder, position: Int) {
            holder.name.text = contacts.get(position).name
        }

        override fun getItemCount(): Int {
            return contacts.size
        }
    }
