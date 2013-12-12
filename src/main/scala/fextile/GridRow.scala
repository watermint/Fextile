package fextile

import javafx.scene.layout.{GridPane => FxGridPane}
import scalafx.scene.layout._
import scalafx.scene.Node
import scalafx.geometry.{VPos, HPos, Insets, Pos}
import scala.collection.mutable
import scala.Some

class GridRow extends BorderPane {
  private val nodes: mutable.ListBuffer[Node] = mutable.ListBuffer[Node]()
  private val spans: mutable.HashMap[Node, Seq[GridSpan]] = mutable.HashMap[Node, Seq[GridSpan]]()
  private var currentCol: Int = 0
  private var currentRow: Int = 0

  lazy val container = new GridPane() {
    alignment = Pos.TOP_CENTER
    hgap = 15
    vgap = 15
  }

  center = container
  hgrow = Priority.ALWAYS
  vgrow = Priority.ALWAYS
  this.width.onChange {
    resizeContainer()
    rebalance()
  }

  protected def resizeContainer(): Unit = {
    ContainerSize.sizes.find(_.widthRange.contains(this.width.value.toInt)) match {
      case None => throw new IllegalStateException(s"Size definition not found for width: ${this.width.value}")
      case Some(sc) =>
        sc.containerWidth match {
          case Some(w: Int) =>
            container.minWidth = w
            container.maxWidth = w
          case _ =>
            container.minWidth = 1
            container.maxWidth = Double.MaxValue
        }
        sc.columnWidth match {
          case Some(w: Int) =>
            container.columnConstraints = (1 to 12).map(i => new ColumnConstraints {
              percentWidth = 100.0 / 12.0
              padding = Insets(0, 15, 0, 15)
              maxWidth = w
            })
          case _ =>
            container.columnConstraints = (1 to 12).map(i => new ColumnConstraints {
              percentWidth = 100.0 / 12.0
              padding = Insets(0, 15, 0, 15)
            })
        }
    }
  }

  protected def offsetAndSpanForNode(node: Node): Option[Pair[Option[Int], Int]] = {
    spans.get(node) match {
      case None => None
      case Some(s) =>
        s.find(_.widthRange.contains(this.width.value.toInt)) match {
          case Some(sc) => Some(sc.gridOffset -> sc.gridSpan)
          case _ => None
        }
    }
  }

  protected def placeNode(node: Node, col: Int, row: Int, span: Int): Unit = {
    if (container.children.contains(node)) {
      FxGridPane.setColumnIndex(node, col)
      FxGridPane.setRowIndex(node, row)
    } else {
      container.add(node, col, row)
    }
    FxGridPane.setColumnSpan(node, span)
  }

  protected def offsetAppend(node: Node, offset: Int, span: Int = 1): Unit = {
    if (currentCol + offset + span > 12) {
      currentRow += 1
      currentCol = offset
    } else if (offset > currentCol) {
      currentCol = offset
    }
    placeNode(node, currentCol, currentRow, span)
    currentCol += span
  }

  protected def simpleAppend(node: Node, span: Int = 1): Unit = {
    if (currentCol + span > 12) {
      currentRow += 1
      currentCol = 0
      placeNode(node, currentCol, currentRow, span)
    } else {
      placeNode(node, currentCol, currentRow, span)
    }
    currentCol += span
  }

  protected def rebalance(): Unit = {
    currentCol = 0
    currentRow = 0

    nodes.foreach {
      node =>
        offsetAndSpanForNode(node) match {
          case None => simpleAppend(node)
          case Some(os) =>
            val spanOfNode = os._2
            os._1 match {
              case None => simpleAppend(node, spanOfNode)
              case Some(offset) => offsetAppend(node, offset, spanOfNode)
            }
        }
    }
  }

  case class NodeContainer(node: Node) {
    def hpos(hpos: HPos): NodeContainer = {
      FxGridPane.setHalignment(node, hpos)
      this
    }
    def vpos(node: Node, vpos: VPos): NodeContainer = {
      FxGridPane.setValignment(node, vpos)
      this
    }
  }

  def add(node: Node, span: GridSpan*): NodeContainer = {
    nodes += node
    spans.put(node, span)

    offsetAndSpanForNode(node) match {
      case None => simpleAppend(node)
      case Some(os) =>
        val spanOfNode = os._2
        os._1 match {
          case None => simpleAppend(node, spanOfNode)
          case Some(offset) => offsetAppend(node, offset, spanOfNode)
        }
    }
    rebalance()
    NodeContainer(node)
  }
}

