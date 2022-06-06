import java.util.ArrayList;
import java.util.List;

public class CarDealer {

    private static final int RECIEVE_TIME = 3000;
    private static final int SELL_TIME = 1000;
    private static final int CARS = 10;
    private final List<Car> cars = new ArrayList<>();


    public void receiveCar(){
        for (int i = 0; i < CARS; i++){
            try{
                Thread.sleep(RECIEVE_TIME);
                cars.add(new Car("Toyota", "Camry"));
                System.out.println(Thread.currentThread().getName() + " выпустил 1 " + cars.get(0));
                synchronized (this){
                    notify();
                }
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    public synchronized void sellCar(){
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0){
                System.out.println("Машин нет!");
                wait();
            }
            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новенькой " + cars.get(0));
            cars.remove(0);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
