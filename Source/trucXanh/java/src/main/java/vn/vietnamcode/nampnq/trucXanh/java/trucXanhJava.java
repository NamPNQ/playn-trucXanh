package vn.vietnamcode.nampnq.trucXanh.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import vn.vietnamcode.nampnq.trucXanh.core.trucXanh;

public class trucXanhJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("vn/vietnamcode/nampnq/trucXanh/resources");
    PlayN.run(new trucXanh());
  }
}
