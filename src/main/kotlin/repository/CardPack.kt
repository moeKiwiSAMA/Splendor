package repository

import model.*

interface CardPack {
    companion object {
        val lowLevel: ArrayList<DevCard>
            get() =
                arrayListOf(
                    DevCard(
                        1,
                        Black(),
                        listOf(Pair(Blue(), 1)),
                        0
                    ),
                    DevCard(
                        1,
                        Blue(),
                        listOf(Pair(Blue(), 2)),
                        0
                    ),
                    DevCard(
                        1,
                        Green(),
                        listOf(Pair(Blue(), 3)),
                        0
                    ),
                    DevCard(
                        1,
                        White(),
                        listOf(Pair(Blue(), 1), Pair(Black(), 1)),
                        0
                    ),
                    DevCard(
                        1,
                        White(),
                        listOf(Pair(Blue(), 1), Pair(Black(), 1)),
                        0
                    )
                )
        val midLevel: ArrayList<DevCard>
            get() = arrayListOf()
        val highLevel: ArrayList<DevCard>
            get() = arrayListOf()
        val Noble: ArrayList<Noble>
            get() = arrayListOf()
    }
}