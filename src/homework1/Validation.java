package homework1;

public class Validation {
    static boolean isValidData(String price) {
        try {
            if (isValidPrice(price)) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    private static boolean isValidPrice(String s) {
        return Integer.valueOf(s) >= 0; //can be free
    }
    @Deprecated // заменил поле на комбобокс, отпала необходимость валидации
    static boolean isValidGenre(String s) {
        return s.toUpperCase().matches("ACTION|SIMULATOR|STRATEGY|MOBA|RACE");
    }

    static boolean someIsEmpty(String name, String price, String dev) {
        return name.isEmpty() || price.isEmpty() || dev.isEmpty();
    }
}
