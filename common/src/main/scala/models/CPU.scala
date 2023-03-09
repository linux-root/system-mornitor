package models

import zio.json.{DeriveJsonCodec, JsonCodec}

case class CPU(name: String, cores: List[Core])

object CPU {
  implicit val codec: JsonCodec[CPU] = DeriveJsonCodec.gen[CPU]
}
