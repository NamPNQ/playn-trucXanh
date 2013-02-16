package vn.vietnamcode.nampnq.trucXanh.core.screens;

import static playn.core.PlayN.graphics;
import playn.core.Surface;
import playn.core.SurfaceLayer;

import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;
import vn.vietnamcode.nampnq.trucXanh.core.util.GameLib;

public class GameOverScreen implements GameScreen {

  private SurfaceLayer layer;
  private String scrollMessage;
  private int tick = 0;
  private int score = 0;

  @Override
  public void begin(Object data) {
    // TODO Auto-generated method stub
    if (data instanceof Integer) {
      this.score = (Integer) data;
      layer = graphics().createSurfaceLayer(graphics().width(), graphics().height());
      layer.setScale(2f);

      graphics().rootLayer().clear();
      graphics().rootLayer().add(layer);
      if (score == 0) {
        scrollMessage = "Game Over!!! Create by: NamPNQ";
      } else {
        scrollMessage = "Clear All Level!!! Create by: NamPNQ";
      }
    }


  }

  @Override
  public void end() {
    // TODO Auto-generated method stub

  }

  @Override
  public InputAdapter inputHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void paint(float alpha) {
    // TODO Auto-generated method stub
    Surface g = layer.surface();
    g.setFillColor(0xff87ceeb);
    g.fillRect(0, 0, 320, 240);
    int f = tick / 3 % 10;
    if (f >= 6) f = 10 - f;
    g.drawImage(GameLib.gameOver[f][0], 160 - 48, 100 - 32);
    drawString(g, scrollMessage, 160 - scrollMessage.length() * 4, 160, 0);
    String strScore = "Your score: " + score;
    drawString(g, strScore, 160 - strScore.length() * 4, 180, 0);
    //if(score!=0)
  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    tick++;
  }

  private void drawString(Surface g, String text, int x, int y, int c) {
    char[] ch = text.toCharArray();
    for (int i = 0; i < ch.length; i++) {
      g.drawImage(GameLib.font[ch[i] - 32][c], x + i * 8, y);
    }
  }

}
