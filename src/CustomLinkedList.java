import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList {
    private Node head;
    private Node tail;
    private int size = 0;

    private List<Node> nodes = new ArrayList<>();

    public void linkLast(Song song) {
        Node node;
        if (tail == null) {
            node = new Node(song, null, null);
            tail = node;
            head = node;
        } else {
            node = new Node(song, null, tail);
            tail.setNext(node);
            tail = node;
        }
        nodes.add(node);
        size++;
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        Node<Song> result = head;
        if (size == 1) {
            songs.add(result.getData());
            return songs;
        } else if (size == 0) {
            return null;
        } else {
            songs.add(result.getData());
            for (int i = 0; i < size - 1; i++) {
                result = result.getNext();
                songs.add(result.getData());
            }
        }
        return songs;
    }

    public Node getSongNode(Song song) {
        Node songNode = null;
        for (Node node : nodes) {
            if (node.getData().equals(song)) {
                songNode = node;
            }
        }
        return songNode;
    }
}
