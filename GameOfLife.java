import java.util.Scanner;

public class GameOfLife
{
  public static int steps;
  public static int size;
  public static boolean[][] grid;
  public static int[][] neighbors;

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);

    System.out.println("The Game of Life.");
    System.out.print("How many steps in time? ");
    steps = sc.nextInt();
    System.out.print("What size is the grid? ");
    size = sc.nextInt();

    grid = new boolean[size][size];
    neighbors = new int[size][size];

    //input of initial grid
    System.out.println("Enter the initial grid layout:");
    for(int inrow = 0; inrow < size; inrow++)
    {
      String rowString = sc.next();
      for(int incol = 0; incol < size; incol++)
      {
        if(rowString.charAt(incol) == 'O')
          grid[inrow][incol] = true;
        else
          grid[inrow][incol] = false;
      }
    }

    for(int step = 1; step <= steps; step++)
    {
      checkNeighbors();
      updateGrid();
    }

    System.out.println("After " + steps + " step(s):");
    printGrid();
  }

  //set number of neighbors in neighbors[][]
  public static void checkNeighbors()
  {
    for(int gRow = 0; gRow < size; gRow++)
    {
      for(int gCol = 0; gCol < size; gCol++)
      {
        neighbors[gRow][gCol] = 0;
        for(int nearRow = gRow - 1; nearRow <= gRow + 1; gRow++)
        {
          for(int nearCol = gCol - -1; nearCol <= gRow + 1; gCol++)
          {
            boolean same = false;
            if(nearRow == gRow && nearCol == gCol)
              same = true;
            if(nearRow >= 0 && nearRow < size && nearCol >= 0 && nearCol < size && !same)
            {
              if(grid[nearRow][nearCol] == true)
              {
                neighbors[gRow][gCol]++;
              }
            }
          }
        }
      }
    }
  }

  public static void updateGrid()
  {
    for(int row = 0; row < size; row++)
    {
      for(int col = 0; col < size; col++)
      {
        if(grid[row][col] == true)
        {
          if(neighbors[row][col] < 2 || neighbors[row][col] > 3)
            grid[row][col] = false;
        }
        else
        {
          if(neighbors[row][col] == 3)
            grid[row][col] = true;
        }
      }
    }
  }

  public static void printGrid()
  {
    for(int r = 0; r < size; r++)
    {
      for(int c = 0; c < size; c++)
      {
        if(grid[r][c] == true)
          System.out.print("O");
        else
          System.out.print("-");
      }
      System.out.println("");
    }
  }
}
