package services

import models.Memory
import zio.UIO

trait RamReader {
  def get: UIO[Memory]
}