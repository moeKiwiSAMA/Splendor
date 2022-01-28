package repository

import com.google.gson.Gson
import logger.Logger
import model.DevCard
import model.DevCardList
import model.Noble
import kotlin.system.exitProcess

interface CardPack {
    companion object {
        val lowLevel: ArrayList<DevCard>
            get() = ArrayList(CardPackReader.devCardPack.filter { it.devLevel == "low" })
        val midLevel: ArrayList<DevCard>
            get() = ArrayList(CardPackReader.devCardPack.filter { it.devLevel == "mid" })
        val highLevel: ArrayList<DevCard>
            get() = ArrayList(CardPackReader.devCardPack.filter { it.devLevel == "high" })
        val Noble: ArrayList<Noble>
            get() = arrayListOf()
    }
}

object CardPackReader {
    private val devCardPackString = this::class.java.classLoader.getResource("cards-info.json").readText()
    val devCardPack: List<DevCard> = try {
        Gson().fromJson(devCardPackString, DevCardList::class.java).devCard
                as kotlin.collections.ArrayList<model.DevCard>
    } catch (e: Exception) {
        Logger.info("Cannot read devCardsFile.")
        exitProcess(1)
    }
}
