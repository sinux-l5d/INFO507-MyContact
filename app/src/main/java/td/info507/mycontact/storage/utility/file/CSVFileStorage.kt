package td.info507.mycontact.storage.utility.file

import android.content.Context

abstract class CSVFileStorage<T>(context: Context, name: String) :
    FileStorage<T>(context, name, EXTENSION) {

    companion object {
        private const val EXTENSION: String = ".csv"
        private const val SEPARATOR: String = ","
    }

    protected abstract fun objectToCsv(id: Int, obj: T) : List<String>
    protected abstract fun csvToObject(csv: List<String>) : T

    override fun dataToString(data: HashMap<Int, T>): String {
        var csv = ""
        data.forEach { csv += objectToCsv(it.key, it.value).joinToString(separator = SEPARATOR) + "\n" }
        return csv
    }

    override fun stringToData(value: String): HashMap<Int, T> {
        val data = HashMap<Int, T>()
        val csv = value.split("\n")
        val iterator = csv.iterator()
        while (iterator.hasNext()) {
            val line = iterator.next()
            data[line.split(SEPARATOR)[0].toInt()] = csvToObject(line.split(SEPARATOR))
        }
        return data
    }
}