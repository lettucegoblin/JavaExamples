
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Cat {
    String catId;
    String catName;
    String owner;

    public Cat(String catId, String catName, String owner) {
        this.catId = catId;
        this.catName = catName;
        this.owner = owner;
    }

    public String getCatId() {
        return catId;
    }

    @Override
    public String toString() {
        return "Cat [catId=" + catId + ", catName=" + catName + ", owner=" + owner + "]";
    }
}

public class CatSearchComparison {

    public static void main(String[] args) {
        String csvFile = "Generated_Cat_Data_1mill.csv";  // Update with the actual CSV path
        List<Cat> catList = new ArrayList<>();
        Map<String, Cat> catMap = new HashMap<>();

        // Read CSV and populate both ArrayList and HashMap
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");  // Split CSV by comma
                String catId = values[0];
                String catName = values[1];
                String owner = values[2];
                Cat cat = new Cat(catId, catName, owner);

                catList.add(cat);  // Add to ArrayList
                catMap.put(catId, cat);  // Add to HashMap
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example cat_id to search
        String searchCatId = "t35LPMJB"; // Change this to a valid cat_id from your CSV

        // Search in ArrayList and measure time
        long startTimeArrayList = System.nanoTime();
        Cat resultInList = searchInArrayList(catList, searchCatId);
        long endTimeArrayList = System.nanoTime();
        long durationArrayList = endTimeArrayList - startTimeArrayList;

        // Search in HashMap and measure time
        long startTimeHashMap = System.nanoTime();
        Cat resultInMap = catMap.get(searchCatId);
        long endTimeHashMap = System.nanoTime();
        long durationHashMap = endTimeHashMap - startTimeHashMap;

        // Output the results
        System.out.println("Result from ArrayList search: " + resultInList);
        System.out.println("Time taken for ArrayList search: " + durationArrayList + " nanoseconds");

        System.out.println("Result from HashMap search: " + resultInMap);
        System.out.println("Time taken for HashMap search: " + durationHashMap + " nanoseconds");
    }

    // Method to search cat by ID in an ArrayList
    private static Cat searchInArrayList(List<Cat> catList, String catId) {
        for (Cat cat : catList) {
            if (cat.getCatId().equals(catId)) {
                return cat;
            }
        }
        return null;
    }
}
