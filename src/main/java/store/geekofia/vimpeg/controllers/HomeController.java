package store.geekofia.vimpeg.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HomeController {
    @FXML
    private AnchorPane mediaViewPane;
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private File videoFile;

    @FXML
    protected void onVideoFilePickerClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi")
        );

        // Assuming you have access to the stage
        Stage stage = (Stage) mediaViewPane.getScene().getWindow();
        videoFile = fileChooser.showOpenDialog(stage);

        if (videoFile != null) {
            System.out.println("Video selected: " + videoFile.getAbsolutePath());

            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            // Bind MediaView size to mediaViewPane size
            mediaView.fitWidthProperty().bind(mediaViewPane.widthProperty());
            mediaView.fitHeightProperty().bind(mediaViewPane.heightProperty());

            // Center MediaView in mediaViewPane
            mediaView.layoutXProperty().bind(mediaViewPane.widthProperty().subtract(mediaView.fitWidthProperty()).divide(2));
            mediaView.layoutYProperty().bind(mediaViewPane.heightProperty().subtract(mediaView.fitHeightProperty()).divide(2));

            mediaPlayer.play();
        }
    }
}