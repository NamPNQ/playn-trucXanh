package vn.vietnamcode.nampnq.trucXanh.core.screens;

import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;

public interface GameScreen {
  void begin(Object data);
  void end();
  
  InputAdapter inputHandler();
  
  void paint(float alpha);
  void update(float delta);
  
  public static GameScreen NOOP = new GameScreen() {
    @Override public void begin(Object data) { }
    @Override public void end() { }
    @Override public InputAdapter inputHandler() { return null; }
    @Override public void paint(float alpha) { }
    @Override public void update(float delta) { }
  };
}
