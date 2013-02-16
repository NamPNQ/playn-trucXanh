package vn.vietnamcode.nampnq.trucXanh.core.screens;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.log;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import vn.vietnamcode.nampnq.trucXanh.core.entities.LevelChooseButton;
import vn.vietnamcode.nampnq.trucXanh.core.input.InputAdapter;
import vn.vietnamcode.nampnq.trucXanh.core.render.BackgroundLayer;
import vn.vietnamcode.nampnq.trucXanh.core.screens.LevelGame.LevelStatus;
import vn.vietnamcode.nampnq.trucXanh.core.screens.ScreenManager.ScreenType;


public class ChooseLevel implements GameScreen {

  private ScreenManager manager;
  private BackgroundLayer backgroundLayer;
  private ImageLayer cloud1, cloud2, logo;
  private Image imgCloud1, imgCloud2, imgLogo;
  private float cloud1X, cloud1Y;
  private float cloud2X, cloud2Y;
  private float bWidth, bHeight;
  private GameConfig config = new GameConfig();

  public ChooseLevel(ScreenManager manager, BackgroundLayer backgroundLayer) {
    this.manager = manager;
    this.backgroundLayer = backgroundLayer;

    this.imgCloud1 = assets().getImage("images/Cloud1.png");
    this.imgCloud2 = assets().getImage("images/Cloud3.png");
    this.imgLogo = assets().getImage("images/choose_level_logo.png");

    this.cloud1 = graphics().createImageLayer(imgCloud1);
    this.cloud1.setOrigin(imgCloud1.width() * 0.3f / 2f, imgCloud1.height() * 0.3f / 2f);
    this.cloud1.setScale(graphics().width() / imgCloud1.width() * 0.3f, graphics().height()
        / imgCloud1.height() * 0.3f);

    this.cloud2 = graphics().createImageLayer(imgCloud2);
    this.cloud2.setOrigin(imgCloud2.width() * 0.3f / 2f, imgCloud2.height() * 0.3f / 2f);
    this.cloud2.setScale(graphics().width() / imgCloud2.width() * 0.3f, graphics().height()
        / imgCloud2.height() * 0.3f);

    this.logo = graphics().createImageLayer(imgLogo);
    this.logo.setScale(graphics().width() / imgCloud2.width() * 0.1f, graphics().height()
        / imgCloud2.height() * 0.1f);


  }

  @Override
  public void begin(Object data) {
    // TODO Auto-generated method stub
    if (data instanceof GameConfig) {
      this.config = (GameConfig) data;
      graphics().rootLayer().clear();
      graphics().rootLayer().add(backgroundLayer.asLayer());
      graphics().rootLayer().add(cloud1);
      graphics().rootLayer().add(logo);
      graphics().rootLayer().add(cloud2);

      logo.setTranslation(15f, 15f);

      backgroundLayer.setImage("bg.png");
      GroupLayer levelLayer = graphics().createGroupLayer();
      int bLen = 4;
      for (int i = 0; i < config.size(); i++) {
        // ImageLayer block = graphics().createImageLayer(blockImage);
        // block.setTranslation((i%bLen)*blockImage.width()+2f, (i/bLen)*blockImage.height()+2f);
        // blocksLayer.add(block);
        final LevelChooseButton b = new LevelChooseButton(levelLayer, "choose_level.png", i+1);
        bWidth = b.getWidth();
        bHeight = b.getHeight();
        b.setTranslation((i % bLen) * (b.getWidth() + 2f), (i / bLen) * (b.getHeight() + 2f));
        // b.setId(i);
        if (config.getLevel(i+1).getStatus() == LevelStatus.UNLOCK) {
          b.setVisibleOverlay(false);
        }else if(config.getLevel(i+1).getStatus() == LevelStatus.CLEAR){
          b.addOverlay(assets().getImage("images/clear.png"));
        };
        b.addListener(new Pointer.Adapter() {

          public void onPointerStart(Pointer.Event event) {
            levelSelect(b);
          }
        });
      }
      graphics().rootLayer().add(levelLayer);
      levelLayer.setTranslation((graphics().width() - bLen * bWidth) / 2, 50 + bHeight);

    }
  }

  protected void levelSelect(LevelChooseButton b) {
    // TODO Auto-generated method stub
    log().debug(Integer.toString(b.getId()));
    log().debug(config.getLevel(b.getId()).getStatus().toString());
    if((config.getLevel(b.getId()).getStatus() == LevelStatus.UNLOCK) || (config.getLevel(b.getId()).getStatus() == LevelStatus.CLEAR )){
      config.setCurrentLevel(b.getId());
      manager.setScreen(ScreenType.PLAYGAME, config);
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

  }

  @Override
  public void update(float delta) {
    // TODO Auto-generated method stub
    cloud1X += delta * 0.1f;
    cloud1.setTranslation(cloud1X, cloud1Y);

    if (cloud1X > cloud1.width() + graphics().width()) {
      cloud1X = -cloud1.width();
      cloud1Y = (float) (Math.random() * graphics().height());
    }

    cloud2X += delta * 0.08f;
    cloud2.setTranslation(cloud2X, cloud2Y);
    if (cloud2X > cloud2.width() + graphics().width()) {
      cloud2X = -cloud1.width();
      cloud2Y = (float) (Math.random() * graphics().height());
    }
  }

}
