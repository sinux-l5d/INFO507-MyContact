package td.info507.mycontact.model

import java.io.Serializable

class Contact (
        val id: Int,
        val firstName : String,
        val lastName: String,
        val phone : String,
        val home : String,
        val mail : String,
        val location : String
    )
    : Serializable {

    companion object{
        const val CONTACT_ID = "id"
        const val CONTACT_FIRST_NAME = "first_name"
        const val CONTACT_LAST_NAME = "last_name"
        const val CONTACT_PHONE = "phone"
        const val CONTACT_HOME = "home"
        const val CONTACT_MAIL = "mail"
        const val CONTACT_LOCATION = "location"
    }

    val name : String = "$firstName $lastName"

}