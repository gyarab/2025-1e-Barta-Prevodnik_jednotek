package org.example.prevodnikjednotek;

public class Hodnoty {
    Double Hodnota;
    String Jednotka;
    int Poradi;
    String Skupina;

    public Hodnoty(Double hodnota, String jednotka) {
        Hodnota = hodnota;
        Jednotka = jednotka;
    }

    public void ZapisDoPole(int i, Double[][] PoleUsporadanychHodnot, String[][] PoleUsporadanychJednotek) {  // HLAVNÍ PROGRAM - ZAPÍŠE HODNOTU I VŠECHNY OSTATNÍ DO POLE
        PriradPoradi();      // PŘIŘADÍ POŘADÍ
        PoleUsporadanychHodnot[i][this.Poradi] = this.Hodnota;      // PODLE POŘADÍ PŘIŘADÍ HODNOTU DO SPRÁVNÉHO POŘADÍ V POLI
        PoleUsporadanychJednotek[i][this.Poradi] = this.Jednotka;  // PODLE POŘADÍ PŘIŘADÍ JEDNOTKU DO SPRÁVNÉHO POŘADÍ V POLI
        PriradSkupinu();                            // ZJISTÍ SKUPINU PODLE JEDNOTKY

        if (this.Skupina.equals("Délka")) {
            for (int d = 3; d <= 9; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(10, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                if (d == 6) {
                    d = d + 2;
                }
            }
        } else if (this.Skupina.equals("Hmotnost")) {
            for (int d = 3; d <= 9; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(10, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                if (d == 6) {
                    d = d + 2;
                } else if (d == 4) {
                    d = d + 1;
                }
            }
        } else if (this.Skupina.equals("Síla") || this.Skupina.equals("Výkon") || this.Skupina.equals("Práce")) {
            for (int d = 5; d <= 8; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(1000, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
            }
        } else if (this.Skupina.equals("Čas")) {
            for (int d = 3; d <= 9; d++) {
                if (d != 6 && d != 7 && d != 8) {
                    if (this.Poradi != 9) {
                        if (d == 9) {
                            PoleUsporadanychHodnot[i][d] = PoleUsporadanychHodnot[i][5] / 24;
                            PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                        } else { // d != 9 && this.Poradi != 9
                            PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(60, (this.Poradi - d));
                            PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                        }
                    } else { // this.Poradi = 9
                        if (d != 9) {
                            PoleUsporadanychHodnot[i][d] = this.Hodnota * 24 * Math.pow(60, (this.Poradi - (d + 4)));
                            PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                        }
                    }
                }
            }
        }else if (this.Skupina.equals("Rychlost")) {
            for (int d = 7; d <= 8; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(3.6, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
            }
        }
        if (this.Skupina.equals("Obsah")) {
            for (int d = 3; d <= 9; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(100, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
            }
        }
        if (this.Skupina.equals("Objem")) {
            for (int d = 3; d <= 9; d++) {
                PoleUsporadanychHodnot[i][d] = this.Hodnota * Math.pow(1000, (this.Poradi - d));
                PoleUsporadanychJednotek[i][d] = PriradJednotku(d);
                if (d == 6) {
                    d = d + 2;
                }
            }
        }
    }

    public void PriradPoradi() {
        if (this.Jednotka.equals("km") || this.Jednotka.equals("t") || this.Jednotka.equals("den") || this.Jednotka.equals("km²") || this.Jednotka.equals("km³")) {
            this.Poradi = 9;
        } else if (this.Jednotka.equals("MN") || this.Jednotka.equals("MW") || this.Jednotka.equals("MJ") || this.Jednotka.equals("m/s") || this.Jednotka.equals("ha")) {
            this.Poradi = 8;
        } else if (this.Jednotka.equals("kN") || this.Jednotka.equals("kW") || this.Jednotka.equals("kJ") || this.Jednotka.equals("km/h") || this.Jednotka.equals("a")) {
            this.Poradi = 7;
        } else if (this.Jednotka.equals("m") || this.Jednotka.equals("kg") || this.Jednotka.equals("N") || this.Jednotka.equals("W") || this.Jednotka.equals("J") || this.Jednotka.equals("m²") || this.Jednotka.equals("m³")) {
            this.Poradi = 6;
        } else if (this.Jednotka.equals("dm") || this.Jednotka.equals("hg") || this.Jednotka.equals("mN") || this.Jednotka.equals("mW") || this.Jednotka.equals("mJ") || this.Jednotka.equals("hod") || this.Jednotka.equals("dm²") || this.Jednotka.equals("dm³")) {
            this.Poradi = 5;
        } else if ((this.Jednotka.equals("cm")) || (this.Jednotka.equals("dag")) || this.Jednotka.equals("min") || this.Jednotka.equals("cm²") || this.Jednotka.equals("cm³")) {
            this.Poradi = 4;
        } else if (this.Jednotka.equals("mm") || (this.Jednotka.equals("g")) || this.Jednotka.equals("s") || this.Jednotka.equals("mm²") || this.Jednotka.equals("mm³")) {
            this.Poradi = 3;
        }
    }

    public void PriradSkupinu() {
        if (this.Jednotka.equals("km") || this.Jednotka.equals("m") || this.Jednotka.equals("dm") || this.Jednotka.equals("cm") || this.Jednotka.equals("mm")) {
            this.Skupina = "Délka";
        } else if (this.Jednotka.equals("t") || this.Jednotka.equals("kg") || this.Jednotka.equals("dag") || this.Jednotka.equals("g")) {
            this.Skupina = "Hmotnost";
        } else if (this.Jednotka.equals("MN") || this.Jednotka.equals("kN") || this.Jednotka.equals("N") || this.Jednotka.equals("mN")) {
            this.Skupina = "Síla";
        } else if (this.Jednotka.equals("MW") || this.Jednotka.equals("kW") || this.Jednotka.equals("W") || this.Jednotka.equals("mW")) {
            this.Skupina = "Výkon";
        } else if (this.Jednotka.equals("MJ") || this.Jednotka.equals("kJ") || this.Jednotka.equals("J") || this.Jednotka.equals("mJ")) {
            this.Skupina = "Práce";
        } else if (this.Jednotka.equals("s") || this.Jednotka.equals("min") || this.Jednotka.equals("hod") || this.Jednotka.equals("den")) {
            this.Skupina = "Čas";
        } else if (this.Jednotka.equals("m/s") || this.Jednotka.equals("km/h")) {
            this.Skupina = "Rychlost";
        } else if (this.Jednotka.equals("km²") || this.Jednotka.equals("m²") || this.Jednotka.equals("dm²") || this.Jednotka.equals("cm²") || this.Jednotka.equals("mm²") || this.Jednotka.equals("a") || this.Jednotka.equals("ha")) {
            this.Skupina = ("Obsah");
        } else if (this.Jednotka.equals("km³") || this.Jednotka.equals("m³") || this.Jednotka.equals("dm³") || this.Jednotka.equals("cm³") || this.Jednotka.equals("mm³")) {
            this.Skupina = ("Objem");
        }
    }

    public String PriradJednotku(int ZadanePoradi) {
        if (ZadanePoradi == 9) {
            if (this.Skupina.equals("Délka")) {
                return "km";
            } else if (this.Skupina.equals("Hmotnost")) {
                return "t";
            } else if (this.Skupina.equals("Čas")) {
                return "den";
            } else if (this.Skupina.equals("Obsah")) {
                return "km²";
            } else {
                return "km³";
            }
        } else if (ZadanePoradi == 8) {
            if (this.Skupina.equals("Síla")) {
                return "MN";
            } else if (this.Skupina.equals("Výkon")) {
                return "MW";
            } else if (this.Skupina.equals("Práce")) {
                return "MJ";
            } else if (this.Skupina.equals("Rychlost")) {
                return "m/s";
            } else {
                return "ha";
            }
        } else if (ZadanePoradi == 7) {
            if (this.Skupina.equals("Síla")) {
                return "kN";
            } else if (this.Skupina.equals("Výkon")) {
                return "kW";
            } else if (this.Skupina.equals("Práce")) {
                return "kJ";
            } else if (this.Skupina.equals("Rychlost")) {
                return "km/h";
            } else {
                return "a";
            }
        } else if (ZadanePoradi == 6) {
            if (this.Skupina.equals("Délka")) {
                return "m";
            } else if (this.Skupina.equals("Hmotnost")) {
                return "kg";
            } else if (this.Skupina.equals("Síla")) {
                return "N";
            } else if (this.Skupina.equals("Výkon")) {
                return "W";
            } else if (this.Skupina.equals("Práce")) {
                return "J";
            } else if (this.Skupina.equals("Obsah")) {
                return "m²";
            } else {
                return "m³";
            }
        } else if (ZadanePoradi == 5) {
            if (this.Skupina.equals("Délka")) {
                return "dm";
            } else if (this.Skupina.equals("Hmotnost")) {
                return "hg";
            } else if (this.Skupina.equals("Síla")) {
                return "mN";
            } else if (this.Skupina.equals("Výkon")) {
                return "mW";
            } else if (this.Skupina.equals("Práce")) {
                return "mJ";
            } else if (this.Skupina.equals("Čas")) {
                return "hod";
            } else if (this.Skupina.equals("Obsah")) {
                return "dm²";
            } else {
                return "dm³";
            }
        } else if (ZadanePoradi == 4) {
            if (this.Skupina.equals("Délka")) {
                return "cm";
            } else if (this.Skupina.equals("Hmotnost")) {
                return "dag";
            } else if (this.Skupina.equals("Čas")) {
                return "min";
            } else if (this.Skupina.equals("Obsah")) {
                return "cm²";
            } else {
                return "cm³";
            }
        } else if (ZadanePoradi == 3) {
            if (this.Skupina.equals("Délka")) {
                return "mm";
            } else if (this.Skupina.equals("Hmotnost")) {
                return "g";
            } else if (this.Skupina.equals("Čas")) {
                return "s";
            } else if (this.Skupina.equals("Obsah")) {
                return "mm²";
            } else {
                return "mm³";
            }
        } else {
            return null;
        }
    }
}