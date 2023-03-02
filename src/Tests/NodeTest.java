package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prod.CustomLinkedList;
import prod.Song;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    CustomLinkedList customLinkedList;
    Song song1 = new Song(Duration.ofSeconds(246), "Песня о Тебе", "Нэил Шери");
    Song song2 = new Song(Duration.ofSeconds(200), "Притяжение", "GUMA, КУОК");
    Song song3 = new Song(Duration.ofSeconds(235), "Aerials", "System of A Down");

    @BeforeEach
    public void create() {
        customLinkedList = new CustomLinkedList();
        customLinkedList.linkLast(song1);
        customLinkedList.linkLast(song2);
        customLinkedList.linkLast(song3);
    }

    @Test
    void shouldGetData() {
        Song expectedSong = song1;

        Song returnedSong = (Song) customLinkedList.getSongNode(song1).getData();

        assertEquals(expectedSong, returnedSong);
    }

    @Test
    void shouldGetNext() {
        Song expectedSong = song2;

        Song returnedSong = (Song) customLinkedList.getSongNode(song1).getNext().getData();

        assertEquals(expectedSong, returnedSong);
    }

    @Test
    void shouldGetPrev() {
        Song expectedSong = song2;

        Song returnedSong = (Song) customLinkedList.getSongNode(song3).getPrev().getData();

        assertEquals(expectedSong, returnedSong);
    }

}