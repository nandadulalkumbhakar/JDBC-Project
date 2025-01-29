package org.example;

import java.util.ArrayList;
import java.util.List;

class Car {
    private String licencePlate;

    public Car(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}
class ParkingSport
{
    private int spotNumber;
    private boolean available;
    private Car car;

    public ParkingSport(int spotNumber)
    {
        this.spotNumber=spotNumber;//in inicial time num of space
        this.available=true;//inicial time avalable space
        this.car=null;//inicial time num of store car
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public Car getCar() {
        return car;
    }
    public void occupy(Car car )
    {
        this.car=car;
        this.available=false;
    }
    public void vacate()//empty
    {
        this.car=null;
        this.available=true;
    }
}

class ParkingLot
{
    private List<ParkingSport> sports;

    public ParkingLot(int capacity) {
        this.sports = new ArrayList<>();
        for(int i=0;i<capacity;i++)
        {
            sports.add(new ParkingSport(i));
        }
    }

    public boolean parkCar(Car car)//here check car is parking successfully or not
    {
        for(ParkingSport spot: sports)
        {
            if(spot.isAvailable())
            {
                spot.occupy(car);
                System.out.println("car with number: "+car.getLicencePlate()+"parked at spot number: "+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("Sorry parking is full");
        return false;
    }

    public boolean removeCar (String licensePlate)
    {
        for(ParkingSport spot: sports)
        {
            if(!spot.isAvailable() && spot.getCar().getLicencePlate().equalsIgnoreCase(licensePlate));
            {
                spot.vacate();
                System.out.println("car with number: " + licensePlate + " removed from parking and spot num "+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("car with number "+licensePlate+"Not found");
        return false;
    }

}


class Test{
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot(5);//num of car
        Car car1=new Car("UP807373");//car parling
        Car car2=new Car("DL837273");
        Car car3=new Car("MP841732");
        Car car4=new Car("WB841732");
        Car car5=new Car("JH841732");
        Car car6=new Car("WB671732");


        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);
        parkingLot.parkCar(car4);
        parkingLot.parkCar(car5);
        parkingLot.parkCar(car6);

        parkingLot.removeCar("UP807673");
    }
}


