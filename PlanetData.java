public class PlanetData {

    private final String name;
    private final String mass;
    private final String distance;
    private final String speed;

    public PlanetData(String name, String mass, String distance, String speed) {
        this.name = name;
        this.mass = mass;
        this.distance = distance;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getMass() {
        return mass;
    }

    public String getDistance() {
        return distance;
    }

    public String getSpeed() {
        return speed;
    }
}