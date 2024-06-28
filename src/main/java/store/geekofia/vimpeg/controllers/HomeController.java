package store.geekofia.vimpeg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeController {
    @FXML
    private AnchorPane mediaViewPane;
    @FXML
    private MediaView mediaView;

    @FXML
    private Label versionLabel;

    private MediaPlayer mediaPlayer;
    private File videoFile;

    @FXML
    public void initialize() {
        String commitHash = getCommitHash();
        versionLabel.setText("Build " + commitHash);
    }

    @FXML
    protected void onVideoFilePickerClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi")
        );

        Stage stage = (Stage) mediaViewPane.getScene().getWindow();
        videoFile = fileChooser.showOpenDialog(stage);

        if (videoFile != null) {
            System.out.println("Video selected: " + videoFile.getAbsolutePath());

            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
            mediaViewPane.getChildren().add(mediaView);

            mediaPlayer.play();
        }
    }

    private String getCommitHash() {
        try {
            Process process = Runtime.getRuntime().exec("git rev-parse --short HEAD");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            reader.close();
            process.waitFor();
            return line != null ? line.trim() : "unknown";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "unknown";
        }
    }
}