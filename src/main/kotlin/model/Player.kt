package model

class Player(val identifier: String) {
    var devBlack = 0
    var devBlue = 0
    var devGreen = 0
    var devRed = 0
    var devWhite = 0

    var tokenBlack = 0
    var tokenBlue = 0
    var tokenGreen = 0
    var tokenRed = 0
    var tokenWhite = 0
    var tokenGold = 0

    var prestigePoint = 0

    var pledgeCard = listOf<DevCard>()
}
