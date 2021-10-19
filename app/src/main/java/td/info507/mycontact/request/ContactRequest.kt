package td.info507.mycontact.request

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.mycontact.dialog.Updatable
import td.info507.mycontact.model.Contact

class ContactRequest(private val context: Context, updatable: Updatable) {
    companion object {
        private const val URL = ""
    }

    init {
        val queue = Volley.newRequestQueue(context)
        val req = JsonObjectRequest(
            Request.Method.GET, URL, null,
            { response ->
                refresh(response)
                updatable.update()
                Toast.makeText(context, R.string.request_success, Toast.LENGTH_SHORT).show()
            },
            { error ->
                println(error)
                Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT)
            }
        )
        queue.add(req)
        queue.start()
    }

    private fun refresh(json: JSONObject) {
        delete()
        insert(json)
    }

    private fun delete() {
        for (contact in ContactStorage.get(context).findAll()) {
            ContactStorage.get(context).delete(contact.id)
        }
    }

    private fun insert(json: JSONObject) {
        val contacts = json.getJSONArray("contacts")
        for (i in 0 until contacts.length()) {
            val contact = contacts.getJSONObject(i)
            ContactStorage.get(context).insert(
                Contact(
                    0,
                    contact.getString(Contact.CONTACT_FIRST_NAME),
                    contact.getString(Contact.CONTACT_LAST_NAME),
                    contact.getString(Contact.CONTACT_PHONE),
                    contact.getString(Contact.CONTACT_HOME),
                    contact.getString(Contact.CONTACT_MAIL),
                    contact.getString(Contact.CONTACT_LOCATION),
                )
            )
        }
    }
}