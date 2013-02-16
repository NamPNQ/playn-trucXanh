package vn.vietnamcode.nampnq.trucXanh.core.util;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import playn.core.Canvas;
import playn.core.Canvas.Composite;
import playn.core.CanvasImage;
import playn.core.Image;
import playn.core.ResourceCallback;


public class GameLib {

  public static Image[][] font;
  public static Image[][] gameOver;

  public static void init() {
    cutImage("images/font.gif", 8, 8, 1);
    cutImage("images/gameovergost.gif", 96, 64, 2);
  }

  private static void cutImage(String imageName, final int xSize, final int ySize, final int which) {
    Image source = assets().getImage(imageName);
    source.addCallback(new ResourceCallback<Image>() {

      @Override
      public void done(Image source) {

        Image[][] images =
            new Image[(int) (source.width() / xSize)][(int) (source.height() / ySize)];
        for (int x = 0; x < source.width() / xSize; x++) {
          for (int y = 0; y < source.height() / ySize; y++) {
            CanvasImage image = graphics().createImage(xSize, ySize);// gc.createCompatibleImage(xSize,
                                                                     // ySize,
                                                                     // Transparency.BITMASK);
            Canvas g = image.canvas();
            g.setCompositeOperation(Composite.SRC);
            g.drawImage(source, -x * xSize, -y * ySize);
            images[x][y] = image;
          }
        }
        if (which == 1) font = images;
        if (which == 2) gameOver = images;

      }

      @Override
      public void error(Throwable err) {


      }

    });


  }

}
