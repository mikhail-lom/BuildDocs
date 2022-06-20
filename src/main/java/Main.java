import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


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






        /*
        Выполняем алгоритм
         */
        OuterStack outerStack = new OuterStack();

        if (height > 2) { // ограничение на ввод ромба 2х2
            for (int currentRow = 0; currentRow < height; currentRow++) { // цикл построения ромба
                DiamondStack currentDiamond = new DiamondStack(width, currentRow);

                // случай серединного ряда
                if (currentRow == getCenter(height)) {
                    currentDiamond.printCenter(outerStack);
                } else {
                    // случай первого ряда
                    if (currentRow == 0) {
                        currentDiamond.printFirstRow(outerStack);
                    } else { //общий случай
                        if (currentRow < getCenter(height)) { // построение ромба до центра
                            if (isInRange((getCenter(width) - currentRow), width)) {
                                currentDiamond.pushAllUpperPart();
                                if (isOdd(width)) {
                                    currentDiamond.popAllOdd();
                                } else {
                                    currentDiamond.popAllEven();
                                }
                                System.out.println();
                            } else { // случай выхода ромба за пределы изображения. Аналогичен случаю серединного ряда
                                currentDiamond.printCenter(outerStack);
                            }
                        }

//                        else {
//                            if (isOdd(height)) {
//                                if (isInRange((getCenter(width) - (height - currentRow)), width)) {
//                                    currentDiamond.pushAllLowerPart();
//                                    if (isOdd(width)) {
//                                        currentDiamond.popAllOdd();
//                                    } else {
//                                        currentDiamond.popAllEven();
//                                    }
//                                    System.out.println();
//                                } else {
//                                    currentDiamond.printCenter();
//                                }
//                            } else {
//
//                            }
//                        }
                    }
                }
            }
        } else {
            System.out.println("Высота ромба не может быть меньше 3-х!!!");
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
     * Проверка числа на <b>нечётность</b>/чётность
     *
     * @param value
     * @return
     */
    public static boolean isOdd(int value) {
        if (value % 2 == 1) {
            return true;
        }
        return false;
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

    public void createCell(String finalString) {
        this.push(finalString);
    }

}

class DiamondStack extends Stack<String> {
    private int diamondWidth; // количество ячеек в ряду
    private int currentRow; // количество рядов в ромбе

    public String finalString;


    DiamondStack(int diamondWidth, int currentRow) {
        this.diamondWidth = diamondWidth;
        this.currentRow = currentRow;
        finalString = "";

    }


    /**
     * Построение изображения первого ряда
     */
    public void createFirstRow() {
//        if (this.size() <= Main.getCenter(diamondSize)) { // ограничиваем размер стека центром изображения
//            if (this.size() == Main.getCenter(diamondSize)) { // записываем "#" в центральный символ
//                this.push("#");
//                System.out.print("#");
//                finalString.concat("#");
//            } else {
//                this.push("_");
//                System.out.print("_");
//                finalString.concat("_");
//                this.pushAllFirstRow();
//            }
//        }

        if (Main.isOdd(diamondWidth)) {
            if (finalString.length() <= Main.getCenter(diamondWidth)) {
                finalString = finalString.concat("_");
                createFirstRow();
            }
            if (finalString.length() == Main.getCenter(diamondWidth)) {
                finalString = finalString.concat("#");
            }
            if (finalString.length() > Main.getCenter(diamondWidth) &&
                    finalString.length() < diamondWidth) {
                finalString = finalString.concat("_");
                createFirstRow();
            }
        }
    }

    public void printCenter(OuterStack stack) {
        this.pushAllCenterRow();
        this.popAllCenterRow();
        stack.createCell(finalString);
        System.out.println();
    }

    public void printFirstRow(OuterStack stack) {
        this.createFirstRow();
        this.popAllFirstRow();
        stack.createCell(finalString);
        System.out.println();
    }

    /**
     * Внесение элементов в стек и печать в консоль
     */
    public void pushAllUpperPart() {
        if (this.size() < Main.getCenter(diamondWidth)) {
            if (this.size() == (Main.getCenter(diamondWidth)) - currentRow) {
                this.push("#");
                System.out.print("#");
                finalString.concat("#");
                this.pushAllUpperPart();
            } else {
                this.push("_");
                System.out.print("_");
                finalString.concat("_");
                this.pushAllUpperPart();
            }
        }
    }



    /**
     * Извлечение элементов для <b>чётного</b> ряда
     */
    public void popAllEven() {
        if (!this.isEmpty()) {
            String item = this.pop();
            System.out.print(item);
            finalString.concat(item);
            this.popAllEven();
        }
    }

    /**
     * Извлечение элементов для <b>нечётного</b> ряда
     */
    public void popAllOdd() {
        this.pop();
        if (!this.isEmpty()) {
            String item = this.pop();
            System.out.print(item);
            finalString.concat(item);
            this.popAllEven();
        }
    }

    /**
     * Печать первой строки
     */
    public void popAllFirstRow() {
        popAllOdd();
        if (!Main.isOdd(diamondWidth)) {
            System.out.print("_");
            finalString.concat("_");
        }
    }

    public void popAllCenterRow() {
        if (!Main.isOdd(diamondWidth)) {
            popAllEven();
        } else {
            popAllOdd();
        }
    }


    /**
     * Построение изображения центрального ряда (построить просто строку ArrayList или String.charAt())
     */
    public void pushAllCenterRow() {
        if (this.size() < Main.getCenter(diamondWidth)) { // ограничиваем размер стека центром изображения
            if (this.size() == 0) { // записываем "#" в первый символ
                this.push("#");
                System.out.print("#");
                finalString.concat("#");
                this.pushAllCenterRow();
            } else {
                this.push("_");
                System.out.print("_");
                finalString.concat("_");
                this.pushAllCenterRow();
            }
        }
    }


}



