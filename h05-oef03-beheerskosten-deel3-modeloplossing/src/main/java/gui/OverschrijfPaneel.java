package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OverschrijfPaneel extends VBox {
    private final DomeinController dc;
    private OverzichtPaneel op1, op2;
    private TextField txfBedrag;

    public OverschrijfPaneel(DomeinController dc, OverzichtPaneel op1, OverzichtPaneel op2) {
        this.dc = dc;
        this.op1 = op1;
        this.op2 = op2;
        buildGui();
    }

    private void buildGui() {
        txfBedrag = new TextField();
        txfBedrag.setPromptText("bedrag");
        Button btnSchrijfOver = new Button("overschrijven");
        btnSchrijfOver.setPrefWidth(100);
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        getChildren().addAll(txfBedrag, btnSchrijfOver);

        btnSchrijfOver.setOnAction((evt) -> verwerkBedrag());
    }

    private void verwerkBedrag() {
        try {
            dc.schrijfOver(Double.parseDouble(txfBedrag.getText()));
            op1.updateLijstRekeningen();
            op2.updateLijstRekeningen();
            geefFeedback(AlertType.INFORMATION, "Het bedrag werd overgeschreven.");
        } catch (NumberFormatException nfe) {
            geefFeedback(AlertType.WARNING, "Dit is geen geldig bedrag!");
        } catch (IllegalArgumentException iae) {
            geefFeedback(AlertType.WARNING, iae.getMessage());
        }
    }

    private void geefFeedback(AlertType alertType, String message) {
        Alert boodschap = new Alert(alertType);
        boodschap.setTitle(String.format("Transactie %sgeslaagd", alertType == AlertType.WARNING ? "niet " : ""));
        boodschap.setContentText(message);
        boodschap.showAndWait();
        txfBedrag.setText("");
        txfBedrag.requestFocus();
    }

}