package td.info507.mycontact

class Test {
}

open class Animal(protected val famille: String, private val age : Int){
    fun affiche(){
        println(this)
    }
    override fun toString() : String{
        return "$famille - $age ans"
    }
}

class Chien(age : Int): Animal("chien", age){
    fun moi(){
        println("Je suis un $famille")
    }
}

fun main(){
    val animal : Animal = Animal("chat", 8)
    val medor = Chien(5)
    animal.affiche()
    medor.moi()
    medor.affiche()

}