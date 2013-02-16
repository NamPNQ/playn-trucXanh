package vn.vietnamcode.nampnq.trucXanh.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import vn.vietnamcode.nampnq.trucXanh.core.trucXanh;

public class trucXanhHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("trucXanh/");
    PlayN.run(new trucXanh());
  }
}
