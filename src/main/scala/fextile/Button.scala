package fextile

import scalafx.scene.control.{Button => FxButton}

class Button extends FxButton {
  styleClass = Seq("button", "btn-default")
}

class ButtonPrimary extends Button {
  styleClass = Seq("button", "btn-primary")
}

class ButtonSuccess extends Button {
  styleClass = Seq("button", "btn-success")
}

class ButtonInfo extends Button {
  styleClass = Seq("button", "btn-info")
}

class ButtonWarning extends Button {
  styleClass = Seq("button", "btn-warning")
}

class ButtonDanger extends Button {
  styleClass = Seq("button", "btn-danger")
}
