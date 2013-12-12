package fextile.sample

import fextile._
import scalafx.application.JFXApp
import scalafx.scene.control.Label

object Sample1 extends JFXApp {
  lazy val sample = new VContainer {
    content = Seq(
      new H1 {
        text = "Fextile Sample"
      },
      new Label {
        text = "Lorem ipsum dolor sit amet, ex sit tempor ceteros interesset. Per ei epicuri complectitur, has quodsi accumsan consetetur eu. Vis at meis quando, ne nec ullum instructior. Vel dolore alienum explicari et, hinc inermis corpora vel an, nominati inciderint eam ad. Cum ex dolor malorum vituperata, cu possit equidem accusata cum."
        wrapText = true
      },
      new Label {
        text = "Lorem ipsum dolor sit amet, ex sit tempor ceteros interesset. Per ei epicuri complectitur, has quodsi accumsan consetetur eu. Vis at meis quando, ne nec ullum instructior. Vel dolore alienum explicari et, hinc inermis corpora vel an, nominati inciderint eam ad. Cum ex dolor malorum vituperata, cu possit equidem accusata cum."
        wrapText = true
        styleClass = Seq("text-primary")
      }
    )
  }

  stage = new JFXApp.PrimaryStage {
    title = "Fextile"
    width = 800
    height = 600

    // use fextile.Scene instead of scalafx.scene.Scene
    scene = new Scene {
      root = sample
    }
  }
}
