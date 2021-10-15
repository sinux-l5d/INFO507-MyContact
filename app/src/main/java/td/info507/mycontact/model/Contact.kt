package td.info507.mycontact.model

import java.io.Serializable

class Contact (nom : String, prenom : String, val phone : String, val home : String, val mail : String, val location : String)
    : Serializable{
    companion object{
        var ID = 1
    }

    val id : Int = ID++
    val name : String = "$prenom $nom"

}