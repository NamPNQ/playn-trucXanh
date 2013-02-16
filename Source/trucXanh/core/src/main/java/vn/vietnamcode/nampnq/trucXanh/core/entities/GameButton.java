package vn.vietnamcode.nampnq.trucXanh.core.entities;

import static playn.core.PlayN.assets;

import playn.core.GroupLayer;
import playn.core.Image;

public class GameButton extends Button {
  
  private String imgHideLink;
  private Image imgHide; 
  
  public GameButton(GroupLayer parentLayer, String imageName) {
    super(parentLayer, imageName);
    // TODO Auto-generated constructor stub
  }
  public void setImageHideLink(String link){
    this.imgHideLink = link;
  }
  public void init(){
    imgHide = assets().getImage(this.imgHideLink);
  }
  public String getLink(){
    return this.imgHideLink;
  }
  public Boolean Equals(GameButton other){
    return this.imgHideLink.equalsIgnoreCase(other.getLink());
  }
  public void showHideImage(){
    this.changeImage(assets().getImage("images/block_choose.png"));
    this.addOverlay(imgHide);
  }
}
