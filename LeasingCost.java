public class LeasingCost {
    
    /* 
     * Parameters:
     *      filename: the file name containing the vehicles description
     *
     * File format:
     *      the first line of the file containing an integer representing how many vehicles will be described 
     *      in the following lines.
     *      Each line other than the first line describes one vehicle; 
     *      7 or 8 fragments of data in randomly placed order describing the vehicle (7 for gas car, 8 for electric car) in each line. 
     *      Each fragment end with the ';' character
     * 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *      type:gas; name:civic; due:1000; length:3; monthly:295; mile/unit:34; 
     *
     * Returns:
     *      this method returns an array of Vehicle objects 
     */
	public static Vehicle[] createAllVehicles(String filename) {
        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt( StdIn.readLine() );
        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for ( int i = 0; i < numberOfCars; i++ ) {
        	String line = StdIn.readLine();
            vehicles[i] = createVehicle(line);
        }
        return vehicles;
    }

    /* 
     * Description:
     *      This method calculates the CO2 emission given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		co2PerUnit:
     *			kg of CO2 released with the combustion of each gallon of gasoline, or
     *			kg of CO2 are emitted to generate 1 kWh on average
     * Returns:
     *      this method returns a double representing the CO2 emission produced by the car during the lease.
     */
	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){
		double miles = numberOfMonth * mileageAllowance ;
		return miles/usage*co2PerUnit;
    }

    /* 
     * Description:
     *      This method calculates the cost the fuel during the lease given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		fuelPrice: 
     *			price of 1 kWh in cents of a dollar,  if fuelType is GAS, or
     *			price of one gallon of gas in dollars if fuelType is ELECTRIC
     * Returns:
     *      this method returns a double representing the fuel cost during the lease
     */
	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){
    	double miles = numberOfMonth * mileageAllowance;
        double cost = miles/usage * fuelPrice;
    	return cost;
    }

	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){
    	
        return dueAtSigning + numberOfMonths * monthlyCost;
    }

    /* 
     * Description:
     *      This method creates and returns an Vehicle object with name, Lease, and Fuel properly populated based on the given string
     *      
     * Parameters:
     *      one string containing 7~8 fragments descrbing the 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *          type:gas.name:civic.due:1000.length:3.monthly:295.mile/unit:34.mileageAllowance:1200.
     *          monthly:238.name:Bolt.due:1000.mileageAllowance:20000.length:15.mile/unit:50.type:electric.charger:500.
     * Returns:
     *      this method creates and returns an Vehicle object with name, Lease, and Fuel properly populated
     *
     */
	public static Vehicle createVehicle(String description){
        
        String[] arr = description.replaceAll("\\s","").split("\\;");

        int fuelType = 0;
        double usage = 0;
        String name = "";
        double due = 0;
        int length = 0;
        double monthly = 0;
        int allowance = 0;
        double chargerC = 0;


      for (int i = 0; i < arr.length; i++) {
        String val = arr[i].substring(0, 5);

        if (val.charAt(0) == 't') {
            String fT = arr[i].substring(arr[i].lastIndexOf(":") + 1);
            if (fT.equalsIgnoreCase("gas")) {
              fuelType = 1;
            } else {
              fuelType = 2;
            }
        }

        if (val.charAt(0) == 'n') {
            String n = arr[i].substring(arr[i].lastIndexOf(":") + 1);
            name = n;
        }

        if (val.charAt(0) == 'd') {
          String d = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          due = Double.parseDouble(d);
        }

        if (val.charAt(0) == 'l') {
          String l = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          length = Integer.parseInt(l);
        }

        if( val.charAt(1) == 'o' ) {
          String mo = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          monthly = Double.parseDouble(mo);
        }

        if (val.charAt(4) == '/') {
          String u = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          usage = Double.parseDouble(u);
        }

        if (val.charAt(0) == 'a') {
          String mA = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          allowance = Integer.parseInt(mA);
        }

        if (val.charAt(0) == 'c') {
          String cC = arr[i].substring(arr[i].lastIndexOf(":") + 1);
          chargerC = Double.parseDouble(cC);
        }
      }

     if (fuelType == 1) {
       //gas
       Fuel fuel1 = new Fuel(usage);
       Lease lease1 = new Lease(due, length, monthly, allowance);

       Vehicle car = new Vehicle(name, fuel1, lease1);

       return car;

     } else if (fuelType == 2) {
       //electric
       Fuel fuel1 = new Fuel(usage, chargerC);
       Lease lease1 = new Lease(due, length, monthly, allowance);

       Vehicle car = new Vehicle(name, fuel1, lease1);

       return car;

     }

     return null;
	}

	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice ){
	   
        for (int i = 0; i < vehicles.length; i++) {


            if (vehicles[i].getFuel().getType() == 1){
              
              vehicles[i].setCO2Emission( computeCO2( vehicles[i].getLease().getLeaseLength(),
                     vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(),Fuel.CO2EMITTED_GASCOMBUSTION));
              
              vehicles[i].setFuelCost( computeFuelCost( vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(),
                    vehicles[i].getLease().getMileageAllowance(), gasPrice));
              
              vehicles[i].setTotalCost( computeLeaseCost( vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(),
                    vehicles[i].getLease().getMonthlyCost()) + computeFuelCost( vehicles[i].getLease().getLeaseLength(),
                    vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), gasPrice));
  
            } else {
  
              vehicles[i].setCO2Emission( computeCO2( vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(),
                    vehicles[i].getLease().getMileageAllowance(), Fuel.CO2EMITTED_ELECTRICITYCOMBUSTION));
  
              vehicles[i].setFuelCost( computeFuelCost( vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(),
                     vehicles[i].getLease().getMileageAllowance(), electricityPrice));
  
              vehicles[i].setTotalCost(vehicles[i].getFuel().getCharger() + computeLeaseCost(vehicles[i].getLease().getDueAtSigning(),
                 vehicles[i].getLease().getLeaseLength(), vehicles[i].getLease().getMonthlyCost())
                  + computeFuelCost(vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(),
                   vehicles[i].getLease().getMileageAllowance(), electricityPrice));
            }
  
          }
    	}

	public static void main (String[] args) {
        String filename = args[0];
        double gasPrice = Double.parseDouble( args[1] );
		double electricityPrice = Double.parseDouble( args[2] );

		Vehicle[] vehicles = createAllVehicles(filename); 
		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for (Vehicle v : vehicles ) 
            System.out.println(v.toString());
    }
}

/**
  * How to compile:
  *     javac LeasingCost.java
  * How to run:         
  *     java LeasingCost vehicles.txt 3.85 11.0
  **/
