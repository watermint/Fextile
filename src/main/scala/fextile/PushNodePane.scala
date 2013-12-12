package fextile

import scala.collection.mutable
import scalafx.scene.Node
import scalafx.scene.layout._
import scalafx.beans.property.DoubleProperty
import scalafx.animation.TranslateTransition

/**
 * UINavigationController like push/pop view.
 * @param firstNode first node.
 */
class PushNodePane(firstNode: Region) extends Pane {
  private val nodes: mutable.Stack[Region] = mutable.Stack[Region](firstNode)
  private val tape: GridPane = new GridPane {
    add(firstNode, 0, 0)
    columnConstraints = List(baseWidthConstraints())
    rowConstraints = List(baseHeightConstraints())
  }

  lazy val paneWidth: DoubleProperty = new DoubleProperty()
  lazy val paneHeight: DoubleProperty = new DoubleProperty()

  content = tape

  hgrow = Priority.ALWAYS
  vgrow = Priority.ALWAYS

  paneHeight.bind(this.height)
  paneWidth.bind(this.width)
  paneWidth.onChange {
    tape.translateX = -paneWidth.value * (nodes.size - 1)
    nodes.foreach {
      case r: Pane =>
        r.minWidth = paneWidth.value
        r.maxWidth = paneWidth.value
      case _ =>
    }
  }

  protected def baseWidthConstraints(): ColumnConstraints = {
    new ColumnConstraints {
      hgrow = Priority.ALWAYS
      minWidth.bind(paneWidth)
      prefWidth.bind(paneWidth)
      maxWidth.bind(paneWidth)
    }
  }

  protected def baseHeightConstraints(): RowConstraints = {
    new RowConstraints {
      vgrow = Priority.ALWAYS
      minHeight.bind(paneHeight)
      prefHeight.bind(paneHeight)
      maxHeight.bind(paneHeight)
    }
  }

  protected def pushTransition(index: Int): Unit = {
    new TranslateTransition {
      node = tape
      toX = -paneWidth.value * index
      fromX = -paneWidth.value * (index - 1)
    }.play()
  }

  protected def popTransition(index: Int, targetNode: Region): Unit = {
    new TranslateTransition {
      node = tape
      toX = -paneWidth.value * (index - 1)
      fromX = -paneWidth.value * index
      onFinished = event {
        e =>
          tape.children.removeAll(targetNode)
      }
    }.play()
  }

  protected def emptyNode: Node = {
    new Pane()
  }

  def pushNode(node: Region): Node = {
    tape.columnConstraints = (0 to nodes.size).map(i => baseWidthConstraints()).toList
    tape.add(node, nodes.size, 0)
    pushTransition(nodes.size)
    nodes.push(node)
    node.minWidth.bind(paneWidth)
    node.maxWidth.bind(paneWidth)
    node
  }

  def popNode(): Node = {
    val node = nodes.pop()
    tape.add(emptyNode, nodes.size, 0)
    popTransition(nodes.size, node)
    node
  }
}