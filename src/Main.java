import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();

        while (true) {
            printMenu();

            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    System.out.println("Введите название песни:");
                    String name = scanner.nextLine();

                    System.out.println("Введите исполнителя песни:");
                    String author = scanner.nextLine();

                    System.out.println("Введите продолжительность песни в секундах");
                    String stringDuration = scanner.nextLine();
                    Duration duration = Duration.ofSeconds(Long.parseLong(stringDuration));

                    Song song = new Song(duration, name, author);
                    player.addSong(song);
                    break;

                case 2:
                    player.play();
                    break;

                case 3:
                    player.pause();
                    break;

                case 4:
                    player.next();
                    break;
                case 5:
                    player.prev();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nЧто вы хотите сделать?\n" +
                "1 - Добавить песню в плейлист\n" +
                "2 - Начать воспроизведение плейлиста\n" +
                "3 - Поставить плейлист на паузу\n" +
                "4 - Воспроизвести следующую песню\n" +
                "5 - Воспроизвести предыдущую песню\n" +
                "0 - Выход");
    }
}