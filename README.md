# car-lease-calculator
The Java files collectively implement a leasing cost calculation system for vehicles.
A system is formed that reads vehicle descriptions from a file, creates Vehicle objects with fuel and lease details, calculates CO2 emissions and fuel costs for each vehicle, and presents a summary of the vehicles' details and costs. The main purpose is to assist in estimating the environmental impact and financial aspects of leasing different vehicles based on their fuel types and lease terms.

Lease.java: 
- The Lease class represents the lease details of a vehicle.
- It encapsulates attributes such as the amount due at signing, lease length in months, monthly lease cost, and mileage allowance per month.
- It provides getter methods to access these attributes and implements the toString() method to generate a string representation of the lease details.
- The equals() method is implemented to compare Lease objects for equality based on their attributes.

Fuel.java: 
- The Fuel class represents the fuel details of a vehicle.
- It contains information about the fuel type (gas or electric), usage (miles per gallon or per kWh), and charger cost (for electric cars).
- It offers accessor methods to retrieve these details and implements the toString() method to generate a string representation of the fuel details.
- The equals() method is provided for comparing Fuel objects.
- Additionally, it defines constants for the CO2 emissions associated with gas combustion and electricity combustion.

RecursiveAppend.java: 
- This class provides a recursive method called appendNTimes() that appends a given string to itself a specified number of times.
- The method uses recursion to concatenate the original string to itself until the desired number of repetitions is achieved.

Vehicle.java: 
- The Vehicle class represents a specific vehicle with its name, fuel details (an instance of the Fuel class), lease details (an instance of the Lease class), and calculated attributes such as CO2 emissions, fuel cost, and total cost.
- It provides getter and setter methods for these attributes, implements the toString() method to generate a string representation of the vehicle details, and overrides the equals() method for comparing Vehicle objects based on their attributes.

LeasingCost.java: 
- This class contains various methods to compute CO2 emissions, fuel costs, and total lease costs for vehicles.

- It includes the following important methods:
  
    - createAllVehicles(String filename):
         - Reads vehicle descriptions from a file and creates an array of Vehicle objects based on theinformation provided.
         - It parses the file format to extract details like fuel type, name, due at signing, lease length, monthly cost, mileage
           allowance, and charger cost (for electric cars).

    - computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit):
          - Calculates the CO2 emissions produced by a vehicle during the lease based on the lease length, fuel usage, mileage allowance,               and CO2 emissions per unit of fuel (either per gallon or per kWh).

    - computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice):
          - Computes the fuel cost for a vehicle during the lease period.
          - The calculation depends on the lease length, fuel usage, mileage allowance, and the price of fuel (either per gallon or per                 kWh).

    - computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost):
          - Calculates the total lease cost for a vehicle, taking into account the due amount at signing, lease length in months, and    
            monthly lease cost.

    - computeCO2EmissionsAndCost(Vehicle[] vehicles, double gasPrice, double electricityPrice): 
          - Iterates over the array of Vehicle objects and computes the CO2 emissions and fuel costs for each vehicle.
          - It uses the computeCO2() and computeFuelCost() methods internally.
          - The computed values are then assigned to the corresponding attributes of each Vehicle object.

    - main(String[] args):
          - The entry point of the program.
          - It accepts command-line arguments specifying the filename of the vehicle descriptions file, the price of gas per gallon, and                the price of electricity per kWh.
          - It calls the createAllVehicles() and computeCO2EmissionsAndCost() methods, and finally prints the details of each Vehicle       
            object.

