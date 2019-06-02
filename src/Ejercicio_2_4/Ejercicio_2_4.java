package Ejercicio_2_4;



import java.io.IOException;
import java.*;
import simlib.collection.*;
import simlib.elements.*;
import simlib.io.*;
import static simlib.SimLib.*;



public class Ejercicio_2_4{

    static final int  STREAM_ARRIVE = 5, STREAM_DEPARTURE = 7,
            STREAM_ELECTION_BERTH = 51;
    static final byte EVENT_ARRIVAL_A = 1,EVENT_ARRIVAL_B = 2,EVENT_ARRIVAL_C = 3, EVENT_DEPARTURE = 4,EVENT_PATH = 5 ,EVENT_END_SIMULATION = 6;
//    static  float PERSON_ARRIVAL = 10, TIME_SWIMMING = 0.5,
//            POOL_CAPACITY = 6, LENGTH_SIMULATION = 8;
    static float ELEVATOR_CAPACYTI,WEIGHT_MATERIAL_A,WEIGHT_MATERIAL_B,WEIGHT_MATERIAL_C,MATERIAL_A_ARRIVAL_1,MATERIAL_A_ARRIVAL_2,
            MATERIAL_B_ARRIVAL,MATERIAL_C_ARRIVAL_1,MATERIAL_C_ARRIVAL_2,DELIVERY_OF_THE_ELEVATOR, LENGTH_SIMULATION;
    static float arrive_material_A,leave_material_A;
    
    static SimReader reader;
    static SimWriter writer;
    static ArrayList queue;
    static ArrayList elevator;
    static boolean elevator_in_floor_1;
    
    public static void main(String[] args) throws IOException {

        reader = new SimReader("Ejercicio_2_5.in");
        writer = new SimWriter("Ejercicio_2_5.out");        
        queue = new ArrayList<>("queue");
        elevator = new ArrayList<>("elevator");
        elevator_in_floor_1=true;
        ELEVATOR_CAPACYTI= reader.readFloat();
        WEIGHT_MATERIAL_A = reader.readFloat();
        WEIGHT_MATERIAL_B = reader.readFloat();
        WEIGHT_MATERIAL_C = reader.readFloat();
        MATERIAL_A_ARRIVAL_1 = reader.readFloat();
        MATERIAL_A_ARRIVAL_2 = reader.readFloat();
        MATERIAL_B_ARRIVAL = reader.readFloat();
        MATERIAL_C_ARRIVAL_1 = reader.readFloat();
        MATERIAL_C_ARRIVAL_2 = reader.readFloat();
        DELIVERY_OF_THE_ELEVATOR = reader.readFloat();
        LENGTH_SIMULATION = reader.readFloat();
        
        
//        System.out.println("                 HARBOR SYSTEM          ");
//        System.out.println("------------------------------------------------");
//        System.out.println("                   DATA INPUT           ");
//        System.out.println("------------------------------------------------");
//        System.out.println("   Mean Arrival:                    " + MEAN_ARRIVAL + "   ");
//        System.out.println("   Min Time Unloaded:               " + MIN_TIME_UNLOADED + "    ");
//        System.out.println("   Max Time Unloaded:               " + MAX_TIME_UNLOADED + "    ");
//        System.out.println("   Length simulation:               " + LENGTH_SIMULATION + "   ");
//        System.out.println("------------------------------------------------");
       
        writer.write("                 Elevator System          " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("                   DATA INPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   Person Arrival:                    " + ELEVATOR_CAPACYTI + "   " + "\n");
        writer.write("   Time Swimming:               " + WEIGHT_MATERIAL_A + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_C + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Pool Capacity:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   Length simulation:               " + LENGTH_SIMULATION + "   " + "\n");
        writer.write("------------------------------------------------" + "\n");
        initSimlib();
        
        eventSchedule(unifrm(MATERIAL_A_ARRIVAL_1, MATERIAL_A_ARRIVAL_2,STREAM_ARRIVE)+simTime, EVENT_ARRIVAL_A);
        eventSchedule(MATERIAL_B_ARRIVAL+simTime, EVENT_ARRIVAL_B);
        eventSchedule(MATERIAL_C_ARRIVAL_1+MATERIAL_C_ARRIVAL_2 + simTime, EVENT_ARRIVAL_C);
        // Fin simulacion
        eventSchedule(LENGTH_SIMULATION, EVENT_END_SIMULATION);

        do {
            timing();
            
            switch (eventType) {
                case EVENT_ARRIVAL_A:
                    
                    arrive_A();
                    break;
                case EVENT_ARRIVAL_B:
                    
                    arrive_B();
                    break;
                    case EVENT_ARRIVAL_C:
                    
                        arrive_C();
                    break;
                case EVENT_DEPARTURE:
       
                    depart();
                    break;
                    case EVENT_PATH:
                        
                    path();
                    break;
                case EVENT_END_SIMULATION:
                    
                    report();
                    break;
            }
        } while (eventType != EVENT_END_SIMULATION);

        reader.close();
        writer.close();
    }

    static void arrive_A(){
        
        eventSchedule(unifrm(MATERIAL_A_ARRIVAL_1, MATERIAL_A_ARRIVAL_2,STREAM_ARRIVE)+simTime, EVENT_ARRIVAL_A);
        arrive_material_A += simTime;
        if(elevator_in_floor_1 && (weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add(new Material(1, 200));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            queue.add(new Material(1, 200));
        }
        depart();
    }
    
    static void arrive_B(){
        eventSchedule(MATERIAL_B_ARRIVAL+simTime, EVENT_ARRIVAL_B);
        if(elevator_in_floor_1 && (weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add(new Material(2, 100));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            queue.add(new Material(1, 200));
        }
        depart();
        
    }
    
    static void arrive_C(){
        eventSchedule(MATERIAL_C_ARRIVAL_1+MATERIAL_C_ARRIVAL_2 + simTime, EVENT_ARRIVAL_C);
        if(elevator_in_floor_1 && (weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add(new Material(3, 50));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            queue.add(new Material(1, 200));
        }
        depart();
    }
    
    static void depart() {
        for (int i = 0; i < queue.size(); i++) {
            Material aux = (Material)queue.get(i);
            if(aux.getPeso()+weight_in_the_elevator()<=ELEVATOR_CAPACYTI){
                elevator.add((Material)queue.remove(i));
                if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                    elevator_path();
                }
            }
        }
    }
    static int weight_in_the_elevator(){
        int aux=0;
        for(int i=0;i<elevator.size();i++){
            Material maux = (Material) elevator.get(i);
            aux += maux.peso;
        }
        return aux;
    }
    static void elevator_path(){
        elevator_in_floor_1=false;
        eventSchedule(DELIVERY_OF_THE_ELEVATOR +simTime, EVENT_PATH);
        
    }
    static void path(){
        elevator_in_floor_1=true;
        for (int i = 0; i < elevator.size(); i++) {
            elevator.remove(i);
            
        }
    }
    static void report() throws IOException{
        
        
        writer.write("                   DATA OUTPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   average_of_3_person_swimming :                 " + ELEVATOR_CAPACYTI+ "%"  + "\n" + "\n");
        writer.write("   swimmers_average:                   " + WEIGHT_MATERIAL_A + "\n");
        writer.write("   percentage_of_person_entering_the_pool:                   " + WEIGHT_MATERIAL_B + "%"+ "\n");
    }
    


}
