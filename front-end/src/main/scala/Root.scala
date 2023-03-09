import com.raquo.laminar.api.L._
import org.scalajs.dom
import services.FetchSystemState
import views.CpuView

import scala.concurrent.ExecutionContext.Implicits.global

object Root {

  def main(args: Array[String]): Unit = {
    lazy val container = dom.document.getElementById("app-container")
    val app = div(
      header,
      div(
        div(
          h3("CPU"),
          child <-- FetchSystemState.getCPU.map(CpuView(_))
        ),

        div(
          h3("Memory"),
          child <-- FetchSystemState.getRam.map(mem => h3(mem.toString))
        )
      )
    )

    renderOnDomContentLoaded(container, app) // Wait until the DOM is loaded, otherwise app-container element might not exist
  }

  private def header = div(
    h1("System Monitor", color("#00B0FF"))
  )
}