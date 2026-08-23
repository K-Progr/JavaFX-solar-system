import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundBank {

    private final Map<String, MediaPlayer> players = new HashMap<>();

    public SoundBank(String[] names) {
        for (String n : names) {
            try {
                Media media = new Media(getClass().getResource("/sounds/" + n + ".wav").toExternalForm());
                MediaPlayer player = new MediaPlayer(media);
                player.setOnEndOfMedia(() -> player.stop());
                players.put(n, player);
            } catch (RuntimeException ex) {
                System.out.println("Could not load sound for " + n);
            }
        }
        warmUp(names[0]);
    }

    private void warmUp(String name) {
        MediaPlayer warmup = players.get(name);
        if (warmup == null) {
            return;
        }
        warmup.setVolume(0);
        warmup.setOnEndOfMedia(() -> {
            warmup.stop();
            warmup.setVolume(1.0);
            warmup.setOnEndOfMedia(() -> warmup.stop());
        });
        warmup.play();
    }

    public void play(String name) {
        MediaPlayer player = players.get(name);
        if (player == null) {
            return;
        }
        player.stop();
        player.seek(Duration.ZERO);
        player.play();
    }

    public void disposeAll() {
        for (MediaPlayer p : players.values()) {
            p.dispose();
        }
    }
}