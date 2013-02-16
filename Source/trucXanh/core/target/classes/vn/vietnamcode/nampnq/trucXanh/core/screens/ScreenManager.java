package vn.vietnamcode.nampnq.trucXanh.core.screens;

import static playn.core.PlayN.keyboard;
import static playn.core.PlayN.pointer;

import java.util.HashMap;
import java.util.Map;

import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;

public class ScreenManager {
  public static enum ScreenType {
    START,
    CHOOSELEVEL,
    PLAYGAME,
    HIGHSCORE,
    WINGAME
  }
  
  private final Map<ScreenType, GameScreen> screens 
      = new HashMap<ScreenType, GameScreen>();
  
  private ScreenType currentType = null;
  
  public GameScreen current() {
    GameScreen at = currentType == null ? null : screens.get(currentType);
    return at != null ? at : GameScreen.NOOP;
  }
  
  public void addScreen(ScreenType type, GameScreen screen) {
    screens.put(type, screen);
  }
  
  public void setScreen(ScreenType type, Object data) {
    if (type == currentType) { return; }
    if (!screens.containsKey(type)) {
      throw new IllegalArgumentException("No screen registered for " + type);
    }
    
    current().end();

    currentType = type;
    GameScreen screen = current();
    screen.begin(data);
    InputAdapter input = screen.inputHandler();
    pointer().setListener(input);
    keyboard().setListener(input);    
  }
}
