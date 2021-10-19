package td.info507.mycontact.storage.utility

import android.content.Context
import android.content.SharedPreferences
import td.info507.mycontact.model.Contact

object ContactStorage {

    private const val PREF_NAME = "sh.sinux.mykontact.preferences"
    private const val PREF_STORAGE = "storage"
    const val PREF_STORAGE_NONE = 0
    const val PREF_STORAGE_FILE_JSON = 1
    const val PREF_STORAGE_FILE_CSV = 2
    const val PREF_STORAGE_DATA_BASE = 3
    private const val PREF_STORAGE_DEFAULT = PREF_STORAGE_NONE

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getPreferencesStorage(context: Context): Int {
        return getPreferences(context).getInt(PREF_STORAGE, PREF_STORAGE_DEFAULT)
    }

    fun setPreferencesStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(PREF_STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<Contact> {
        lateinit var storage: Storage<Contact>
        when (getPreferencesStorage(context)) {
            PREF_STORAGE_NONE -> storage = ContactNoneStorage()
            PREF_STORAGE_FILE_JSON -> storage = ContactJSonFileStorage.get(context)
            //PREF_STORAGE_FILE_CSV -> storage = ContactCSVFileStorage.get(context)
            //PREF_STORAGE_DATA_BASE -> storage = ContactDataBaseStorage.get(context)
        }
        return storage
    }

}
