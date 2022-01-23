import model.DevCard
import model.Noble
import repository.CardPack
import javax.smartcardio.Card
import kotlin.random.Random

class Stage {
    private var lowLevelPool: ArrayList<DevCard> = CardPack.lowLevel
    private var midLevelPool: ArrayList<DevCard> = CardPack.midLevel
    private var highLevelPool: ArrayList<DevCard> = CardPack.highLevel
    private var noblePool: ArrayList<Noble> = CardPack.Noble

    var lowLevelCurrent: ArrayList<DevCard> = lowLevelPool.takeRandom(4) as ArrayList<DevCard>
    var midLevelCurrent: ArrayList<DevCard> = midLevelPool.takeRandom(4) as ArrayList<DevCard>
    var highLevelCurrent: ArrayList<DevCard> = highLevelPool.takeRandom(4) as ArrayList<DevCard>
    var nobleCurrent: ArrayList<Noble> = noblePool.takeRandom(4) as ArrayList<Noble>

    init {

    }

    override fun toString(): String {
        return "lowLevelCurrent: $lowLevelCurrent \nlowLevelPool $lowLevelPool"
    }
}

fun <T> MutableList<T>.takeRandom(n: Int): MutableList<T> {
    val buffer = mutableListOf<T>()
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