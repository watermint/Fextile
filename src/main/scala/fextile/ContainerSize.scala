package fextile

trait ContainerSize extends DeviceSize {
  val containerWidth: Option[Int]
  val columnWidth: Option[Int]
}

case class ContainerSizeExtraSmall() extends ContainerSize with DeviceSizeExtraSmall {
  val containerWidth: Option[Int] = None
  val columnWidth: Option[Int] = None
}

case class ContainerSizeSmall() extends ContainerSize with DeviceSizeSmall {
  val containerWidth: Option[Int] = Some(750)
  val columnWidth: Option[Int] = Some(60)
}

case class ContainerSizeMedium() extends ContainerSize with DeviceSizeMedium {
  val containerWidth: Option[Int] = Some(970)
  val columnWidth: Option[Int] = Some(78)
}

case class ContainerSizeLarge() extends ContainerSize with DeviceSizeLarge {
  val containerWidth: Option[Int] = Some(1170)
  val columnWidth: Option[Int] = Some(95)
}

object ContainerSize {
  lazy val sizes: Seq[ContainerSize] = Seq(
    ContainerSizeExtraSmall(),
    ContainerSizeSmall(),
    ContainerSizeMedium(),
    ContainerSizeLarge()
  )
}