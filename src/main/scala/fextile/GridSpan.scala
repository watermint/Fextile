package fextile

trait GridSpan extends DeviceSize {
  val gridSpan: Int
  val gridOffset: Option[Int]

  if (!(1 to 12).contains(gridSpan)) {
    throw new IllegalArgumentException(s"span should be in range 1 to 12. given span is ${gridSpan}")
  }
  if (!(1 to 12).contains(gridOffset.getOrElse(1))) {
    throw new IllegalArgumentException(s"offset should be in range 1 to 12: given offset is ${gridOffset}")
  }

  def offset(offset: Int): GridSpan
}

case class GridSpanExtraSmall(gridSpan: Int = 1, gridOffset: Option[Int] = None) extends GridSpan with DeviceSizeExtraSmall {
  def offset(offset: Int): GridSpan = GridSpanExtraSmall(gridSpan, Some(offset))
}

case class GridSpanSmall(gridSpan: Int = 1, gridOffset: Option[Int] = None) extends GridSpan with DeviceSizeSmall {
  def offset(offset: Int): GridSpan = GridSpanSmall(gridSpan, Some(offset))
}

case class GridSpanMedium(gridSpan: Int = 1, gridOffset: Option[Int] = None) extends GridSpan with DeviceSizeMedium {
  def offset(offset: Int): GridSpan = GridSpanMedium(gridSpan, Some(offset))
}

case class GridSpanLarge(gridSpan: Int = 1, gridOffset: Option[Int] = None) extends GridSpan with DeviceSizeLarge {
  def offset(offset: Int): GridSpan = GridSpanLarge(gridSpan, Some(offset))
}
