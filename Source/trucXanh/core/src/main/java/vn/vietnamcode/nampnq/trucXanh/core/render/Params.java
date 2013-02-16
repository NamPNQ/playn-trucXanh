package vn.vietnamcode.nampnq.trucXanh.core.render;

import static playn.core.PlayN.graphics;

public class Params {
  
  public int width() {
    // TODO - cache if expensive
    return graphics().width();
  }
  public int height() {
    return graphics().height();
  }
  public float ratio() {
    return (1f * height()) / width();
  }
}
