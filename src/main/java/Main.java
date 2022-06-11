import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) throws java.io.IOException {

        int height;
        int width;

        //Мой код получающий параметры с консоли
        System.out.println("Введите высоту ромба:");
        height = Main.checkInputValues();

        System.out.println("Введите ширину ромба:");
        width = Main.checkInputValues();

        System.out.println("\nВаш ромб:");
        Main.printDiamond(height, width);

    }

    /**
     * Метод построения изображения ромба
     */
    private static void printDiamond(int height, int width) {


        for (int currentRow = 0; currentRow < height; currentRow++) {

            //---------
            if (currentRow <= (getCenter(height))) { // ограничиваем цикл до середины ромба по высоте
                for (int currentCell = 0; currentCell < width; currentCell++) {
                    if ((getCenter(width) - currentRow) > 0) { // проверяем не выходит ли ромб за пределы заданной ширины
                        if ((currentCell == (getCenter(width)) - currentRow) || (currentCell == (getCenter(width)) + currentRow)) { //печатаем '#' на необходимых позициях (построитель расширения ромба)
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if (currentCell == 0 || currentCell == ((width - 1))) { // печатаем "#" в крайних позициях по ширине, если ширина ромба выходит за пределы заданной
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    }

                }
            } else { // начинаем сужение ромба
                for (int currentCell = 0; currentCell < width; currentCell++) {
                    if ((currentRow - getCenter(width)) > 0) { // проверяем не выходит ли ромб за пределы заданной ширины
                        if ((currentCell == getCenter(width) - ((width) - currentRow)) /*|| (currentCell == currentRow + )*/) { //печатаем '#' на необходимых позициях (построитель расширения ромба)
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        if (currentCell == 0 || currentCell == ((width - 1))) { // печатаем "#" в крайних позициях по ширине, если ширина ромба выходит за пределы заданной
                            System.out.print("#");
                        } else {
                            System.out.print("_");
                        }
                    }
                }
//                System.out.println("low half");
            }
            System.out.println();

        }


//        System.out.println("Высота вашего ромба: " + height);
//        System.out.println("Ширина вашего ромба: " + width);

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
    private static int getCenter(int value) {
        return value / 2;
    }




}
