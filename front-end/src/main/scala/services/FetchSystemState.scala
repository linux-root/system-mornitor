package services

import com.raquo.airstream.core.EventStream
import io.laminext.fetch.Fetch
import models._
import zio.json.DecoderOps
import zio.json.JsonDecoder.fromCodec
import scala.concurrent.ExecutionContext

object FetchSystemState {

  def getRam(implicit ec: ExecutionContext): EventStream[Memory] = {
    EventStream.periodic(1000).flatMap{_ =>
      Fetch.get("http://localhost:8080/ram").text.map{ response =>
        println(response.data)
        response.data.fromJson[Memory] match {
          case Right(v) => v
        }
      }
    }
  }

  def getCPU(implicit ec: ExecutionContext): EventStream[CPU] = {
    EventStream.periodic(1000).flatMap{_ =>
      Fetch.get("http://localhost:8080/cpu").text.map{ response =>
        println(response.data)
        response.data.fromJson[CPU] match {
          case Right(v) => v
        }
      }
    }
  }
}