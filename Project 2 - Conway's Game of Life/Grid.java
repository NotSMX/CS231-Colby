package Lab02;
/**
* Author: Daniel Yu
* 
* To create and manipulate a 2D array
*/

import java.util.Random;
public class Grid
{

    //to check if two grids have the same contents
    public static boolean gridEquals(int[][] arr1, int[][] arr2)
    {
        if (arr1.length != arr2.length)
        {
            return false;
        }
        
        for (int r = 0; r < arr1.length; r++)
        {
            if (arr1[r].length != arr2[r].length)
            {
                return false;
            }
            else
            {
                for (int c = 0; c < arr1[r].length; c++)
                {
                    if(arr1[r][c] != arr2[r][c])
                    {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    //Function rotates a grid by 90 degrees clockwise
    public static int[][] rotate(int[][] arr)
    {
        int[][] temp = new int[arr[0].length][arr.length];
        for (int c = 0; c < arr[0].length; c++)
        {
            for (int r = arr.length-1; r >= 0; r--)
            {
                temp[c][r] = arr[r][c];
            }
        }
        
        for (int c = 0; c < arr[0].length; c++)
        {
            for (int r = arr.length-1; r >= 0; r--)
            {
                System.out.print(temp[c][r]);
            }
            System.out.println();

        }

        return temp;
    }

    public static void main(String[] args)
    {
        String[][] grid;
        //grid = new String[3][5];

        // if (args.length > 0)
        // {
        //     for (int x = 0; x < args.length; x++)
        //     {

        //         System.out.println(args[x]);
        //         //command lines are strings
        //     }
        // }
        // else
        // {
        //     System.out.println("USAGE: java Grid.java <commandline argument>");
        // }
        
        //grid making
        grid = new String[3][]; 
        Random ran = new Random();
        char letter = (char) ran.nextInt(65, 90);

        for(int i = 0; i < 3; i++)
        {
            grid[i] = new String[i+3];
        } 

        for (int r = 0; r< grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                grid[r][c] = "" + letter;
                //System.out.print(grid[r][c]);
            }
        }
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                System.out.print(grid[r][c]);
            }System.out.println();
        }

        //testing
        int[][] arr1 = new int[2][2];
        int[][] arr2 = new int[2][2];
        int[][] arr3;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                arr1[i][j] = i+j;
                arr2[i][j] = i+j;
            }
        } arr3 = arr1;
        System.out.println(arr1 == arr2);
        System.out.println(arr1 == arr3);
        
        //testing the functionality of gridEquals
        System.out.println(gridEquals(arr1, arr2));

        int[][] arr4 = new int[3][5];
        int[][] arr5 = new int[3][5];
        System.out.println(gridEquals(arr4, arr5));

        int[][] arr6 = new int[3][5];
        int[][] arr7 = new int[3][3];
        System.out.println(gridEquals(arr6, arr7));

        //Testing rotate and gridequals
        int[][] arr8 = 
        {{1, 2},
        {3, 4},
        {5, 6}};

        rotate(arr8);

        int[][] arr9 = 
        {{1,2},
        {3, 4},};

        rotate(arr9);
        System.out.println(gridEquals(arr8, arr9));

    }
}