package java_home_3;

import java.util.Scanner;

public class Program {
    // Проверка возможности хода вниз или вправо
    private static boolean isMovePossible(int x, int y, boolean[][] walls) {
        int rows = walls.length;
        int cols = walls[0].length;
        return x < rows && y < cols && !walls[x][y];
    }

    // Подсчет количества маршрутов
    public static int countRoutes(int x, int y, boolean[][] walls) {
        int rows = walls.length;
        int cols = walls[0].length;
        int[][] dp = new int[rows][cols];
        // Нижняя правая клетка достижима из самой себя
        dp[rows - 1][cols - 1] = 1;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // Пропустить нижнюю правую клетку, так как она уже рассчитана
                if (i == rows - 1 && j == cols - 1) {
                    continue;
                }
                if (isMovePossible(i + 1, j, walls)) {
                    dp[i][j] += dp[i + 1][j];
                }
                if (isMovePossible(i, j + 1, walls)) {
                    dp[i][j] += dp[i][j + 1];
                }
            }
        }

        return dp[x][y];
    }

    // Ввод значений
    private static int inputVal(String string) {
        Scanner iScanner = new Scanner(System.in);

        System.out.print(string);
        boolean bo = iScanner.hasNextInt();
        if (bo == true) {
            int n = iScanner.nextInt();
            return n;
        } else {
            System.out.println("Введено недопустимое значение ");
        }

        iScanner.close();
        return 0;
    }

    // Тестирование решения
    public static void main(String[] args) {

        int n = inputVal("Введите значение N:\n");
        int m = inputVal("Введите значение M:\n");
        if (n > 1 && n < 1000 && m > 1 && m < 1000) {
            int x = inputVal("Введите значение X:\n") - 1;
            int y = inputVal("Введите значение Y:\n") - 1;
            if (x >= 0 && x < n && y >= 0 && y < m) {
                boolean[][] walls = new boolean[n][m];
                walls[1][2] = true; // Пример стены
                int count = countRoutes(x, y, walls);
                System.out.println("Количество маршрутов: " + count);
            } else
                System.out.println("Введено недопустимое значение ");
        } else
            System.out.println("Введено недопустимое значение ");

    }
}
