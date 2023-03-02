package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prod.CustomLinkedList;
import prod.Player;
import prod.Song;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomLinkedListTests {

    Player player;
    CustomLinkedList customLinkedList;
    Song song1 = new Song(Duration.ofSeconds(246), "Песня о Тебе", "Нэил Шери");
    Song song2 = new Song(Duration.ofSeconds(200), "Притяжение", "GUMA, КУОК");

    @BeforeEach
    public void create() {
        customLinkedList = new CustomLinkedList();
        player = new Player();
    }

    @Test
    public void shouldBeTwoSize() {
        int expectedSize = 2;

        customLinkedList.linkLast(song1);
        customLinkedList.linkLast(song2);
        int returnedSize = customLinkedList.getSongs().size();

        assertEquals(returnedSize, expectedSize);
    }

    @Test
    public void shouldBeNull() {
        List<Song> returnedSize = customLinkedList.getSongs();

        assertNull(returnedSize);
    }

    @Test
    public void shouldReturnListWithSongs() {
        List<Song> expectedList = new ArrayList<>();
        expectedList.add(song1);
        expectedList.add(song2);

        customLinkedList.linkLast(song1);
        customLinkedList.linkLast(song2);
        List<Song> returnedList = customLinkedList.getSongs();

        assertEquals(expectedList, returnedList);
    }

    @Test
    public void shouldBeLastSong() {
        Song expectedSong = new Song(Duration.ofSeconds(200), "Притяжение", "GUMA, КУОК");

        customLinkedList.linkLast(song1);
        customLinkedList.linkLast(song2);
        Song returnedSong = customLinkedList.getSongs().get(customLinkedList.getSongs().size() - 1);

        assertEquals(expectedSong, returnedSong);
    }
}