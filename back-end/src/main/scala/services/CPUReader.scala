package services

import models.CPU
import zio.UIO

trait CPUReader {
  def get: UIO[CPU]
}