package version_four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class MainVersionFour {

    public static void main(String[] args) throws IOException {

        int height;
        int width;

        //Мой код получающий параметры с консоли
        System.out.println("Введите высоту ромба:");
        height = checkInputValues();

        System.out.println("Введите ширину ромба:");
        width = checkInputValues();

        System.out.println("\nВаш ромб:");
        printDiamond(height, width);

    }

    /**
     * Метод построения изображения ромба
     */
    private static void printDiamond(int height, int width) {

        /*
        Выполняем алгоритм
         */
        OuterStack outerStack = new OuterStack();
        for (int i = 0; i < height; i++) {
            if (height > 2) { // проверка на отстутствие ромба 2х2
                InnerStack innerStack = new InnerStack(width);
                if (i == 0) { // печатаем начальную строку
                    innerStack.generateFirstString();
                    System.out.println(innerStack.finalString.toString());
                    outerStack.push(innerStack.finalString.toString());
                    innerStack.finalString.clear();
                }

                if (i == height/2 ) { // для нечетных высот
                    if (height%2 == 1) {
                        innerStack.generateCentralString();
                        System.out.println(innerStack.finalString.toString());
                        outerStack.push(innerStack.finalString.toString());
                        innerStack.finalString.clear();
                    }
                }

                if (i == height/2-1 ) { // для чётных высот
                    if (height%2 == 0) {
                        innerStack.generateCentralString();
                        System.out.println(innerStack.finalString.toString());
                        outerStack.push(innerStack.finalString.toString());
                        innerStack.finalString.clear();
                    }
                }
            } else {
                System.out.println("Высота ромба не может быть меньше 3-х!!!");
            }
        }

    }


    /**
     * Отдельный метод для проверки введенных в консоль данных
     */
    public static int checkInputValues() throws IOException {
        int checkedValue = 0;
        boolean noError;
        do {
            try {
                BufferedReader valueReader = new BufferedReader(
                        new InputStreamReader(System.in));
                checkedValue = Integer.parseInt(valueReader.readLine());
                noError = true;
                if (checkedValue < 2) {
                    System.out.println("Значение грани ромба не может быть меньше 2!\n" +
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
     * Метод для нахождения центрального значения ряда.<br>
     * Для <b>чётных</b> чисел берётся <b>первое</b> из двух центральных значений.
     * Также учитывается [0] элемент массива
     *
     * @param numberRow
     * @return center value
     */
    public static int getCenter(int numberRow) {
        if (numberRow % 2 == 1) {
            return (numberRow / 2);
        }
        return (numberRow / 2) - 1;
    }


    /**
     * Проверка на вхождение числа в определённый числовой ряд<br>
     * По умолчанию <code>min = 0</code>
     *
     * @param value
     * @param max
     * @return
     */
    public static boolean isInRange(int value, int max) {
        int min = 0;
        if (value > max || value < min) {
            return false;
        }
        return true;
    }
}





class OuterStack extends Stack<String> {

    public void popAllEven() {
        if (!this.isEmpty()) {
            System.out.print(this.pop());
            this.popAllEven();
        }
    }

    public void popAllOdd() {
        this.pop();
        if (!this.isEmpty()) {
            System.out.print(this.pop());
            this.popAllEven();
        }
    }
}

class InnerStack extends  Stack<String> {
    int size;
    ArrayList<String> finalString;
    InnerStack (int size) {
        this.size = size;
        finalString = new ArrayList<String>();
    }

    public boolean isOdd(int value) {
        if (value % 2 == 1) {
            return true;
        }
        return false;
    }

    public void generateFirstString() {
        if (finalString.size() < size) {
            finalString.add("_");
            generateFirstString();
        } else {
            if (isOdd(size)) {
                finalString.set(size/2,"#");
            } else {
                finalString.set(size/2-1,"#");
            }
        }
    }

    public void generateCentralString() {
        if (finalString.size() < size) {
            finalString.add("_");
            generateFirstString();
        }
        finalString.set(size-1,"#");
        finalString.set(0,"#");

    }
}













