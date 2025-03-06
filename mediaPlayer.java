import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class DarkVinylPlayer extends Application {
    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    private Timeline spinAnimation;
    private Canvas canvas;
    private double spinSpeed = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up canvas for drawing
        canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up background gradient (black to dark gray)
        String gradient = "radial-gradient(circle, #222, #111)";
        gc.setFill(Color.web(gradient));
        gc.fillRect(0, 0, 600, 600);

        // Set up the turntable circle (record)
        gc.setFill(Color.DARKGRAY);
        gc.fillOval(200, 200, 200, 200);

        // Set up the spinning text
        Text spinningText = new Text("Your soul remains in this song");
        spinningText.setFont(Font.font("Brush Script MT", 24));  // Cursive font
        spinningText.setFill(Color.RED);
        spinningText.setLayoutX(250);
        spinningText.setLayoutY(100);  // Positioning the text in a nice place for the turntable

        // Load music
        String musicPath = "your-song.mp3";
        Media media = new Media("file:///" + musicPath);
        mediaPlayer = new MediaPlayer(media);

        // Set up spinning animation for turntable and text
        spinAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    if (isPlaying) {
                        gc.rotate(spinSpeed);
                        spinningText.setRotate(spinningText.getRotate() + spinSpeed);  // Rotate the text with the turntable
                    }
                }),
                new KeyFrame(Duration.seconds(0.01))
        );
        spinAnimation.setCycleCount(Timeline.INDEFINITE);

        // Set up click functionality to toggle music and spinning
        canvas.setOnMouseClicked(e -> toggleMusic(gc, spinningText));

        // StackPane layout for the chat functionality
        StackPane root = new StackPane();
        root.getChildren().addAll(canvas, spinningText);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Dark Vinyl Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleMusic(GraphicsContext gc, Text spinningText) {
        if (isPlaying) {
            mediaPlayer.pause();
            spinSpeed = 0; // Stop spinning when paused
        } else {
            mediaPlayer.play();
            spinSpeed = 1; // Normal speed when playing
        }

        // Detect if song is skipped or rewound
        mediaPlayer.setOnSeekDone(() -> {
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            if (currentTime > mediaPlayer.getStopTime().toSeconds()) {
                spinSpeed = 2; // Fast spin when skipping
            } else if (currentTime < mediaPlayer.getStartTime().toSeconds()) {
                spinSpeed = -2; // Reverse spin when rewinding
            }
        });

        isPlaying = !isPlaying;
    }
}
