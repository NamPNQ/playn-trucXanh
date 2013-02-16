package vn.vietnamcode.nampnq.trucXanh.core.input;

import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;
import playn.core.Pointer;

public class InputAdapter implements Pointer.Listener, Keyboard.Listener {
  @Override public void onKeyDown(Event event) { }
  @Override public void onKeyTyped(TypedEvent event) { }
  @Override public void onKeyUp(Event event) { }
  @Override public void onPointerStart(playn.core.Pointer.Event event) { }
  @Override public void onPointerEnd(playn.core.Pointer.Event event) { }
  @Override public void onPointerDrag(playn.core.Pointer.Event event) { }
}
