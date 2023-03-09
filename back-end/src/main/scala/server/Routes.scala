package server

import services.SystemStateReader
import zio.http._
import zio.http.model._
import zio.json.EncoderOps


object Routes {
  val app: App[SystemStateReader] = {
    Http.collectZIO[Request] {
      case Method.GET -> !! / "ram" =>
        SystemStateReader.getRam.map(ram => Response.json(ram.toJson))
      case Method.GET -> !! / "cpu" =>
        SystemStateReader.getCPU.map(cpu => Response.json(cpu.toJson))

      case Method.GET -> !! =>
        StaticServing.serveFile("front-end/src/main/resources/index.html")

      case Method.GET -> !! / frontEndStaticFiles =>
        val filePath = s"front-end/target/scala-2.13/front-end-fastopt/$frontEndStaticFiles"
        StaticServing.serveFile(filePath)

    } @@ Middleware.withAccessControlAllowOrigin("*")
  }
}