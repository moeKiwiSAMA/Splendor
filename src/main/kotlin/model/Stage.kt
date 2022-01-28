package model

import model.DevCard
import model.Noble
import repository.CardPack
import utils.takeRandom
import kotlin.random.Random

class Stage(owner: Player) {
    private var lowLevelPool: ArrayList<DevCard> = CardPack.lowLevel
    private var midLevelPool: ArrayList<DevCard> = CardPack.midLevel
    private var highLevelPool: ArrayList<DevCard> = CardPack.highLevel
    private var noblePool: ArrayList<Noble> = CardPack.Noble

    private var lowLevelCurrent: ArrayList<DevCard> = lowLevelPool.takeRandom(4)
    private var midLevelCurrent: ArrayList<DevCard> = midLevelPool.takeRandom(4)
    private var highLevelCurrent: ArrayList<DevCard> = highLevelPool.takeRandom(4)
    private var nobleCurrent: ArrayList<Noble> = noblePool.takeRandom(4)


    var tokenBlack = 0
    var tokenBlue = 0
    var tokenGreen = 0
    var tokenRed = 0
    var tokenWhite = 0
    var tokenGold = 0

    fun currentLowLevelInfo() = lowLevelCurrent.toString()
    fun currentMidLevelInfo() = midLevelCurrent.toString()
    fun currentHighLevelInfo() = highLevelCurrent.toString()
    fun currentNobleInfo() = nobleCurrent.toString()


    init {

    }

}
