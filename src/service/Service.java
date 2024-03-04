package service;

import domain.Weather;
import repository.Repository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Weather> getWeathersSortedByStartingHour(){
        return (ArrayList<Weather>) repo.getWeathers().stream()
                .sorted(Comparator.comparing(Weather::getStartHour))
                .collect(Collectors.toList());
    }

    public ArrayList<Weather> getSortedByDescription(String description){
        return (ArrayList<Weather>) repo.getWeathers().stream()
                .filter(w-> Objects.equals(w.getDescription(),description))
                .collect(Collectors.toList());
    }
    public ArrayList<Weather> getSortedByDescriptionAndStartingHour(String description) {

        return (ArrayList<Weather>) repo.getWeathers().stream()
                .filter(weather -> weather.getDescription().equals(description))
                .sorted(Comparator.comparing(Weather::getDescription)
                        .thenComparing(Weather::getStartHour))
                .collect(Collectors.toList());
    }
    /*
    public List<String> getAllDescriptions(){
        List<Weather> weathers=repo.getWeathers();
        List<String> descriptions = new ArrayList<>();
        for (Weather w : weathers) {
            descriptions.add(w.getDescription());
        }
        return descriptions;
    }

     */
    public List<String> getAllUniqueDescriptions() {
        List<Weather> weathers = repo.getWeathers();

        // Use a Set to store unique organisms
        Set<String> uniqueDescriptions = new HashSet<>();

        for (Weather w : weathers) {
            String description = w.getDescription();

            // Extract the first word from the organism and add it to the set
            String[] words = description.split("\s+");
            if (words.length > 0) {
                uniqueDescriptions.add(words[0]);
            }
        }

        // Convert the Set to a List to maintain order (if order matters)
        List<String> result = new ArrayList<>(uniqueDescriptions);
        return result;
    }
    public void updateWeather(Weather weather, int precipitation, String description){
        weather.setPrecipitationProbability(precipitation);
        weather.setDescription(description);
        try {
            this.repo.update(weather);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Weather> getWeathers(){
        return repo.getWeathers();

    }
    public ArrayList<Weather> getWeatherbyDescription(String description){
        return (ArrayList<Weather>) repo.getWeathers().stream()
                .filter(r -> Objects.equals(r.getDescription(), description))
                .collect(Collectors.toList());
    }
}
