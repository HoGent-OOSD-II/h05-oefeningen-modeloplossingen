package gui;

import domein.DomeinController;
import dto.ContainerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class ToevoegController extends GridPane {
    private DomeinController dc;
    private OverzichtsScherm overzichtsScherm;

    @FXML
    private Button btnVoegToe;

    @FXML
    private TextField txfEigenaar;

    @FXML
    private TextField txfMassa;

    @FXML
    private TextField txfSerienummer;

    @FXML
    private TextField txfVolume;

    @FXML
    private Label lblEigenaarOngeldig;

    @FXML
    private Label lblMassaOngeldig;

    @FXML
    private Label lblSerienummerOngeldig;

    @FXML
    private Label lblVolumeOngeldig;

    public ToevoegController(DomeinController dc, OverzichtsScherm overzichtsScherm) {
        this.dc = dc;
        this.overzichtsScherm = overzichtsScherm;
    }

    @FXML
    private void gaTerugNaarOverzicht(ActionEvent event) {
        overzichtsScherm.show();
    }

    @FXML
    void valideerEigenaar(KeyEvent event) {
        try {
            String value = txfEigenaar.getText();
            if (value.isEmpty())
                throw new Exception();
            lblEigenaarOngeldig.setText("");
        } catch (Exception e) {
            lblEigenaarOngeldig.setText("Ongeldige waarde");
        }
    }

    @FXML
    void valideerMassa(KeyEvent event) {
        valideerInvoerIsStriktPositief(txfMassa, lblMassaOngeldig);
    }

    @FXML
    void valideerSerienummer(KeyEvent event) {
        valideerInvoerIsStriktPositief(txfSerienummer, lblSerienummerOngeldig);
    }

    @FXML
    void valideerVolume(KeyEvent event) {
        valideerInvoerIsStriktPositief(txfVolume, lblVolumeOngeldig);
    }

    private void valideerInvoerIsStriktPositief(TextField txfField, Label lblErrorMessage) {
        try {
            int value = Integer.parseInt(txfField.getText());
            if (value <= 0)
                throw new Exception();
            lblErrorMessage.setText("");
        } catch (Exception e) {
            lblErrorMessage.setText("Ongeldige waarde");
        }
    }

    @FXML
    void voegToe(ActionEvent event) {
        try {
            valideerAlleVelden();
            dc.voegContainerToe(new ContainerDTO(txfEigenaar.getText(), Integer.parseInt(txfMassa.getText()),
                    Integer.parseInt(txfVolume.getText()), Integer.parseInt(txfSerienummer.getText())));
            toonMelding(
                    String.format("Container met serienummer %s werd succesvol toegevoegd.", txfSerienummer.getText()));
            maakAlleVeldenLeeg();
        } catch (NumberFormatException e) {

        } catch (IllegalArgumentException e) {
            toonFoutmelding(e.getMessage());
        }
    }

    private void valideerAlleVelden() {
        valideerEigenaar(null);
        valideerMassa(null);
        valideerVolume(null);
        valideerSerienummer(null);
    }

    private void maakAlleVeldenLeeg() {
        txfEigenaar.setText("");
        txfSerienummer.setText("");
        txfMassa.setText("");
        txfVolume.setText("");
    }

    private void toonMelding(String tekst) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(tekst);
        alert.showAndWait();
    }

    private void toonFoutmelding(String melding) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Container kan niet toegevoegd worden");
        alert.setContentText(melding);
        alert.showAndWait();
    }
}