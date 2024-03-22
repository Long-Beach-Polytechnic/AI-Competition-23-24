public class Movable {

  protected Cell token;
  protected int row;
  protected int col;

  public Movable(Cell token, int row, int col)
  {
    this.token = token;
    this.row = row;
    this.col = col;
  }

  public Cell getToken()
  {
    return token;
  }
  
  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }

  public void setToken(Cell token)
  {
    this.token = token;
  }

  public void setRow(int newRow)
  {
    this.row = newRow;
  }

  public void setCol(int newCol)
  {
    this.col = newCol;
  }

  public void setLocation(int row, int col)
  {
    this.row = row;
    this.col = col;
  }

  public boolean isLocatedAt(int row, int col)
  {
    return (this.row == row && this.col == col);
  }

  public void moveUp()
  {
    row--;
  }

  public void moveDown()
  {
    row++;
  }

  public void moveLeft()
  {
    col--;
  }

  public void moveRight()
  {
    col++;
  }

  public void randomMove()
  {
    //default behavior: do nothing
  }

  
}