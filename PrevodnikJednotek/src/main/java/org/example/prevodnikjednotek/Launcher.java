package org.example.prevodnikjednotek;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.geometry.Pos;
import javafx.scene.Scene;

public class Launcher extends Application{
    int pocet_poli = 1;         // POČÍTÁ POČET POLÍ A PODLE TOHO PRODLUŽUJE SEZNAM
    int povolitpokracovani = 0;     // DOKÁŽE ZASTAVIT PROGRAM VPŘÍPADĚ, ŽE NEJSOU ZADÁNY VŠECHNY POTŘEBNÉ HODNOTY
    int pocet_hboxu = pocet_poli + 2;   // POČÍTÁ POČET ŘÁDKŮ A DOKÁŽE JE TAK MAZAT A VYTVÁŘET

    public void start(Stage primaryStage) {

        VBox svisly_panel = new VBox();     // VYTVOŘENÍ BORDERU A VBOXU

        HBox[] hbox = new HBox[25];     // VYTVOŘENÍ 25 HBOXŮ

        Text[] tx = new Text[25];       // 25 TEXTOVÝCH POLÍ
        Text[] tx2 = new Text[25];      // 25 TEXTOVÝCH POLÍ PRO VYPSÁNÍ JEDNOTEK
        TextField[] txF = new TextField[4]; // 4x TEXTOVÉ POLE PRO ZADÁNÍ HODNOT
        ComboBox[] cB = new ComboBox[4]; // 4x COMBOBOX PRO ZADÁNÍ JEDNOTEK

        Button bt1 = new Button("+");
        Button bt3 = new Button("-");
        Button bt2 = new Button("Výpočet");     // VYTVOŘENÍ BUTTONŮ

        bt1.setPrefWidth(70);
        bt3.setPrefWidth(70);
        bt2.setPrefWidth(70);       // VYTVOŘENÍ BUTTONŮ

        svisly_panel.setSpacing(8);     // NASTAVENÍ ODSAZENÍ MEZI HBOXY
        svisly_panel.setPadding(new Insets(20));

        for (int i = 1; i <= 3; i++) {
            hbox[i] = new HBox();
            tx[i] = new Text();
            txF[i] = new TextField();
            cB[i] = new ComboBox();

            tx[i].setText(i + ". hodnota");
            tx[i].setFont(Font.font("Arial", 14));

            cB[i].getItems().addAll("km", "t", "den", "km²", "km³", "MN", "MW", "MJ", "m/s", "ha", "kN", "kW", "kJ", "km/h", "a", "m", "kg", "N", "W", "J", "m²", "m³", "dm", "hg", "mN", "mW", "mJ", "hod", "dm²", "dm³", "cm", "dag", "min", "cm²", "cm³", "mm", "g", "s", "mm²", "mm³");
            cB[i].setPromptText("Jednotka");

            hbox[i].setSpacing(8);
            hbox[i].setAlignment(Pos.CENTER);

            svisly_panel.getChildren().add(hbox[i]);        // PŘIDÁNÍ A NASTAVENÍ PRVNÍCH 3 HBOXŮ
        }

        hbox[1].getChildren().addAll(tx[1], txF[1], cB[1]);
        hbox[2].getChildren().addAll(bt1, bt2);     // PŘIDÁME DEFAULTNÍ ZOBRAZENÍ: 1 TEXTOVÉ POLE, 1 VÝPLŇOVÉ, JEDEN COMBOBOX A TLAČÍTKO PLUS A VÝPOČET

        for (int i = 4; i <= hbox.length - 1; i++) {
            hbox[i] = new HBox();
            tx[i] = new Text();
            tx2[i] = new Text();

            hbox[i].setSpacing(8);
            hbox[i].setAlignment(Pos.CENTER);
            hbox[i].getChildren().addAll(tx[i], tx2[i]);

            svisly_panel.getChildren().add(hbox[i]);        // PŘIDÁME A NASTAVÍME DALŠÍ HBOXY A TEXTOVÉ POLE
        }

        bt1.setOnAction(event-> {
            tx[1].setText("1. hodnota");
            if (hbox[3].getChildren().contains(tx[3])){
                hbox[3].getChildren().remove(tx[3]);
            }
            vymazatMtd(tx, tx2);
            if (pocet_poli == 1){
                hbox[2].getChildren().removeAll(bt1, bt2);
                hbox[2].getChildren().addAll(tx[2], txF[2], cB[2]);
                hbox[3].getChildren().addAll(bt1, bt3, bt2);
                pocet_poli = 2;
            }
            else{ // pocet_poli = 2;
                hbox[3].getChildren().removeAll(bt1, bt3, bt2);
                hbox[3].getChildren().addAll(tx[3], txF[3], cB[3]);
                tx[3].setText(3 + ". hodnota");
                hbox[4].getChildren().addAll(bt3, bt2);
                pocet_poli = 3;
            }
        });

        bt3.setOnAction(event->{
            tx[1].setText("1. hodnota");
            vymazatMtd(tx, tx2);
            if (pocet_poli == 2){
                hbox[3].getChildren().removeAll(bt1, bt3, bt2);
                hbox[2].getChildren().removeAll(tx[2], txF[2], cB[2]);
                hbox[2].getChildren().addAll(bt1, bt2);
                pocet_poli = 1;
            }
            else{ // pocet_poli = 3
                hbox[4].getChildren().removeAll(bt1, bt3, bt2);
                hbox[3].getChildren().removeAll(tx[3], txF[3], cB[3]);
                hbox[3].getChildren().addAll(bt1, bt3, bt2);
                pocet_poli = 2;
            }
        });

        bt2.setOnAction(event ->{
            Double[] pole_hodnot = new Double[4];
            String[] pole_jednotek = new String[4];

            for (int i = 1; i <= pocet_poli; i++){
                if (!(txF[i].getText().isEmpty() || cB[i].getValue() == null)) {
                    pole_jednotek[i] = (String) cB[i].getValue();
                    try {
                        pole_hodnot[i] = Double.valueOf(txF[i].getText());
                    } catch (NumberFormatException vjmk){
                        tx[1].setText("Neplatný zápis!");
                        i = pocet_poli;
                        povolitpokracovani = 1;
                    }
                }
                else{
                    tx[1].setText("Neplatný zápis!");
                    i = pocet_poli;
                    povolitpokracovani = 1;
                }
            }
            if (povolitpokracovani == 0) {
                tx[1].setText("1. hodnota");
                vymazatMtd(tx, tx2);
                Double[][] PoleUsporadanchHodnot = new Double[5][11];    // VYTVOŘÍME POLE UŽ USPOŘÁDANÝCH HODNOT, DO KTERÉHO BUDEME POTÉ MOCI NAHLÍŽET
                String[][] PoleUsporadanychJednotek = new String[5][11];    // VYTVOŘÍME POLE JEDNOTEK, TYTO JEDNOTKY BUDOU MÍT STEJNOU POZICI JAKO HODNOTA
                Hodnoty[] PoleZadanychHodnot = new Hodnoty[5];      // VYTVOŘÍME POLE INSTANCÍ TŘÍDY HODNOTY, TYTO INSTANCE BUDOU HODNOTAMI, KTERÉ ZADÁME
                PoleUsporadanychJednotek[0][0] = "volno";

                for (int i = 1; i <= pocet_poli; i++) {       // PROGRAM BUDE NAČÍTAT A DOPŇOVAT DO POLE INSTANCÍ, DOKUD NENAPLNÍ VŠECHNY HODNOTY
                    PoleZadanychHodnot[i] = new Hodnoty(pole_hodnot[i], pole_jednotek[i]);          // VYTVOŘÍME PRVNÍ INSTANCI TŘÍDY
                    PoleZadanychHodnot[i].ZapisDoPole(i, PoleUsporadanchHodnot, PoleUsporadanychJednotek);  // NECHÁME ZAPSAT DANOU JEDNOTKU DO POLE USPOŘÁDANÝCH HODNOT A JEDNOTEK
                }

                if (pocet_poli == 1){
                    PoleUsporadanychJednotek[0][1] = "Převod jednotek:";
                    if (PoleZadanychHodnot[1].Skupina.equals("Délka")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m", (PoleUsporadanchHodnot[1][6] * 2 * Math.PI), 2);
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m²", (PoleUsporadanchHodnot[1][6] * PoleUsporadanchHodnot[1][6] * Math.PI), 3);
                        PoleUsporadanychJednotek[0][2] = "Obvod kruhu:";
                        PoleUsporadanychJednotek[0][3] = "Obsah kruhu:";
                    }
                } else if (pocet_poli == 2) {
                    if (PoleZadanychHodnot[1].Skupina.equals("Délka") && PoleZadanychHodnot[2].Skupina.equals("Délka")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m²", PoleUsporadanchHodnot[1][6] * PoleUsporadanchHodnot[2][6], 3);
                        PoleUsporadanychJednotek[0][3] = "Obsah:";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Čas") && PoleZadanychHodnot[2].Skupina.equals("Délka")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m/s", PoleUsporadanchHodnot[2][6] / PoleUsporadanchHodnot[1][3], 3);
                        PoleUsporadanychJednotek[0][3] = "Rychlost";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Délka") && PoleZadanychHodnot[2].Skupina.equals("Čas")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m/s", PoleUsporadanchHodnot[1][6] / PoleUsporadanchHodnot[2][3], 3);
                        PoleUsporadanychJednotek[0][3] = "Rychlost:";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Čas") && PoleZadanychHodnot[2].Skupina.equals("Rychlost")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m", PoleUsporadanchHodnot[1][3] * PoleUsporadanchHodnot[2][8], 3);
                        PoleUsporadanychJednotek[0][3] = "Dráha:";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Rychlost") && PoleZadanychHodnot[2].Skupina.equals("Čas")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m", PoleUsporadanchHodnot[1][8] * PoleUsporadanchHodnot[2][3], 3);
                        PoleUsporadanychJednotek[0][3] = "Dráha:";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Délka") && PoleZadanychHodnot[2].Skupina.equals("Rychlost")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "s", PoleUsporadanchHodnot[1][6] / PoleUsporadanchHodnot[2][8], 3);
                        PoleUsporadanychJednotek[0][3] = "Doba:";
                    } else if (PoleZadanychHodnot[1].Skupina.equals("Rychlost") && PoleZadanychHodnot[2].Skupina.equals("Délka")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "s", PoleUsporadanchHodnot[2][6] / PoleUsporadanchHodnot[1][8], 3);
                        PoleUsporadanychJednotek[0][3] = "Doba:";
                    } else {
                        PoleUsporadanychJednotek[0][0] = "nedostupne prevody";
                    }
                } else { // pocet_poli == 3
                    if (PoleZadanychHodnot[1].Skupina.equals("Délka") && PoleZadanychHodnot[2].Skupina.equals("Délka") && PoleZadanychHodnot[3].Skupina.equals("Délka")) {
                        NovyVyraz(PoleUsporadanchHodnot, PoleUsporadanychJednotek, PoleZadanychHodnot, "m³", PoleUsporadanchHodnot[1][6] * PoleUsporadanchHodnot[2][6] * PoleUsporadanchHodnot[3][6], 4);
                        PoleUsporadanychJednotek[0][4] = "Objem:";
                    } else {
                        PoleUsporadanychJednotek[0][0] = "nedostupne prevody";
                    }
                }

                if (!(PoleUsporadanychJednotek[0][0].equals("nedostupne prevody"))) {
                    if (pocet_poli == 1){
                        if (!(hbox[3].getChildren().contains(tx[3]))){
                            hbox[3].getChildren().add(tx[3]);
                        }
                    }
                    for (int b = 1; b <= 4; b++) {
                        if (PoleUsporadanychJednotek[0][b] != null) {
                            tx[pocet_hboxu].setText(PoleUsporadanychJednotek[0][b]);
                            tx[pocet_hboxu].setFont(Font.font("Arial", 14));
                            pocet_hboxu = pocet_hboxu + 1;
                            for (int d = 3; d <= 10; d++) {
                                if (PoleUsporadanchHodnot[b][d] != null){
                                    tx[pocet_hboxu].setFont(Font.font("Arial", 13));
                                    tx[pocet_hboxu].setText(String.format("%.3f", PoleUsporadanchHodnot[b][d]));
                                    tx2[pocet_hboxu].setFont(Font.font("Arial", 13));
                                    tx2[pocet_hboxu].setText(PoleUsporadanychJednotek[b][d]);
                                    pocet_hboxu = pocet_hboxu + 1;
                                }
                            }
                        }
                    }
                }
                else{
                    tx[1].setText("Neplatný zápis!");
                }
            } else { // povolitpokracovani = 1
                povolitpokracovani = 0;
            }
        });


        Scene scene = new Scene(svisly_panel, 400, 560);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Převodník jednotek");
        primaryStage.show();

    }
    public static void NovyVyraz(Double[][] PoleUsporadanchHodnot, String[][] PoleUsporadanychJednotek, Hodnoty[] PoleZadanychHodnot, String jednotka, double vyraz, int pozice) {
        PoleZadanychHodnot[pozice]= new Hodnoty(vyraz, jednotka);
        PoleZadanychHodnot[pozice].ZapisDoPole(pozice, PoleUsporadanchHodnot, PoleUsporadanychJednotek);  // NECHÁME ZAPSAT DANOU JEDNOTKU DO POLE USPOŘÁDANÝCH HODNOT A JEDNOTEK
    }
    public void vymazatMtd(Text tx[], Text tx2[]){
        for (int b = pocet_poli + 2; b <= 24; b++) {
            if (!(tx[b].getText().isEmpty())) {
                tx[b].setText("");
            }
            if (b != 3 && !(tx2[b].getText().isEmpty())) {
                tx2[b].setText("");
            }
        }
        pocet_hboxu = pocet_poli + 2;
    }
    public static void main(String[] args) {
        launch(args);
    }
}