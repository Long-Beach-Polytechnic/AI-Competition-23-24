public class Item extends Movable{

  private int healthModifier;
  private final static int HEALTH_BOOST = 30;
  private final static int MINE_DAMAGE = -20;
  public final static int MINE = 100;
  public final static int HEAL = 200;

  public Item(Cell image, int row, int col, int healthModifier)
  {
    super(image,row,col);
    this.healthModifier = healthModifier;
  }

  public static Item create(int code, int row, int col)
  {
    if (code == HEAL)
    {
      return new Item(new Cell("+",TextColor.CYAN),row,col,HEALTH_BOOST);
    }
    if (code == MINE)
    {
      return new Item(new Cell("o",TextColor.RED),row,col,MINE_DAMAGE);
    }
    return new Item(new Cell("?",TextColor.GREEN),row,col,0);
  }

  public int getHealthModifier()
  {
    return healthModifier;
  }

  public boolean isMine()
  {
    return healthModifier < 0;
  }

  public boolean isHeal()
  {
    return healthModifier > 0;
  }

  
}