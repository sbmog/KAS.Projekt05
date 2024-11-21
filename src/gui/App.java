package gui;

import application.controller.Controller;
import application.model.Konference;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();

    }

    public static void initStorage(){
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel","Odense Universitet", LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),350);
        Controller.createHotel("Den Hvide Svane","XX",1050,1250,0,50,0,havOgHimmel);
        Controller.createHotel("Høtel Phønix","XX",700,800,200,75,0,havOgHimmel);
        Controller.createHotel("Pension Tusindfryd","XX",500,600,0,0,100,havOgHimmel);
    }
}
