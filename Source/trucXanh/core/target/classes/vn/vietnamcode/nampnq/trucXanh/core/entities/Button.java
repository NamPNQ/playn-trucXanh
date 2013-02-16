package vn.vietnamcode.nampnq.trucXanh.core.entities;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Layer;
import playn.core.ResourceCallback;
import playn.core.TextFormat;
import playn.core.Font;
import vn.vietnamcode.nampnq.trucXanh.core.util.Util;


public class Button {

  private Image imgNormal;
  private int id;
  private float width, height;
  private GroupLayer groupLayer;
  private ImageLayer layer,overlayLayer;


  public Button(final GroupLayer parentLayer, String imageName) {
    imgNormal = assets().getImage("images/" + imageName);
    groupLayer = graphics().createGroupLayer();
    layer = graphics().createImageLayer(imgNormal);
    final Button b = this;
    imgNormal.addCallback(new ResourceCallback<Image>() {

      @Override
      public void done(Image resource) {
        // TODO Auto-generated method stub
        b.width = resource.width();
        b.height = resource.height();
        groupLayer.add(layer);
        parentLayer.add(groupLayer);
      }

      @Override
      public void error(Throwable err) {
        // TODO Auto-generated method stub
        log().error("Ko the load hinh anh!");
      }

    });



  }

  public int getId(){
    return this.id;
  }
  public void setId(int id){
    this.id = id;
  }
  public float getHeight(){
    return this.height;
  }
  public float getWidth(){
    return this.width;
  }
  public void setTranslation(final float x, final float y){
    groupLayer.setTranslation(x, y);
  }
  public void addListener(playn.core.Pointer.Listener listener)
  {
      layer.addListener(listener);
  }
  public void addText(String string){
    Layer textLayer = Util.createTextLayer(graphics().layoutText(
      string, new TextFormat().withFont(graphics().createFont("Tahoma", Font.Style.BOLD, 30)).
                             withEffect(TextFormat.Effect.outline(0xFFFFFFFF)).
                             withTextColor(0xFF000066)));
    groupLayer.add(textLayer);
    textLayer.setTranslation((this.width-10f)/2, (this.height-10f)/2);
  }
  public void addOverlay(Image image){
    if(overlayLayer !=null)
    {
      overlayLayer.setImage(image);
      overlayLayer.setVisible(true);
    }
    else
    {
    overlayLayer = graphics().createImageLayer(image); 
    groupLayer.add(overlayLayer);
    overlayLayer.setTranslation((width-image.width()*(width*.7f/image.width()))/2, 5 + (height-image.height()*(height*.7f/image.height()))/2);
    overlayLayer.setScale((width*.7f/image.width()), (height*.7f/image.height()));
    }
  }
  public void hideOverlay(){
    overlayLayer.setVisible(false);
  }
  public void changeImage(Image image){
    layer.setImage(image);
  }
  public void resetImage(){
    layer.setImage(imgNormal);
    if(overlayLayer!=null){
      overlayLayer.setVisible(false);
    }
  }
  public void setVisible(Boolean flag){
    groupLayer.setVisible(flag);
  }
}
