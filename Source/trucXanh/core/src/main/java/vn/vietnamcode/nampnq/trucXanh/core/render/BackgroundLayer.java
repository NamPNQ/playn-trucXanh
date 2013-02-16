package vn.vietnamcode.nampnq.trucXanh.core.render;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public class BackgroundLayer {

  private Params params = new Params();
  
  private String currentImage = null;
  private ImageLayer playnLayer = null;
  
  public synchronized ImageLayer asLayer() {
    if (playnLayer == null) {
      playnLayer = graphics().createImageLayer();
      playnLayer.setSize(params.width(), params.height());
      if (currentImage != null) {
        setImage(currentImage);
      }
    }
    return playnLayer;
  }
  
  public void setImage(String name) {
    if (playnLayer != null) {
      assets().getImage("images/" + name).addCallback(new ResourceCallback<Image>() {
        @Override
        public void done(Image im) {
          playnLayer.setVisible(false);
          //playnLayer.setScale(params.width()/ im.width(), params.height()/ im.height());
          playnLayer.setImage(im);
          playnLayer.setVisible(true);
        }
        @Override
        public void error(Throwable err) { }
      });
    }
    currentImage = name;
  }
}
