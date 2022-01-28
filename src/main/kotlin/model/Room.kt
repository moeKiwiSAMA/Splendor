package model

data class Room(val owner: Player, val member: ArrayList<Player> = arrayListOf<Player>()) {
    fun addPlayer(player: Player) {
        member.add(player)
    }
    fun removePlayer(player: Player) {
        member.remove(player)
        // TODO: change owner
    }
}