package fextile

import scalafx.scene.control.Label

trait Heading

class H1 extends Label with Heading {
  styleClass = Seq("h1")
}

class H2 extends Label with Heading {
  styleClass = Seq("h2")
}

class H3 extends Label with Heading {
  styleClass = Seq("h3")
}

class H4 extends Label with Heading {
  styleClass = Seq("h4")
}

class H5 extends Label with Heading {
  styleClass = Seq("h5")
}

class H6 extends Label with Heading {
  styleClass = Seq("h6")
}

class H1PageHeader extends Label with Heading {
  styleClass = Seq("page-header", "h1")
}
