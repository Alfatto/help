public class Test2 {

    static StringBuilder stringBuilder = new StringBuilder();
    static int myCommad;
    static Stack<StringBuilder> stringStack = new Stack<>();
    static Stack<StringBuilder> redo = new Stack<>();
    static int N;
    static String s = "";
    static boolean reset = false;
    //static String[] strings = {"1 Привет", "1 , Мир!", "1 ++", "2 2", "4", "4", "1 *", "4", "4", "4", "3 6", "2 100"}; работает
    static String[] strings = {"1 Привет", "1 , Мир!", "1 ++", "4", "4", "5", "4", "5", "5", "5", "5", "4", "4", "2 2", "4", "5", "5", "5"};


    public static void main(String[] args) {

        for (String s : strings) {
            System.out.println(BastShoe(s));
        }
    }


    public static String BastShoe(String command) {

        System.out.println(command);
        String[] numbers = command.split(" ");
        N = Integer.parseInt(numbers[0]);

        if (N == 1) {
            //System.out.println("вызов 1: ");
            if (reset) {
                stringStack = new Stack<>();
                redo = new Stack<>();
                stringStack.push(new StringBuilder(stringBuilder));
            }
            stringStack.push(new StringBuilder(stringBuilder));
            stringBuilder = new StringBuilder(command.substring(2, command.length()));
        } else if (N == 2) {
            //System.out.println("вызов 2");
            if (reset) {
                stringStack = new Stack<>();
                redo = new Stack<>();
                stringStack.push(new StringBuilder(stringBuilder));
            }
            myCommad = Integer.parseInt(numbers[1]);
            String s = new String(stringBuilder);
            if (myCommad > stringBuilder.length()) {
                stringBuilder = new StringBuilder();
            } else {
                stringStack.push(new StringBuilder(stringBuilder));
                stringBuilder = new StringBuilder(s.substring(0, s.length() - myCommad));
            }
        } else if (N == 3) {
            //System.out.println("вызов 3");
            if (stringBuilder.length() < Integer.parseInt(numbers[1])) {
                return "null";
            } else {
                return Character.toString(stringBuilder.charAt(Character.getNumericValue(command.charAt(2))));
            }
        } else if (N == 4) {
            //System.out.println("вызов 4");
            //если резет фальс то откатываем по нехочу
            if (stringStack.size() == 1) {
                redo.push(new StringBuilder(stringBuilder));
                return new String(stringBuilder);
            } else {
                //перед изменение билдер отправляем оба стека
                reset = true;
                redo.push(new StringBuilder(stringBuilder));
                stringBuilder = stringStack.pop();
            }

        } else if (N == 5) {
            //System.out.println("вызов 5");
            if (redo.size() == 1) {
                return new String(redo.peek());
            } else {
                //перед изменением отправлем билдер в stek
                //stringStack.pop();
                stringStack.push(stringBuilder);
                //stringStack.push(new StringBuilder(stringBuilder));
                stringBuilder = redo.pop();
            }
        } else {
            return "null";
        }

        return new String(stringBuilder);
    }
}