import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainVersionThree {

    public static void main(String[] args) throws IOException {

        int height;
        int width;

        //Мой код получающий параметры с консоли
        System.out.println("Введите высоту ромба:");
        height = MainVersionThree.checkInputValues();

        System.out.println("Введите ширину ромба:");
        width = MainVersionThree.checkInputValues();

        System.out.println("\nВаш ромб:");
        MainVersionThree.printDiamond(height, width);

    }

    /**
     * Метод построения изображения ромба
     */
    private static void printDiamond(int height, int width) {

        for (int currentRow = 0; currentRow < indexConverter(height); currentRow++) {
            if (currentRow < indexConverter(getCenter(height))) {
                for (int currentCell = 1; currentCell <= width; currentCell++) {
                    if (isInRange((getCenter(width) - currentRow), width)) {
                        if ((currentCell == getCenter(width) - currentRow) || (currentCell == getCenter(width) + currentRow)) {
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if (isOdd(width)) {
                            if (currentCell == 1 || currentCell == indexConverter(width)) {
                                System.out.print("#");
                            } else {
                                System.out.print("_");
                            }
                        } else {
                            if (currentCell == 1 || currentCell == indexConverter(width) - 1) {
                                System.out.print("#");
                            } else {
                                System.out.print("_");
                            }
                        }
                    }

                }
                System.out.println();
            } else {
                for (int currentCell = 1; currentCell <= width; currentCell++) {
                    if (isInRange(getCenter(width) - (height - currentRow), width)) {
                        if (currentCell == (getCenter(width) - (height - currentRow) + 1) || (currentCell == (getCenter(width) + (height - currentRow)) - 1)) {
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if (isOdd(width)) {
                            if (currentCell == 1 || currentCell == indexConverter(width)) {
                                System.out.print("#");
                            } else {
                                System.out.print("_");
                            }
                        } else {
                            if (currentCell == 1 || currentCell == indexConverter(width) - 1) {
                                System.out.print("#");
                            } else {
                                System.out.print("_");
                            }
                        }
                    }

                }
                System.out.println();
            }

        }


    }


    /**
     * Отдельный метод для проверки введенных в консоль данных
     */
    private static int checkInputValues() throws IOException {
        int checkedValue = 0;
        boolean noError;
        do {
            try {
                BufferedReader valueReader = new BufferedReader(
                        new InputStreamReader(System.in));
                checkedValue = Integer.parseInt(valueReader.readLine());
                noError = true;
                if (checkedValue == 0) {
                    System.out.println("Значение грани ромба не может быть меньше 1!\n" +
                            "Попробуйте ещё раз:");
                    noError = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат чисел!\n" +
                        "Попробуйте ещё раз:");
                noError = false;
            }
        } while (!noError);

        return checkedValue;
    }


    /**
     * декремент для учёта значения "0" в индексах массива
     *
     * @param value
     * @return
     */
    private static int indexConverter(int value) {
        return value--;
    }

    /**
     * Метод для нахождения центрального значения ряда.<br>
     * Для <b>чётных</b> чисел берётся <b>первое</b> из двух центральных значений.
     *
     * @param numberRow
     * @return center value
     */
    private static int getCenter(int numberRow) {
        if (numberRow % 2 == 1) {
            return (numberRow / 2) + 1;
        }
        return numberRow / 2;
    }

    /**
     * Проверка числа на <b>нечётность</b>/чётность
     *
     * @param value
     * @return
     */
    private static boolean isOdd(int value) {
        if (value % 2 == 1) {
            return true;
        }
        return false;
    }

    /**
     * Проверка на вхождение числа в определённый числовой ряд<br>
     * По умолчанию <code>min = 1</code>
     *
     * @param value
     * @param max
     * @return
     */
    private static boolean isInRange(int value, int max) {
        int min = 1;
        if (value > max || value < min) {
            return false;
        }
        return true;
    }


}
