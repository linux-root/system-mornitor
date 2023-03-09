import models.Memory
import org.scalatest.wordspec.AnyWordSpec

class MemorySpec extends AnyWordSpec {

  "toString" in {
    val mem = Memory(17179869184L, 8789934592L)
    assert(mem.toString == "7.81 GB (48.84 %) of 16.0 GB")
  }

}
