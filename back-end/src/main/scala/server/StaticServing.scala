package server

import zio.http.model.{Headers, MediaType, Status}
import zio.http.{Body, Response}
import zio.{UIO, ZIO}

import java.io.File

object StaticServing {
  def serveFile(filePath: String): UIO[Response] = {
    ZIO.attempt {
      val file = new File(filePath)
      val length = Headers.contentLength(file.length())
      val response = Response(headers = length, body = Body.fromFile(file))
      val pathName = file.toPath.toString
      pathName.lastIndexOf(".") match {
        case i =>
          val ext = pathName.substring(i + 1)
          MediaType.forFileExtension(ext) match {
            case Some(mediaType) =>
              response.withMediaType(mediaType)
          }
      }
    }.fold(_ => Response.status(Status.NotFound), a => a)
  }

}
