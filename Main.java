//Xandra Leal
//3/7/2026


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main{


    
    public static void main(String[] args) throws InterruptedException{
        //Defining ArrayList and Scanner
        boolean isSorted = false;
        ArrayList<Car> cars = new ArrayList<>();

        //Calls a function that loads the cars to the from the csv to ArrayList cars
        loadCars(cars);
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));
        Scanner userInput = new Scanner(System.in);


        //Start of UI
        while(true){
        System.out.println();
        System.out.println("Please select an option:\n 1: Sort \n 2: Search by brand\n 3: Stats\n 4: Exit");

        //Try-catch in case user puts in wrong inputs
       int option = 0;
       try{ 
        option = userInput.nextInt();

       } catch(Exception e) {
        System.out.println("Invalid input. Please enter a number between 1 and 4.");
        userInput.nextLine(); 
        continue;
       }
        //UI, lets the user choose between Sort, Search, Stats, and Exit. Search and stats are dependent on eachother, Stats and exit are independent.
        switch(option) {
            case 1:
                Sort(working);
                isSorted = true;
                break;
            case 2:

                if(isSorted == true){
                System.out.println("Enter the brand to search for:");
                String key2 = userInput.next();
                search(working, key2);
                //sets isSorted to false and assigns arrayList to original so user will have to resort in order to call this again
                working = new ArrayList<>(cars.subList(0, 2000));
                isSorted = false;
                break;
                } else System.out.println(); System.out.println("Please sort first");
                Thread.sleep(500);
                break;
            case 3:
                stats(working);
                break;
            case 4:
                System.out.println("Exiting program");
                userInput.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please select 1, 2, or 3.");
        }

    }

    }

    //Loads cars from csv file to ArrayList cars, seperates the data into 7 variables, and adds each instance created and prints it.
public static void loadCars(ArrayList<Car> cars){
      int carCount = 0;

     try (BufferedReader reader = new BufferedReader(new FileReader("Car_Data.csv"))) {
            String line = reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    carCount++;
                    String carID = data[0];
                    String make = data[1];
                    String model = data[2];
                    int year = Integer.parseInt(data[3]);
                    String fuelType = data[4];
                    String color = data[5];
                    double mileageKmpl = Double.parseDouble(data[6]);
                    
                    Car car = new Car(carID, make, model, year, fuelType, color, mileageKmpl);
                    cars.add(car);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    System.out.println(carCount + " cars loaded.");



}

//Sorts the ArrayList working by brand using insertion sort, and prints the first 10 cars after sorting.
public static void Sort(ArrayList<Car> working){

     System.out.println("First 10 cars after sorting:");

    int n = working.size();
    for (int i = 1; i < n; i++) {
        Car key = working.get(i);
        int j = i - 1;
        while (j >= 0 && working.get(j).getBrand().compareToIgnoreCase(key.getBrand()) > 0) {
            working.set(j + 1, working.get(j));
            j = j - 1;
        }
        working.set(j + 1, key);
    }

    for (int i = 0; i < 10; i++) {
        System.out.println(working.get(i).toString());
    }

}
//Uses Binary search to find the car brand the user inputs and prints it. Removes the car brand from list so it wont repeat.
public static void search(ArrayList<Car> working, String key2) {
        
           
            int low = 0, high = working.size() - 1;
            while(low <= high) {
                int mid = low + (high - low) / 2;
                Car midCar = working.get(mid);
                int cmp = midCar.getBrand().compareToIgnoreCase(key2);
                if (cmp == 0) {
                    System.out.println("Car found: " + midCar.toString());
                 
                    working.remove(mid);

                    high = working.size() - 1;
                  
                    
                    
                } else if (cmp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }   

          
         
        

     

         }

//Calculations to find the stats of the cars, such as average mileage and count of each fuel type, and prints them.
public static void stats(ArrayList<Car> working){
int mileageSum = 0;
int mileageAvg = 0;
for(int i = 0; i < working.size(); i++) {
     double mileage = working.get(i).getMileageKmpl();
     mileageSum += mileage;
     mileageAvg = mileageSum / working.size();
}   

System.out.println("Average mileage: " + mileageAvg + " kmpl");

int petrolCount = 0;
int dieselCount = 0;
int electricCount = 0;
int hybridCount = 0;

for(int i = 0; i < working.size(); i++) {
    String fuelType = working.get(i).getFuelType();
    if(fuelType.equalsIgnoreCase("Petrol")) {
        petrolCount++;
    } else if(fuelType.equalsIgnoreCase("Diesel")) {
        dieselCount++;
    } else if(fuelType.equalsIgnoreCase("Electric")) {
        electricCount++;
    } else if(fuelType.equalsIgnoreCase("Hybrid")) {
        hybridCount++;
    }

}

System.out.println("Petrol cars: " + petrolCount);
System.out.println("Diesel cars: " + dieselCount);
System.out.println("Electric cars: " + electricCount);
System.out.println("Hybrid cars: " + hybridCount);


}


}
    









    



