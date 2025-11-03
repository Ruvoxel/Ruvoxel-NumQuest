import java.util.Random;
import javax.swing.JOptionPane;

public class Main {
    private static boolean Game_isActive = false;
    private static byte LiveUser = 0;
    private static byte RandomInt;
    private static boolean Totem = false;

    private static Random random = new Random();
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("Start")) setUserValues();

        while (Game_isActive | (args.length == 1 && args[0].equals("Default"))) {
            String Text = "";

            if (LiveUser > 0) {
                switch (LiveUser) {
                    case 3:
                        Text = "У меня еще полно сил! Я бодрый как никогда, играем!";
                        break;
                    case 2:
                        Text = "Ладушки, потерял одну жизнь ну и пофиг, главное что еще могу играть!";
                        break;
                    case 1:
                        Text = "Я еще могу продолжать...";
                        break;
                }

                try {
                    byte Number = Byte.parseByte(JOptionPane.showInputDialog(String.format("Игра началсь!\n\nЖизни: %d\nВы: %s", LiveUser, Text)));
                    
                    if (Number > RandomInt) {
                        JOptionPane.showMessageDialog(null, String.format("Число меньше!\n\nОйой! Смотрти: %d - 1 = %d", LiveUser, LiveUser - 1), "Минус жизнь", JOptionPane.INFORMATION_MESSAGE);
                        LiveUser--;
                    }
                    else if (Number < RandomInt) {
                        JOptionPane.showMessageDialog(null, String.format("Число больше!\n\nОйой! Смотри: %d - 1 = %d", LiveUser, LiveUser - 1), "Минус жизнь", JOptionPane.INFORMATION_MESSAGE);
                        LiveUser--;
                    }    
                    else {
                        JOptionPane.showMessageDialog(null, String.format("Ты выйграл!\n\nСообщение: молодец ты, угадал число!\nЧисло: %d\nЖизни (Осталось): %d\nЖизни (Потерял): %d\nВторая жизнь: %s", RandomInt, LiveUser, Totem ? 4 - LiveUser : 3 - LiveUser, Totem ? "Использована" : "Неиспользована"), "Ты победил!", JOptionPane.QUESTION_MESSAGE);
                        LiveUser = 0;
                        Game_isActive = false;
                    }
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Играй по правилам, хорошо?", "Что-то сломалось :(", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            } else {
                if (args.length == 1 && args[0].equals("Start")) JOptionPane.showMessageDialog(null, String.format("Проигрыш!\n\nВаши жизни: %d", LiveUser), "Ты проиграл", JOptionPane.ERROR_MESSAGE);
                
                if (!Totem) {
                    String Ready = JOptionPane.showInputDialog("Псс... Парень, вижу ты проиграл да? я думаю, ты хочешь продолжить игру и я знаю как тебе помочь. Ты готов? | (Да - согласится; Нет - Отказатся)").toLowerCase();
                    if (Ready.equals("нет")) {
                        JOptionPane.showMessageDialog(null, String.format("Ну парень, знай как хочешь. И будет по твоему!\n\nЧисло было: %s", RandomInt));
                        break;
                    
                    
                    } else if (Ready.equals("да")) {
                        for (long Time=System.currentTimeMillis() + 60000; System.currentTimeMillis() <= Time;) JOptionPane.showMessageDialog(null, String.format("Подожди минуту, и я тебе дам еще одну жинзь!\n\nВремя: %s", (Time - System.currentTimeMillis()) / 1000));
                        
                        LiveUser = 1;
                        Totem = true;

                        JOptionPane.showMessageDialog(null, String.format("Вот, держи одну жизнь, если ты захочешь еще одну. Увы, я тебе не дам больше. Храни ее!\n\nЖизней: 0 + 1 = %d", LiveUser));
                    } else {
                        JOptionPane.showMessageDialog(null, "Парень, я тебя не понял...");
                        main(new String[]{"Default"});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, String.format("Я тебе уже давал шанс...\n\nЧисло было: %d", RandomInt));
                    break;
                    
                }
                 
            }
        }
        

        char Action = JOptionPane.showInputDialog("Введите дейсивие | (+ => Начать; - => Отказатся)").charAt(0);
        if (!(Action == '-' ^ Action == '+') || Action == '-') System.exit(0);
        else main(Action == '+' ? new String[]{"Start"} : args);
        
    }

    private static void setUserValues() {
        int Integer = random.nextInt(11);
        
        Game_isActive = true;   
        LiveUser = 3;
        RandomInt = (byte) (Integer == 0 ? ++Integer : Integer);

        if (Totem) Totem = false;

    }
}