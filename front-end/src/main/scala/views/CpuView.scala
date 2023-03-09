package views

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import models.{CPU, Core}
import org.scalajs.dom.HTMLDivElement
import utils.NumberUtil

object CpuView {

  def apply(cpu: CPU): ReactiveHtmlElement[HTMLDivElement] = {
    div(
      div(h4(cpu.name, fontWeight("bold"))),
      div(
        display("flex"),
        cpu.cores.map(core => coreView(core))
      )
    )
  }


  private def coreView(core: Core) = {
    div(
      marginLeft("10px"),
      div(
        div(margin("auto"), s"CPU${core.number}"),
        borderRadius("5px"),
        borderColor("#C6FF00"),
        width("60px"),
        height(s"${core.usagePercent * 5}px"),
        margin("2px"),
        color("white"),
        background(core.colorCode),
      ),
      div(
        marginTop("25px"),
        fontFamily("sans-serif"),
        fontSize("0.95em"),
        s"${NumberUtil.formatNumber(core.usagePercent)} %"
      )
    )
  }

}
