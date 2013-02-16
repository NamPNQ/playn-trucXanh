package vn.vietnamcode.nampnq.trucXanh.core.util;

import static playn.core.PlayN.graphics;

import playn.core.CanvasLayer;
import playn.core.TextLayout;
import playn.core.Layer;

public class Util {

  public static Layer createTextLayer(TextLayout layout) {
    CanvasLayer layer = graphics().createCanvasLayer(
      (int)Math.ceil(layout.width()), (int)Math.ceil(layout.height()));
    layer.canvas().drawText(layout, 0, 0);
    return layer;
  }
}
