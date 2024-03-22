public class Cell{

  private String symbol;
  private String color;
  private boolean canPass;

  public Cell()
  {
    this.symbol = "";
    this.color = TextColor.WHITE;
    this.canPass = true;
  }

  public Cell(String symbol, String color)
  {
    this.symbol = symbol;
    this.color = color;
    this.canPass = true;
  }

  public Cell(String symbol, String color, boolean canPass)
  {
    this.symbol = symbol;
    this.color = color;
    this.canPass = canPass;
  }

  public String getSymbol()
  {
    return symbol;
  }

  public String getColor()
  {
    return color;
  }

  public boolean canPass()
  {
    return canPass;
  }

  public String toString()
  {
    return color + symbol + TextColor.RESET;
  }

  public void setSymbol(String symbol)
  {
    this.symbol = symbol;
  }

  public void setColor(String color)
  {
    this.color = color;
  }

  public boolean equals(Cell anotherCell)
  {
    if (this.symbol.equals(anotherCell.getSymbol()) &&
        this.color.equals(anotherCell.getColor()))
    {
      return true;
    }
    return false;
  }

  
}