import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainVersionTwo {

    public static void main(String[] args) throws IOException {

        int height;
        int width;

        //Мой код получающий параметры с консоли
        System.out.println("Введите высоту ромба:");
        height = MainVersionTwo.checkInputValues();

        System.out.println("Введите ширину ромба:");
        width = MainVersionTwo.checkInputValues();

        System.out.println("\nВаш ромб:");
        MainVersionTwo.printDiamond(height, width);

    }

    /**
     * Метод построения изображения ромба
     */
    private static void printDiamond(int height, int width) {
        char[][] bufferArray = new char[getCenter(height)][getCenter(width)];

        for (int i = 0; i < indexConverter(getCenter(height)); i++) {
            for (int j = 0; j < indexConverter(getCenter(width)); j++) {

            }
        }

        System.out.println();


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


    /**
     * конвертер для учёта значения "0" в индексах массива
     * @param value
     * @return
     */
    private static int indexConverter(int value) {
        return value - 1;
    }

    /**
     * Метод для нахождения центрального значения ряда.<br>
     * Для четных чисел берётся первое из двух центральных значений.
     * @param row
     * @return center value
     */
    private static int getCenter(int row) {
        if (row % 2 == 1) {
            return (row / 2) + 1;
        }
        return row / 2;
    }


}
