public class MapFactory{

  //               object name              symbol    color                       canPass
  public static final Cell GRASS =       new Cell("\"",   TextColor.GREEN_BOLD_BRIGHT,  true);
  public static final Cell WATER =       new Cell("~",    TextColor.CYAN_BACKGROUND,    false);
  public static final Cell MINE  =       new Cell("o",    TextColor.RED_BACKGROUND, true);
  public static final Cell TELEPORT  =   new Cell("T",    TextColor.GREEN_BACKGROUND, true);
  public static final Cell BORDER_HOR =   new Cell("-",    TextColor.YELLOW_BOLD,        false);
  public static final Cell BORDER_VERT =  new Cell("|",    TextColor.YELLOW_BOLD,        false);
  
  public static Map arena(int rows, int cols)
  {
    Cell[][] map = new Cell[rows][cols];
    for (int row=0; row<rows; row++)
    {
      for (int col=0; col<cols; col++)
      {
        if (row == 0 || row == rows-1)
        {
          map[row][col] = BORDER_HOR;
        }
        else if (col == 0 || col == cols-1)
        {
          map[row][col] = BORDER_VERT;
        }
        else
        {
          map[row][col] = GRASS;
        }
      }
    }
    return new Map(map);
  }

  public static Map maze(int rows, int cols)
  {
    Cell[][] map = new Cell[rows][cols];
    for (int row=0; row<rows; row++)
    {
      for (int col=0; col<cols; col++)
      {
        if (row == 0 || row == rows-1)
        {
          map[row][col] = BORDER_HOR;
        }
        else if (col == 0 || col == cols-1)
        {
          map[row][col] = BORDER_VERT;
        }
        else if ( row > 2 && row < rows-2 && (col > 4 && row%8 == 0) || (col < cols-4 && row%8 == 4))
        {
          map[row][col] = BORDER_HOR;
        }
        else
        {
          map[row][col] = GRASS;
        }
      }
    }
    return new Map(map);
  }


  
}