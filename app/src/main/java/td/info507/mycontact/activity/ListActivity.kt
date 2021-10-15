package td.info507.mycontact.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import td.info507.mycontact.model.Contact
import td.info507.mycontact.R
import td.info507.mycontact.adapter.ContactAdapter

class ListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CONTACT = "EXTRA_CONTACT"
    }

    var contacts : ArrayList<Contact> = arrayListOf()
    lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        contacts.add(
            Contact("Pajean", "Romain", "0682369547",
                "0452369874", "ror@gmail.com", "130 rue de la calvitie Le Bourget du Lac")
        )

        list = findViewById<RecyclerView>(R.id.contact_list)

        list.adapter = object : ContactAdapter(contacts){
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, MainActivity::class.java).apply{
                    putExtra(
                        EXTRA_CONTACT,
                        contacts.get(list.getChildViewHolder(view).adapterPosition)
                    )
                }
                startActivity(intent)
            }
            override fun onLongItemClick(view: View): Boolean {
                Toast.makeText(applicationContext, "Je veux supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    }

}