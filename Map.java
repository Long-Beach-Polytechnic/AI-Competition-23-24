public class Map{

  private Cell[][] map;
  private Cell[][] coveredMap;
  private Player player;

  public Map()
  {
    this.map = new Cell[10][10];
    cloneMap();
  }

  public Map(int rows, int cols)
  {
    this.map = new Cell[rows][cols];
    cloneMap();
  }

  public Map(Cell[][] map)
  {
    this.map = map;
    cloneMap();
  }

  public Cell getCell(int row, int col)
  {
    return map[row][col];
  }

  public Cell getCell(Movable movable)
  {
    return map[movable.getRow()][movable.getCol()];
  }

  public int getRows()
  {
    return map.length;
  }

  public int getCols()
  {
    return map[0].length;
  }

  public void display()
  {
    for (Cell[] row:coveredMap)
    {
      for (Cell cell:row)
      {
        System.out.print(cell.toString() + " ");
      }
      System.out.println();
    }
  }

  public void setCell(Cell cell, int row, int col)
  {
    this.map[row][col] = cell;
  }

  public void cloneMap()
  {
    coveredMap = new Cell[map.length][map[0].length];
    for (int row=0; row<map.length; row++) 
    {
      for (int col=0; col<map[0].length; col++) 
      {
        coveredMap[row][col] = map[row][col];
      }
    }
  }

  public void place(Movable movable)
  {
    coveredMap[movable.getRow()][movable.getCol()] = movable.getToken();
  }

  public void addPlayer(Player player)
  {
    this.player = player;
    place(player);
  }

  public int countCell(Cell findCell)
  {
    int count = 0;
    for (Cell[] row:map)
    {
      for (Cell cell:row)
      {
        if (cell.equals(findCell))
        {
          count++;
        }
      }
    }
    return count;
  }
  
}