package controller

import com.google.gson.Gson
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import model.Player
import model.Room
import repository.JedisClientPool
import java.util.*

object RoomController {
    fun createRoom(routingContext: RoutingContext) {
        val userIdentifier = routingContext.request().getParam("userIdentifier")
        val player = Player(userIdentifier)

        if (player.inRoom) {
            val responseObject = JsonObject()
            responseObject.put("response", "Already in room")
            responseObject.put("roomUUID", player.roomUUID)
            routingContext.response().setStatusCode(400).end(responseObject.toString())
            return
        } else {
            val room = Room(player)
            val roomUUID = UUID.randomUUID().toString()
            val roomInfo = Gson().toJson(room)

            JedisClientPool.jedisPool.resource.use {
                it.set("splendor_player_room_${player.identifier}", roomUUID)
                it.set("splendor_room_$roomUUID", roomInfo)
            }
            val responseObject = JsonObject()
            responseObject.put("response", "Room created")
            responseObject.put("roomUUID", roomUUID)
            routingContext.response().setStatusCode(200).end(responseObject.toString())
        }
    }

    fun joinRoom(routingContext: RoutingContext) {
        val userIdentifier = routingContext.request().getParam("userIdentifier")
        val roomUUID = routingContext.request().getParam("roomUUID")
        val player = Player(userIdentifier)

        if (player.inRoom) {
            val responseObject = JsonObject()
            responseObject.put("response", "Already in room")
            responseObject.put("roomUUID", player.roomUUID)
            routingContext.response().setStatusCode(400).end(responseObject.toString())
            return
        } else {
            val roomInfo = JedisClientPool.jedisPool.resource.use {
                it.get("splendor_room_$roomUUID")
            }

            if (roomInfo == null) {
                val responseObject = JsonObject()
                responseObject.put("response", "Room not found")
                responseObject.put("roomUUID", roomUUID)
                routingContext.response().setStatusCode(400).end(responseObject.toString())
                return
            }

            val room = Gson().fromJson(roomInfo, Room::class.java)
            room.addPlayer(player)

            val roomInfoUpdated = Gson().toJson(room)

            JedisClientPool.jedisPool.resource.use {
                it.set("splendor_player_room_${player.identifier}", roomUUID)
                it.set("splendor_room_$roomUUID", roomInfoUpdated)
            }
            val responseObject = JsonObject()
            responseObject.put("response", "Room joined")
            responseObject.put("roomUUID", roomUUID)
            routingContext.response().setStatusCode(200).end(responseObject.toString())
        }
    }

    fun leaveRoom(routingContext: RoutingContext) {
        val userIdentifier = routingContext.request().getParam("userIdentifier")
        val player = Player(userIdentifier)
        if (!player.inRoom) {
            val responseObject = JsonObject()
            responseObject.put("response", "Not in room")
            routingContext.response().setStatusCode(400).end(responseObject.toString())
            return
        } else {
            val roomInfo = JedisClientPool.jedisPool.resource.use {
                it.get("splendor_room_${player.roomUUID}")
            }

            if (roomInfo == null) {
                val responseObject = JsonObject()
                responseObject.put("response", "Room not found")
                routingContext.response().setStatusCode(400).end(responseObject.toString())
                return
            }

            val roomUUID = player.roomUUID
            val room = Gson().fromJson(roomInfo, Room::class.java)
            room.removePlayer(player)

            val roomInfoUpdated = Gson().toJson(room)

            JedisClientPool.jedisPool.resource.use {
                it.del("splendor_player_room_${player.identifier}")
                it.set("splendor_room_$roomUUID", roomInfoUpdated)
            }
            val responseObject = JsonObject()
            responseObject.put("response", "Room left")
            routingContext.response().setStatusCode(200).end(responseObject.toString())
        }
    }

    private val Player.inRoom: Boolean
        get() {
            val roomID = JedisClientPool.jedisPool.resource.use {
                it.get("splendor_player_room_${this.identifier}")
            }

            return roomID != null
        }

    private val Player.roomUUID: String
        get() =
            JedisClientPool.jedisPool.resource.use {
                it.get("splendor_player_room_${this.identifier}")
            }
}