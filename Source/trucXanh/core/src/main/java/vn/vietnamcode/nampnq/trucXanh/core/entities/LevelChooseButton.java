package vn.vietnamcode.nampnq.trucXanh.core.entities;

import static playn.core.PlayN.assets;
import playn.core.GroupLayer;
import playn.core.Image;

public class LevelChooseButton extends Button {

  private Image overlayLockIcon;


  public LevelChooseButton(GroupLayer parentLayer, String imageName, int id) {
    super(parentLayer, imageName);
    this.setId(id);
    overlayLockIcon = assets().getImage("images/lock.png");
    this.addOverlay(overlayLockIcon);
    this.setVisibleOverlay(true);
  }
  public void setVisibleOverlay(boolean flag){
    if(flag){
      this.addOverlay(overlayLockIcon);
    }
    else{
      this.hideOverlay();
    }
  }

}
