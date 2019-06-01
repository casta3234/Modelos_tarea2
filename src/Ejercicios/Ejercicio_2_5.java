package Ejercicios;



import java.io.IOException;
import java.*;
import simlib.collection.*;
import simlib.elements.*;
import simlib.io.*;
import static simlib.SimLib.*;



public class Ejercicio_2_5{

    static final int  STREAM_ARRIVE = 5, STREAM_DEPARTURE = 7,
            STREAM_ELECTION_BERTH = 51;
    static final byte EVENT_ARRIVAL = 1, EVENT_DEPARTURE = 2, EVENT_END_SIMULATION = 3;
//    static  float PERSON_ARRIVAL = 10, TIME_SWIMMING = 0.5,
//            POOL_CAPACITY = 6, LENGTH_SIMULATION = 8;
    static float PERSON_ARRIVAL, TIME_swimming,POOL_CAPACITY , LENGTH_SIMULATION;
    static float time_of_3_person_swimming, time_of_not_3_person_swimming,average_of_3_person_swimming,
            time_last_event,swimmers_average,number_of_person_arrive,number_of_person_enter_the_pool,
            percentage_of_person_entering_the_pool;
    
    static SimReader reader;
    static SimWriter writer;
    static Store pool;
    static boolean aux = false;
    

    public static void main(String[] args) throws IOException {

        reader = new SimReader("Ejercicio_2_5.in");
        writer = new SimWriter("Ejercicio_2_5.out");        
        time_of_3_person_swimming=0;
        time_of_not_3_person_swimming=0;
        time_last_event=0;
        swimmers_average=0;
        number_of_person_arrive=0;
        number_of_person_enter_the_pool=0;
        percentage_of_person_entering_the_pool=0;
        PERSON_ARRIVAL= reader.readFloat();
        TIME_swimming = reader.readFloat();
        POOL_CAPACITY = reader.readFloat();
        LENGTH_SIMULATION = reader.readFloat();
        pool = new Store("pool", (long) POOL_CAPACITY);
//        System.out.println("                 HARBOR SYSTEM          ");
//        System.out.println("------------------------------------------------");
//        System.out.println("                   DATA INPUT           ");
//        System.out.println("------------------------------------------------");
//        System.out.println("   Mean Arrival:                    " + MEAN_ARRIVAL + "   ");
//        System.out.println("   Min Time Unloaded:               " + MIN_TIME_UNLOADED + "    ");
//        System.out.println("   Max Time Unloaded:               " + MAX_TIME_UNLOADED + "    ");
//        System.out.println("   Length simulation:               " + LENGTH_SIMULATION + "   ");
//        System.out.println("------------------------------------------------");
       
        writer.write("                 Pool System          " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("                   DATA INPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   Person Arrival:                    " + PERSON_ARRIVAL + "   " + "\n");
        writer.write("   Time Swimming:               " + TIME_swimming + "    " + "\n");
        writer.write("   Pool Capacity:               " + POOL_CAPACITY + "    " + "\n");
        writer.write("   Length simulation:               " + LENGTH_SIMULATION + "   " + "\n");
        writer.write("------------------------------------------------" + "\n");
        initSimlib();

        ///Primer barco
        eventSchedule(poisson(PERSON_ARRIVAL, STREAM_ARRIVE), EVENT_ARRIVAL);
        // Fin simulacion
        eventSchedule(LENGTH_SIMULATION, EVENT_END_SIMULATION);

        do {
            timing();
            swimmers_average += pool.getused()*(simTime-time_last_event);
            switch (eventType) {
                case EVENT_ARRIVAL:
                    time_last_event=simTime;
                    arrive();
                    break;
                case EVENT_DEPARTURE:
                    time_last_event=simTime;
                    depart();
                    break;
                case EVENT_END_SIMULATION:
                    time_last_event=simTime;
                    report();
                    break;
            }
        } while (eventType != EVENT_END_SIMULATION);

        reader.close();
        writer.close();
    }

    static void arrive() {
        eventSchedule(simTime + poisson(PERSON_ARRIVAL, STREAM_ARRIVE),
                EVENT_ARRIVAL);
        
        number_of_person_arrive+=1;
        if(pool.getused()==3){
            aux = true;
            }
        else{
            aux=false;
        }
            
        if(pool.hasSpace()){
            
            pool.enter();
            number_of_person_enter_the_pool+=1;
            eventSchedule(simTime + expon(TIME_swimming, STREAM_DEPARTURE), EVENT_DEPARTURE);
       
            if(pool.getused()==3){
                time_of_3_person_swimming += simTime;
                
            }
            if(aux==true){
                time_of_not_3_person_swimming+=simTime;
            }
        }
        System.out.println(pool.getused());
        
        
 
    }

    static void depart() {
        if(pool.getused()==3){
                time_of_not_3_person_swimming += simTime;
                
            }
        pool.leave();
        if(pool.getused()==3){
                time_of_3_person_swimming += simTime;
                
            }
        System.out.println(pool.getused());
    }
    
    static void report() throws IOException{
        average_of_3_person_swimming = ((time_of_not_3_person_swimming - time_of_3_person_swimming)/simTime)*100;
        swimmers_average= swimmers_average/simTime;
        percentage_of_person_entering_the_pool = (number_of_person_enter_the_pool/number_of_person_arrive)*100;
        
        writer.write("                   DATA OUTPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   average_of_3_person_swimming :                 " + average_of_3_person_swimming+ "%"  + "\n" + "\n");
        writer.write("   swimmers_average:                   " + swimmers_average + "\n");
        writer.write("   percentage_of_person_entering_the_pool:                   " + percentage_of_person_entering_the_pool + "%"+ "\n");
    }
    


}
