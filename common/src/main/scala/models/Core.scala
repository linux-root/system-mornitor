package models

import zio.json.{DeriveJsonCodec, JsonCodec}


case class Core(number: Int, usagePercent: Double) {
  def colorCode: String = {
    if (usagePercent < 30) {
      "#4CAF50"
    } else if (usagePercent <= 50) {
      "#FB8C00"
    } else {
      "#E53935"
    }
  }
}

object Core {
  implicit val codec: JsonCodec[Core] = DeriveJsonCodec.gen[Core]
}