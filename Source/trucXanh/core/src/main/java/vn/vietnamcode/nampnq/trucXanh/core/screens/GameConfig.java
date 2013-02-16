package vn.vietnamcode.nampnq.trucXanh.core.screens;

import java.util.HashMap;
import java.util.Map;

import vn.vietnamcode.nampnq.trucXanh.core.screens.LevelGame.LevelStatus;



public class GameConfig {
  private final Map<Integer, LevelGame> levelGame = new HashMap<Integer, LevelGame>();
  private Integer currentLevel = null;
  private Integer maxLevel = 0;
  
  public Integer getCurrentLevel(){
    return this.currentLevel;
  }
  public void setCurrentLevel(int level){
    this.currentLevel = level;
  }
  public void addLevel(int idLevel, LevelGame level){
    levelGame.put(idLevel,level);
    if(idLevel>maxLevel){
      maxLevel=idLevel;
    }
  }
  public int getMaxLevel(){
    return this.maxLevel;
  }
  public LevelGame getLevel(Integer idLevel){
    if(!levelGame.containsKey(idLevel)){
      throw new IllegalArgumentException("Khong tim thay level " + idLevel);
    }
    return levelGame.get(idLevel);
  }
  public void changeStatusLevel(Integer idLevel, LevelStatus status){
    if(!levelGame.containsKey(idLevel)){
      throw new IllegalArgumentException("Khong tim thay level " + idLevel);
    }
    levelGame.get(idLevel).setStatus(status);
  }
  public int size() {
    // TODO Auto-generated method stub
    return levelGame.size();
  }
}
