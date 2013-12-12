package fextile

import scalafx.scene.layout.{Priority, HBox, VBox}

trait Container

class VContainer extends VBox with Container {
  hgrow = Priority.ALWAYS
  vgrow = Priority.ALWAYS
  spacing = 8
  styleClass = Seq("container")
}

class HContainer extends HBox with Container {
  hgrow = Priority.ALWAYS
  vgrow = Priority.ALWAYS
  spacing = 8
  styleClass = Seq("container")
}
