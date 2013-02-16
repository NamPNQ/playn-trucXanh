package vn.vietnamcode.nampnq.trucXanh.core;

import playn.core.Game;

import vn.vietnamcode.nampnq.trucXanh.core.render.BackgroundLayer;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ChooseLevel;
import vn.vietnamcode.nampnq.trucXanh.core.screens.GameConfig;
import vn.vietnamcode.nampnq.trucXanh.core.screens.GameOverScreen;
import vn.vietnamcode.nampnq.trucXanh.core.screens.LevelGame;
import vn.vietnamcode.nampnq.trucXanh.core.screens.LevelGame.LevelStatus;
import vn.vietnamcode.nampnq.trucXanh.core.screens.MainScreen;
import vn.vietnamcode.nampnq.trucXanh.core.screens.PlayScreen;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ScreenManager;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ScreenManager.ScreenType;
import vn.vietnamcode.nampnq.trucXanh.core.util.GameLib;

public class trucXanh implements Game {

  private BackgroundLayer backgroundLayer;

  private ScreenManager manager = new ScreenManager();

  @Override
  public void init() {
    GameLib.init();
    backgroundLayer = new BackgroundLayer();

    manager.addScreen(ScreenType.START, new MainScreen(manager, backgroundLayer));
    manager.addScreen(ScreenType.CHOOSELEVEL, new ChooseLevel(manager, backgroundLayer));
    manager.addScreen(ScreenType.PLAYGAME, new PlayScreen(manager, backgroundLayer));
    manager.addScreen(ScreenType.WINGAME, new GameOverScreen());

    GameConfig config = new GameConfig();
    config.addLevel(1, new LevelGame(1, 2, 5, LevelStatus.UNLOCK));
    config.addLevel(2, new LevelGame(2, 2, 10, LevelStatus.LOCK));
    config.addLevel(3, new LevelGame(2, 2, 5, LevelStatus.LOCK));
    config.addLevel(4, new LevelGame(3, 2, 30, LevelStatus.LOCK));
    config.addLevel(5, new LevelGame(3, 4, 50, LevelStatus.LOCK));
    config.addLevel(6, new LevelGame(4, 4, 60, LevelStatus.LOCK));
    config.addLevel(7, new LevelGame(4, 5, 90, LevelStatus.LOCK));
    config.addLevel(8, new LevelGame(5, 6, 120, LevelStatus.LOCK));

    manager.setScreen(ScreenType.START, config);
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
    manager.current().paint(alpha);
  }

  @Override
  public void update(float delta) {
    manager.current().update(delta);
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
