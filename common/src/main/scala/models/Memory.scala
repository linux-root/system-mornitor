package models

import utils.NumberUtil.{bytesToGb, formatNumber}
import zio.json.{DeriveJsonCodec, JsonCodec}

case class Memory(total: Long, available: Long) {
  override def toString: String = {
    s"${bytesToGb(usage)} GB ($usagePercent %) of ${bytesToGb(total)} GB"
  }

  private lazy val usage: Long = total - available

  private lazy val usagePercent: String = formatNumber((usage.toDouble / total) * 100)

}

object Memory {
  implicit val codec: JsonCodec[Memory] = DeriveJsonCodec.gen[Memory]
}