package utils

import kotlin.random.Random

fun <T> ArrayList<T>.takeRandom(n: Int): ArrayList<T> {
    val buffer = arrayListOf<T>()
    if (n > this.size) {
        for (i in 0 until this.size) {
            val random = Random.nextInt(this.size)
            buffer.add(this[random])
            this.removeAt(random)
        }
    } else {
        for (i in 0 until n) {
            val random = Random.nextInt(this.size)
            buffer.add(this[random])
            this.removeAt(random)
        }
    }
    return buffer
}
