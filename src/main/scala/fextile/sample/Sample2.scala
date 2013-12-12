package fextile.sample

import fextile._
import scalafx.scene.control.Label
import scalafx.application.JFXApp

object Sample2 extends JFXApp {
  lazy val sample2 = new GridRow {
    add(
      new H1 {
        text = "Responsive"
      },
      xs12, sm12, md12, lg12
    )
    add(
      new Label {
        text = "Lorem ipsum dolor sit amet, ex sit tempor ceteros interesset. Per ei epicuri complectitur, has quodsi accumsan consetetur eu. Vis at meis quando, ne nec ullum instructior. Vel dolore alienum explicari et, hinc inermis corpora vel an, nominati inciderint eam ad. Cum ex dolor malorum vituperata, cu possit equidem accusata cum."
        wrapText = true
      },
      xs12, sm6, md6, lg6
    )
    add(
      new Label {
        text = "Lorem ipsum dolor sit amet, ex sit tempor ceteros interesset. Per ei epicuri complectitur, has quodsi accumsan consetetur eu. Vis at meis quando, ne nec ullum instructior. Vel dolore alienum explicari et, hinc inermis corpora vel an, nominati inciderint eam ad. Cum ex dolor malorum vituperata, cu possit equidem accusata cum."
        wrapText = true
        styleClass = Seq("text-primary")
      },
      xs12, sm6, md6, lg6
    )
  }

  stage = new JFXApp.PrimaryStage {
    title = "Fextile"
    width = 800
    height = 600

    // use fextile.Scene instead of scalafx.scene.Scene
    scene = new Scene {
      root = sample2
    }
  }
}
