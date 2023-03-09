package server

import services.SystemStateReader
import zio.http.Server
import zio.{ZIO, ZIOAppDefault}

object HttpServer extends ZIOAppDefault {

  override val run: ZIO[Any, Throwable, Nothing] = {
    Server.serve(
      Routes.app
    ).provide(
      Server.default ++
      SystemStateReader.live
    )
  }
}