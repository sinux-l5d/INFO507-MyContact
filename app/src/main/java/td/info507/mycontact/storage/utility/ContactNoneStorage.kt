package td.info507.mycontact.storage.utility

import td.info507.mycontact.model.Contact
import td.info507.mycontact.storage.utility.file.FileStorage

class ContactNoneStorage : Storage<Contact> {

    override fun insert(obj: Contact) { }

    override fun size(): Int {
        return 0
    }

    override fun find(id: Int): Contact? {
        return null
    }

    override fun findAll(): List<Contact> {
        return emptyList()
    }

    override fun update(id: Int, obj: Contact) { }

    override fun delete(id: Int) { }

}