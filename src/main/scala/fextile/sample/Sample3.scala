package fextile.sample

import fextile._
import scalafx.application.JFXApp
import scalafx.scene.control.Label

object Sample3 extends JFXApp {
  lazy val pushPane = new PushNodePane(screen1)
  
  lazy val screen1: VContainer = new VContainer {
    content = Seq(
      new H1 {
        text = "PushNodePane Sample"
      },
      new Label {
        text =
          """
            |PushNodePane is like UINavigationController in iOS.
            |PushNodePane has pushNode/popNode to move left/right navigation.
          """.stripMargin
        wrapText = true
      },
      new ButtonPrimary {
        text = "Next"
        onAction = event {
          e =>
            pushPane.pushNode(screen2)
        }
      }
    )
  }

  lazy val screen2: VContainer = new VContainer {
    content = Seq(
      new H1 {
        text = "Screen 2"
      },
      new ButtonPrimary {
        text = "Next"
        onAction = event {
          e =>
            pushPane.pushNode(screen3)
        }
      },
      new Button {
        text = "Back"
        onAction = event {
          e =>
            pushPane.popNode()
        }
      }
    )
  }

  lazy val screen3: VContainer = new VContainer {
    content = Seq(
      new H1 {
        text = "Screen 3"
      },
      new Button {
        text = "Back"
        onAction = event {
          e =>
            pushPane.popNode()
        }
      }
    )
  }

  stage = new JFXApp.PrimaryStage {
    title = "Fextile"
    width = 800
    height = 600

    // use fextile.Scene instead of scalafx.scene.Scene
    scene = new Scene {
      root = pushPane
    }
  }
}
