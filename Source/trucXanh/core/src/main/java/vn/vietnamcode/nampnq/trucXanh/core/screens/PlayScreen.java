package vn.vietnamcode.nampnq.trucXanh.core.screens;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import java.util.Random;

import playn.core.GroupLayer;
import playn.core.Pointer;
import playn.core.Surface;
import playn.core.SurfaceLayer;
import playn.core.util.Callback;
import vn.vietnamcode.nampnq.trucXanh.core.entities.GameButton;
import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;
import vn.vietnamcode.nampnq.trucXanh.core.render.BackgroundLayer;
import vn.vietnamcode.nampnq.trucXanh.core.screens.LevelGame.LevelStatus;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ScreenManager.ScreenType;
import vn.vietnamcode.nampnq.trucXanh.core.util.GameLib;
import vn.vietnamcode.nampnq.trucXanh.core.util.Timer;

public class PlayScreen implements GameScreen {


  private ScreenManager manager;
  private GameConfig config;
  private BackgroundLayer backgroundLayer;
  private LevelGame lvGame;
  private GroupLayer playLayer;
  private GameButton btn1, btn2;
  private Timer timeCheck;
  private int score = 10;
  private int nMatch = 0;
  private int timeLeft = 0;
  private SurfaceLayer timeLabel;
  private SurfaceLayer scoreLabel;

  public PlayScreen(ScreenManager manager, BackgroundLayer backgroundLayer) {
    this.manager = manager;
    this.backgroundLayer = backgroundLayer;
    this.score = 10;
    this.nMatch = 0;
    this.timeLeft = 0;
  }

  @Override
  public void begin(Object data) {
    // TODO Auto-generated method stub
    if (data instanceof GameConfig) {
      this.config = (GameConfig) data;

      graphics().rootLayer().clear();
      graphics().rootLayer().add(backgroundLayer.asLayer());
      backgroundLayer.setImage("playBackground.jpg");
      lvGame = config.getLevel(config.getCurrentLevel());
      log().debug(
          "Info current level: " + config.getCurrentLevel() + "-> COL:" + lvGame.getCol() + " ROW:"
              + lvGame.getRow() + " TIME:" + lvGame.getTime());;
      init();
    }

  }

  private void drawStringDropShadow(Surface g, String text, float x, float y, int c) {
    drawString(g, text, x * 8 + 5, y * 8 + 5, 0);
    drawString(g, text, x * 8 + 4, y * 8 + 4, c);
  }

  private void drawString(Surface g, String text, float x, float y, int c) {
    char[] ch = text.toCharArray();
    for (int i = 0; i < ch.length; i++) {
      g.drawImage(GameLib.font[ch[i] - 32][c], x + i * 8, y);
    }
  }

  private void init() {
    nMatch = 0;
    log().debug(""+nMatch);
    btn1 = null;
    btn2 = null;
    timeLeft = lvGame.getTime() * 35;
    scoreLabel = graphics().createSurfaceLayer(100, 20);
    scoreLabel.setScale(1.5f);
    scoreLabel.setTranslation(195, 15);

    timeLabel = graphics().createSurfaceLayer(graphics().width(), graphics().height());
    timeLabel.setScale(2f);

    graphics().rootLayer().add(timeLabel);
    graphics().rootLayer().add(scoreLabel);

    drawStringDropShadow(scoreLabel.surface(), "" + score, 0, 0, 6);

    int nTotal = this.lvGame.getCol() * this.lvGame.getRow();
    playLayer = graphics().createGroupLayer();
    GameButton btnGame[] = new GameButton[nTotal];
    int nIndex = 0;
    for (int i = 0; i < nTotal; i++) {
      btnGame[i] = new GameButton(playLayer, "block.png");
      btnGame[i].setId(i);
      btnGame[i].setImageHideLink("images/" + nIndex + ".png");
      i++;
      btnGame[i] = new GameButton(playLayer, "block.png");
      btnGame[i].setId(i);
      btnGame[i].setImageHideLink("images/" + nIndex + ".png");
      nIndex++;
    }
    Random rgen = new Random();
    for (int i = 0; i < nTotal; i++) {
      int rand = rgen.nextInt(nTotal);
      String tmp = btnGame[i].getLink();
      btnGame[i].setImageHideLink(btnGame[rand].getLink());
      btnGame[rand].setImageHideLink(tmp);
    }

    for (int i = 0; i < nTotal; i++) {
      final GameButton b = btnGame[i];
      b.init();
      b.setTranslation((i % this.lvGame.getCol()) * (b.getWidth() + 2f), (i / this.lvGame.getCol())
          * (b.getHeight() + 2f));
      b.addListener(new Pointer.Adapter() {

        public void onPointerStart(Pointer.Event event) {
          btnGameClick(b);
        }
      });
    }
    graphics().rootLayer().add(playLayer);
    playLayer.setTranslation((graphics().width() - this.lvGame.getCol() * 71) / 2, 50 + 61);
  }

