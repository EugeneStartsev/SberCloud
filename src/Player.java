import java.time.Duration;
import java.time.LocalTime;

public class Player {
    private final CustomLinkedList listOfSongs = new CustomLinkedList();
    private Duration songDuration;
    private Song currentSong;
    private LocalTime endTimeOfSong;
    private boolean isPlayed;
    private boolean isPaused;
    private boolean isPrev;
    private LocalTime startTimeOfSong;
    private LocalTime pauseTime;
    private Thread songThread = new Thread();

    public synchronized void addSong(Song song) {
        listOfSongs.linkLast(song);
    }

    public synchronized void play (boolean isPlayed) {
        if (isPlayed) {
            startTimeOfSong = LocalTime.now();
            songDuration = currentSong.getDuration();
            endTimeOfSong = LocalTime.now().plus(songDuration);
            isPaused = false;
            isPrev = false;
            System.out.println("\nСейчас играет: \n" + currentSong);
        } else System.out.println("\nСейчас ничего не играет");
    }

    public synchronized void play() {
        if (!isPlayed) {
            if (isPaused) {
                Duration leftToListen = Duration.between(pauseTime, endTimeOfSong);
                endTimeOfSong = LocalTime.now().plus(leftToListen);
                isPaused = false;
            } else {
                startTimeOfSong = LocalTime.now();
                songThread = new Thread(new SongThread());
                currentSong = listOfSongs.getSongs().get(0);
                songDuration = currentSong.getDuration();
                endTimeOfSong = startTimeOfSong.plus(songDuration);
                isPlayed = true;
                songThread.start();
            }
        } else System.out.println("\nПлейлист уже запущен!");
        System.out.println("\nСейчас играет: \n" + currentSong);
    }

    public synchronized void pause() {
        pauseTime = LocalTime.now();
        isPlayed = false;
        isPaused = true;
        System.out.println("Плейлист на паузе");
    }

    public synchronized void next() {
        if (songThread.isAlive()) {
            int indexLastSong = listOfSongs.getSongs().size() - 1;
            if (currentSong.equals(listOfSongs.getSongs().get(indexLastSong))) {
                System.out.println("\nСейчас играет последняя песня. Вы можете включить сначала или предыдущую");
            } else if (listOfSongs.getSongs().size() > 1) {
                currentSong = (Song) listOfSongs.getSongNode(currentSong).getNext().getData();
                play(isPlayed);
            }
        } else System.out.println("Сейчас ничего не играет!");
    }

    public synchronized void prev() {
        isPrev = true;
        if (songThread.isAlive()) {
            if (currentSong.equals(listOfSongs.getSongs().get(0))) {
                System.out.println("\nСейчас играет первая песня. Вы можете включить следующую");
            } else if (listOfSongs.getSongs().size() > 1) {
                currentSong = (Song) listOfSongs.getSongNode(currentSong).getPrev().getData();
                play(isPlayed);
            }
        } else System.out.println("\nНа данный момент ничего не воспроизводится!");
    }

    class SongThread implements Runnable {
        @Override
        public void run() {
            while (!songThread.isInterrupted()) {
                out:
                while (!isPaused) {
                    if (isPrev) {
                        break;
                    }
                    while (LocalTime.now().isBefore(endTimeOfSong)) {
                        if (isPaused) {
                            break out;
                        }
                    }
                    int countSongs = listOfSongs.getSongs().size();
                    if (currentSong.equals(listOfSongs.getSongs().get(countSongs - 1))) {
                        System.out.println("\nПлейлист закончился");
                        isPlayed = false;
                        songThread.interrupt();
                        break;
                    } else {
                        next();
                    }
                }
            }
        }
    }
}
