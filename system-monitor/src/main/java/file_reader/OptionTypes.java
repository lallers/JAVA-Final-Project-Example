package file_reader;

public class OptionTypes {

    public static class Animals {
        String animal;
        String name;
        Integer age;
        String health;
        String feedingSchedule;
        final String animalPrefix = "Animal\\s-\\s";
        final String namePrefix = "Name:\\s";
        final String agePrefix = "Age:\\s";
        final String healthPrefix = "Feeding schedule:\\s";

        // public String healthPrompt() {
        // return "*****" + this.healthPrefix;
        // }
    }

    public static class Habitats {
        String habitat;
        String temperature;
        String food;
        String cleanliness;
        final String habitatPrefix = "Habitat\\s-\\s";
        final String temperaturePrefix = "Temperature:\\s";
        final String foodPrefix = "Food source:\\s";
        final String cleanlinessPrefix = "Cleanliness:\\s";

        // public String buildPrompt(String option) {
        // return "*****" + this.option;
        // }
    }
}
/*
 * Details on penguin habitat Details on bird house Details on aquarium
 * 
 * Habitat - Penguin Temperature: Freezing Food source: Fish in water running
 * low Cleanliness: Passed
 * 
 * Habitat - Bird Temperature: Moderate Food source: Natural from environment
 * Cleanliness: Passed
 * 
 * Habitat - Aquarium Temperature: Varies with output temperature Food source:
 * Added daily Cleanliness: Needs cleaning from algae }
 */