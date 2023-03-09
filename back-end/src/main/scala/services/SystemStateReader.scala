package services

import models.{CPU, Core, Memory}
import oshi.SystemInfo
import oshi.hardware.HardwareAbstractionLayer
import zio.{UIO, ULayer, URIO, ZIO, ZLayer}

import scala.jdk.CollectionConverters.CollectionHasAsScala

trait SystemStateReader {
  def getRam: UIO[Memory]

  def getCPU: UIO[CPU]
}

object SystemStateReader {
  def getRam: URIO[SystemStateReader, Memory] = ZIO.service[SystemStateReader].flatMap(_.getRam)
  def getCPU: URIO[SystemStateReader, CPU] = ZIO.service[SystemStateReader].flatMap(_.getCPU)

  val live: ULayer[SystemStateReader] = ZLayer.succeed(
    new SystemStateReader {
      val si : SystemInfo = new SystemInfo()
      val hal: HardwareAbstractionLayer = si.getHardware

      override def getRam: UIO[Memory] ={
        ZIO.succeed(Memory(hal.getMemory.getTotal, hal.getMemory.getAvailable))
      }

      override def getCPU: UIO[CPU] = ZIO.succeed {
        val processor = hal.getProcessor
        val cores = (processor.getProcessorCpuLoad(300) zip processor.getPhysicalProcessors.asScala).map{ case(load, pProcessor) =>
          Core(pProcessor.getPhysicalProcessorNumber, load*100)
        }.toList
        CPU("Apple M2", cores)
      }
    }
  )
}