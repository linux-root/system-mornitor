package utils

object NumberUtil {
  def formatNumber(n: Number): String = {
    "%.2f".format(n)
  }

  def bytesToGb(bytes: Long): Double = {
    formatNumber(bytes / Math.pow(1024, 3)).toDouble
  }
}
