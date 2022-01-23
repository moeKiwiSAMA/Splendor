package model

data class DevCard(
    val level: Int,
    val bounce: Token,
    val requiredToken: List<Pair<Token, Int>>,
    val prestige: Int
)