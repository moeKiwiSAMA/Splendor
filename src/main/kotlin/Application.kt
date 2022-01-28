import controller.RoomController.createRoom
import controller.RoomController.joinRoom
import controller.RoomController.leaveRoom
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class Application {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val router = Router.router(vertx)

            /**
             * @api {get} /createRoom
             * @apiName CreateRoom
             * @apiGroup Room
             *
             * @apiParam {String} userIdentifier    User identifier.
             *
             * @apiSuccess {String} response    Response description.
             * @apiSuccess {String} roomUUID    Room UUID.
             *
             * @apiSuccessExample Success-Response:
             *    HTTP/1.1 200 OK
             *    {
             *    "response": "Room created",
             *    "roomUUID": "eb761958-4943-4c07-8fa4-8c31ce0aac9b"
             *    }
             *
             * @apiError Already in room.
             *
             * @apiErrorExample Error-Response:
             *   HTTP/1.1 400 Bad Request
             *   {
             *   "response": "Already in room",
             *   "roomUUID": "eb761958-4943-4c07-8fa4-8c31ce0aac9b"
             *   }
             *
             */
            router.get("/createRoom").handler(::createRoom)

            /**
             * @api {get} /joinRoom
             * @apiName JoinRoom
             * @apiGroup Room
             *
             *
             * @apiParam {String} userIdentifier    User identifier.
             * @apiParam {String} roomUUID  Room UUID.
             *
             * @apiSuccess {String} response    Response description.
             * @apiSuccess {String} roomUUID    Room UUID.
             *
             * @apiSuccessExample Success-Response:
             *   HTTP/1.1 200 OK
             *   {
             *   "response": "Room joined",
             *   "roomUUID": "eb761958-4943-4c07-8fa4-8c31ce0aac9b"
             *   }
             *
             * @ApiError Room not found.
             *
             * @apiErrorExample Error-Response:
             *  HTTP/1.1 400 Bad Request
             *  {
             *   "response": "Room not found",
             *   "roomUUID": "eb761958-4943-4c07-8fa4-8c31ce0aac9b"
             *  }
             *
             * @apiError Already in room.
             *
             * @apiErrorExample Error-Response:
             *  HTTP/1.1 400 Bad Request
             *  {
             *  "response": "Already in room",
             *  "roomUUID": "eb761958-4943-4c07-8fa4-8c31ce0aac9b"
             *  }
             */
            router.get("/joinRoom").handler(::joinRoom)

            /**
             * @api {get} /leaveRoom
             * @apiName LeaveRoom
             * @apiGroup Room
             *
             * @apiParam {String} userIdentifier    User identifier.
             * @apiParam {String} roomUUID  Room UUID.
             *
             * @apiSuccess {String} response    Response description.
             * @apiSuccess {String} roomUUID    Room UUID.
             *
             * @apiSuccessExample Success-Response:
             *  HTTP/1.1 200 OK
             *  {
             *  "response": "Room left"
             *  }
             *
             *  @apiError Room not found.
             *
             *  @apiErrorExample Error-Response:
             *  HTTP/1.1 400 Bad Request
             *  {
             *  "response": "Room not found"
             *  }
             *
             *  @apiError Not in room.
             *
             *  @apiErrorExample Error-Response:
             *  HTTP/1.1 400 Bad Request
             *  {
             *  "response": "Not in room"
             *  }
             *
             */
            router.get("/leaveRoom").handler(::leaveRoom)

            vertx.createHttpServer().requestHandler(router).listen(28080)
        }


    }
}