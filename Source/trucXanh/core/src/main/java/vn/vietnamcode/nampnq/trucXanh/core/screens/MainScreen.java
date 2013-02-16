package vn.vietnamcode.nampnq.trucXanh.core.screens;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.assets;
import playn.core.Image;
import playn.core.Layer;
import playn.core.Pointer;
import playn.core.SurfaceLayer;
import playn.core.TextFormat;
import playn.core.Font;

import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;
import vn.vietnamcode.nampnq.trucXanh.core.render.BackgroundLayer;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ScreenManager.ScreenType;
import vn.vietnamcode.nampnq.trucXanh.core.util.*;

public class MainScreen implements GameScreen {
  
  private final ScreenManager manager;
  private GameConfig config;
  private final BackgroundLayer backgroundLayer;
  private SurfaceLayer logo;
  private Image imgLogo;
  private int tick=0;
  private final InputAdapter inputHandler = new InputAdapter(){
    @Override
    public void onPointerEnd(Pointer.Event event) {
      manager.setScreen(ScreenType.CHOOSELEVEL, config);
    }
  };
  
  public MainScreen(ScreenManager manager,BackgroundLayer backgroundLayer){
    this.manager = manager;
    this.backgroundLayer = backgroundLayer;
    config = new GameConfig();
  }

  @Override
  public void begin(Object data) {
    // TODO Auto-generated method stub
    if (data instanceof GameConfig) {
      GameConfig newConfig = (GameConfig) data;
      this.config = newConfig;
      
      graphics().rootLayer().clear();
      graphics().rootLayer().add(backgroundLayer.asLayer());
      
      backgroundLayer.setImage("trucXanh.jpg");
      
      imgLogo = assets().getImage("images/truc_xanh_logo.png");
      logo = graphics().createSurfaceLayer(900, 500);
      graphics().rootLayer().add(logo);
      logo.setScale(.6f);
      logo.surface().drawImage(imgLogo, 20, 200);
      //ImageLayer logoLayer = graphics().createImageLayer(assets().getImage("images/truc_xanh_logo.png")); 
      //graphics().rootLayer().add(logoLayer);
      //logoLayer.setTranslation((graphics().width()- logoLayer.width()*.6f)/2, 20);
      //logoLayer.setScale(.6f, .6f);
      
      @SuppressWarnings("deprecation")
      Layer textLayer = Util.createTextLayer(graphics().layoutText(
      "Create by: Pham Ngoc Quang Nam", new TextFormat().withFont(graphics().createFont("Courier", Font.Style.PLAIN, 16)).
                             withEffect(TextFormat.Effect.outline(0xFFFFFFFF)).
                             withTextColor(0xFF000066)));
      
      textLayer.setTranslation((graphics().width()- 300)/2, 100);
      graphics().rootLayer().add(textLayer);
      
      textLayer = Util.createTextLayer(graphics().layoutText(
        "Click Anywhere To Continue", new TextFormat()
                                              .withFont(graphics().createFont("Courier", Font.Style.PLAIN, 28))
                                              .withEffect(TextFormat.Effect.outline(0xFFFFFFFF))
                                              .withTextColor(0xFF000066)));
      
      textLayer.setTranslation((graphics().width()- 400)/2, 400);
      graphics().rootLayer().add(textLayer);
    }
  }

  @Override
  public void end() {
    // TODO Auto-generated method stub

  }

  @Override
  public void paint(float alpha) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    tick++;
    logo.surface().clear();
    logo.surface().drawImage(imgLogo, 90, 40-Math.abs((int)(Math.sin((tick*delta)/6.0)*20)));
  }

  @Override
  public InputAdapter inputHandler() {
    // TODO Auto-generated method stub
    return inputHandler;
  }

}