  protected void btnGameClick(GameButton b) {
    // TODO Auto-generated method stub
    // log().debug("Current Score:" + score);
    log().debug("Button Click " + b.getId() + " -> Info: " + b.getLink());
    if (btn1 == null) {
      btn1 = b;
      b.showHideImage();
    } else if (btn2 == null) {
      if (btn1.getId() != b.getId()) {
        btn2 = b;
        b.showHideImage();
      } else {
        btn1.resetImage();
        btn1 = null;
      }

    }

    if (btn1 != null && btn2 != null) {
      timeCheck = new Timer(500, new Callback<String>() {

        @Override
        public void onSuccess(String result) {
          if (btn1 == null || btn2 == null) return;
          // TODO Auto-generated method stub
          log().debug("Compare Btn1 with Btn2 : " + btn2.Equals(btn1));
          if (btn2.Equals(btn1)) {
            btn1.setVisible(false);
            btn2.setVisible(false);
            score += 20;
            nMatch += 2;
            if (nMatch == lvGame.getCol() * lvGame.getRow()) {
              // Clear level
              if (config.getCurrentLevel() < config.getMaxLevel()) {
                score += 30;
                config.getLevel(config.getCurrentLevel()).setStatus(LevelStatus.CLEAR);
                config.getLevel(config.getCurrentLevel() + 1).setStatus(LevelStatus.UNLOCK);
                manager.setScreen(ScreenType.CHOOSELEVEL, config);
              } else {
                log().debug("Clear all level");
                manager.setScreen(ScreenType.WINGAME, score);
              }

            }
          } else {
            btn1.resetImage();
            btn2.resetImage();
            score -= 10;
            if (score <= 0) {
              log().debug("Game Over!!");
              manager.setScreen(ScreenType.WINGAME, 0);
            }
          }

          btn1 = null;
          btn2 = null;
          scoreLabel.surface().clear();
          drawStringDropShadow(scoreLabel.surface(), "" + score, 0, 0, 6);
        }

        @Override
        public void onFailure(Throwable cause) {
          // TODO Auto-generated method stub

        }

      });
      timeCheck.start();
    }

    // log().debug("Compare with ButtonID_0 : " + b.Equals(btn1));

    // b.resetImage();
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

  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    if (timeCheck != null) {
      timeCheck.update(delta);
    }
    timeLeft--;
    int time = (timeLeft + 35 - 1) / 35;
    timeLabel.surface().clear();
    drawStringDropShadow(timeLabel.surface(), "" + time, 31.5f, 3.5f, 7);
    if (timeLeft == 0) {
      log().debug("TIME OUT!!!");
      score -= 50;
      if (score <= 0) {
        manager.setScreen(ScreenType.WINGAME, 0);
      } else {
        manager.setScreen(ScreenType.CHOOSELEVEL, config);
      }
    }
  }

}
