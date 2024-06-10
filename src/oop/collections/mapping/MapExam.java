package oop.collections.mapping;

import oop.collections.timetables.TableSubject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapExam {


    static HashMap<String, ArrayList<City>> countryCities;
    double rating;

    public MapExam() {
        rating = countryCities.get(City.population).stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        City.setRating(rating);
    }

    /**
     * Nacte vsechny prvky, nasledne namapuje
     */
    static HashMap<String, ArrayList<City>> loadMapCities(String filePath){
      HashMap<String, ArrayList<City>> map = new HashMap<>();
      //nacist mesta, vyparsovat jako objekt a ulozit do zadane mapy
        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.remove(0);//copa to asi udela...? :) (odstrani prvni radek)
            String[] params;
            City city;
            for (String line : lines){
                params = line.split(",");

                //doplnit parametry
                city = new City(params[1],params[2],Integer.parseInt(params[3]));
                //hodt do mapy (kod-mesto)
                map.put(params[0],params[1]);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
      return map;
    }

    public static void main(String[] args) {
        countryCities = loadMapCities("city.csv");

    }
}  class City{
    String name, district;
    int population;
    double rating;
    public void setRating(double rating) {
        this.rating = rating;
    }

    public City(String name, String district, int population) {
        this.name = name;
        this.district = district;
        this.population = population;
        this.rating =0;
    }


}
