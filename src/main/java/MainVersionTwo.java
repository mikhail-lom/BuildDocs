import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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




         class Stack {
             private char[] stck;//Queue items storage
             private int tos;//insertion and extraction indices

             //Creation of empty stack of defined size
             Stack(int size) {
                 stck = new char[size];//Memory allocation
                 tos=0;
             }

             //Create stack from another one
             Stack(Stack ob) {
                 tos = ob.tos;
                 stck = new char[ob.stck.length];
             }

             //Create stack with initial values
             Stack(char[] a){
                 stck = new char[a.length];

                 for(int i=0; i < a.length; i++) {
                     push(a[i]);
                 }
             }
             /* Insertion of element to the stack */
             void push(char ch){
                 if (tos == stck.length) {
                     System.out.println(" - Queue is full");
                     return;
                 }
                 stck[tos++] = ch;
             }

             /*Extraction of element from the stack*/
             char pop() {
                 if(tos == 0) {
                     System.out.println(" - Stack is empty");
                     return (char) 0;
                 }
                 return stck[tos++];
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
