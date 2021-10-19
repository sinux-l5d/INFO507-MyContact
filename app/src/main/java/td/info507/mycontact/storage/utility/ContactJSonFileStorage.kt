package td.info507.mycontact.storage.utility

import android.content.Context
import org.json.JSONObject
import td.info507.mycontact.model.Contact
import td.info507.mycontact.storage.utility.file.JSonFileStorage

class ContactJSonFileStorage private constructor(context: Context) :
    JSonFileStorage<Contact>(context, NAME) {
    companion object {
        private const val NAME = "contact"

        private var STORAGE: ContactJSonFileStorage? = null

        fun get(context: Context) : ContactJSonFileStorage {
            if (STORAGE == null) {
                STORAGE = ContactJSonFileStorage(context)
            }
            return STORAGE as ContactJSonFileStorage
        }
    }

    override fun create(id: Int, obj: Contact): Contact {
        return Contact(id, obj.firstName, obj.lastName, obj.phone, obj.home, obj.mail, obj.location)
    }

    override fun objectToJson(id: Int, obj: Contact): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(Contact.CONTACT_ID, id)
        jsonObject.put(Contact.CONTACT_FIRST_NAME, obj.firstName)
        jsonObject.put(Contact.CONTACT_FIRST_NAME, obj.firstName)
        TODO('Next properties')
        return jsonObject
    }

    override fun jsonToObject(json: JSONObject): Contact {
        return Contact(
            json.getInt(Contact.CONTACT_ID),
            json.getString(Contact.CONTACT_FIRST_NAME),
            json.getString(Contact.CONTACT_LAST_NAME),
            json.getString(Contact.CONTACT_PHONE),
            json.getString(Contact.CONTACT_HOME),
            json.getString(Contact.CONTACT_MAIL),
            json.getString(Contact.CONTACT_LOCATION),
        )
    }

}