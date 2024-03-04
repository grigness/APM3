package domain;

public class Weather {
    private int id;
    private int startHour;
    private int endHour;
    private int temperature;
    private int precipitationProbability;
    private String description;

    public Weather(int id,int startHour, int endHour, int temperature, int precipitationProbability, String description) {
        this.id=id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.temperature = temperature;
        this.precipitationProbability = precipitationProbability;
        this.description = description;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(int precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "startHour=" + startHour +
                ", endHour=" + endHour +
                ", temperature=" + temperature +
                ", precipitationProbability=" + precipitationProbability +
                ", description='" + description + '\'' +
                '}';
    }
}
