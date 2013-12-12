package fextile

trait DeviceSize {
  val widthRange: Range
}

trait DeviceSizeExtraSmall extends DeviceSize {
  val widthRange: Range = 0 until 768
}

trait DeviceSizeSmall extends DeviceSize {
  val widthRange: Range = 768 until 992
}

trait DeviceSizeMedium extends DeviceSize {
  val widthRange: Range = 992 until 1200
}

trait DeviceSizeLarge extends DeviceSize {
  val widthRange: Range = 1200 to Integer.MAX_VALUE
}