package model

data class DevCardList(
    val devCard: List<DevCard>
)

data class DevCard(
    val black: Int,

    val blue: Int,
    val devLevel: String,
    val devToken: String,
    val green: Int,
    val prestige: Int,
    val red: Int,
    val white: Int
)