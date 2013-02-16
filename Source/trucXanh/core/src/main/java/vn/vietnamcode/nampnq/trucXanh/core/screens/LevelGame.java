package vn.vietnamcode.nampnq.trucXanh.core.screens;


public class LevelGame {
  
  public enum LevelStatus {
    LOCK, CLEAR, UNLOCK
  };
  
  int row;
  int col;
  int time;
  LevelStatus status;

  public LevelGame(int row, int col, int time, LevelStatus status) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.status = status;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public int getTime() {
    return this.time;
  }
  public void setStatus(LevelStatus status){
    this.status = status;
  }
  public LevelStatus getStatus() {
    return this.status;
  }

}
