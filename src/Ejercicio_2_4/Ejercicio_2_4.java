package Ejercicio_2_4;



import ED_ESimples.List;
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
    static float ELEVATOR_CAPACYTI,WEIGHT_MATERIAL_A,WEIGHT_MATERIAL_B,WEIGHT_MATERIAL_C,MATERIAL_A_ARRIVAL_1,MATERIAL_A_ARRIVAL_2,
            MATERIAL_B_ARRIVAL,MATERIAL_C_ARRIVAL_1,MATERIAL_C_ARRIVAL_2,DELIVERY_OF_THE_ELEVATOR, LENGTH_SIMULATION;
    static float counter_material_A,arrive_material_A,leave_material_A,average_transit_time_of_material_A,
            arrive_materil_B,enter_material_B,counter_material_B,average_wait_time_of_material_B,c_material_that_makes_the_trip_in_1_hour;
    
    static SimReader reader;
    static SimWriter writer;
    static List queue;
    static List elevator;
    static boolean elevator_in_floor_1;
    
    public static void main(String[] args) throws IOException {

        reader = new SimReader("Ejercicio_2_4.in");
        writer = new SimWriter("Ejercicio_2_4.out");        
        queue = new List();
        elevator = new List();
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
        
        
       
        writer.write("                 Elevator System          " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("                   DATA INPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   ELEVATOR_CAPACYTI:               " + ELEVATOR_CAPACYTI + "   " + "\n");
        writer.write("   WEIGHT_MATERIAL_A:               " + WEIGHT_MATERIAL_A + "    " + "\n");
        writer.write("   WEIGHT_MATERIAL_B:               " + WEIGHT_MATERIAL_B + "    " + "\n");
        writer.write("   WEIGHT_MATERIAL_C:               " + WEIGHT_MATERIAL_C + "    " + "\n");
        writer.write("   MATERIAL_A_ARRIVAL_1:            " + MATERIAL_A_ARRIVAL_1 + "    " + "\n");
        writer.write("   MATERIAL_A_ARRIVAL_2:            " + MATERIAL_A_ARRIVAL_2 + "    " + "\n");
        writer.write("   MATERIAL_B_ARRIVAL:              " + MATERIAL_B_ARRIVAL + "    " + "\n");
        writer.write("   MATERIAL_C_ARRIVAL_1:            " + MATERIAL_C_ARRIVAL_1 + "    " + "\n");
        writer.write("   MATERIAL_C_ARRIVAL_2:            " + MATERIAL_C_ARRIVAL_2 + "    " + "\n");
        writer.write("   DELIVERY_OF_THE_ELEVATOR:        " + DELIVERY_OF_THE_ELEVATOR + "    " + "\n");
        writer.write("   LENGTH_SIMULATION:               " + LENGTH_SIMULATION + "   " + "\n");
        writer.write("------------------------------------------------" + "\n");
        initSimlib();
        
        eventSchedule(unifrm(MATERIAL_A_ARRIVAL_2, MATERIAL_A_ARRIVAL_1,STREAM_ARRIVE)+simTime, EVENT_ARRIVAL_A);
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
        counter_material_A+=1;
        eventSchedule(unifrm(MATERIAL_A_ARRIVAL_2, MATERIAL_A_ARRIVAL_1,STREAM_ARRIVE)+simTime, EVENT_ARRIVAL_A);
        arrive_material_A += simTime;
        if(elevator_in_floor_1 && (WEIGHT_MATERIAL_A + weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add_to_the_end(new Material(1, 200));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            queue.add_to_the_end(new Material(1, 200));
        }
        depart();
    }
    
    static void arrive_B(){
        counter_material_B+=1;
        eventSchedule(MATERIAL_B_ARRIVAL+simTime, EVENT_ARRIVAL_B);
        if(elevator_in_floor_1 && (WEIGHT_MATERIAL_B+ weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add_to_the_end(new Material(2, 100));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            arrive_materil_B+=simTime;
            queue.add_to_the_end(new Material(2, 100));
        }
        depart();
        
    }
    
    static void arrive_C(){
        //eventSchedule(MATERIAL_C_ARRIVAL_1+MATERIAL_C_ARRIVAL_2 + simTime, EVENT_ARRIVAL_C);
        eventSchedule(4+simTime, EVENT_ARRIVAL_C);
        if(elevator_in_floor_1 && (WEIGHT_MATERIAL_C+weight_in_the_elevator()<=ELEVATOR_CAPACYTI)){
            elevator.add_to_the_end(new Material(3, 50));
            if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                elevator_path();
            }
        }
        else{
            queue.add_to_the_end(new Material(3, 50));
        }
        depart();
    }
    
    static void depart() {
        for (int i = 0; i < queue.length(); i++) {
            Material aux = (Material)queue.get_object(i);
            if(aux.getPeso()+weight_in_the_elevator()<=ELEVATOR_CAPACYTI){
                elevator.add_to_the_end((Material)queue.get_object(i));
                queue.delete_the_position(i);
                if(aux.getTipo()==2){
                    enter_material_B+=simTime;
                }
                if(weight_in_the_elevator()==ELEVATOR_CAPACYTI){
                    elevator_path();
                }
            }
        }
    }
    static int weight_in_the_elevator(){
        int aux=0;
        for(int i=0;i<elevator.length();i++){
            Material maux = (Material) elevator.get_object(i);
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
        for (int i = 0; i < elevator.length(); i++) {
            Material aux = (Material) elevator.get_object(i);
            elevator.delete_the_position(i);
            if (aux.getTipo()==1) {
                leave_material_A += simTime;
                
            }
            if(simTime<=60){
                if(aux.getTipo()==3){
                    c_material_that_makes_the_trip_in_1_hour++;
                }
            }
            
        }
    }
    static void report() throws IOException{
        for (int i = 0; i < queue.length(); i++) {
            Material aux = (Material) queue.get_object(i);
            if(aux.getTipo()==1){
                leave_material_A+=simTime;
            }
        }
        for (int i = 0; i < elevator.length(); i++) {
            Material aux = (Material) elevator.get_object(i);
            if(aux.getTipo()==1){
                leave_material_A+=simTime;
            }
        }
        for (int i = 0; i < queue.length(); i++) {
            Material aux = (Material) queue.get_object(i);
            if(aux.getTipo()==2){
                enter_material_B+=simTime;
            }
        }
        average_transit_time_of_material_A = (leave_material_A-arrive_material_A)/counter_material_A;
        average_wait_time_of_material_B =(enter_material_B-arrive_materil_B)/counter_material_B;
        writer.write("                   DATA OUTPUT           " + "\n");
        writer.write("------------------------------------------------" + "\n");
        writer.write("   average_transit_time_of_material_A:                 " + average_transit_time_of_material_A   + "\n" + "\n");
        writer.write("   average_wait_time_of_material_B:                    " + average_wait_time_of_material_B + "\n");
        writer.write("   c_material_that_makes_the_trip_in_1_hour:           " + c_material_that_makes_the_trip_in_1_hour +  "\n");
    }
    


}
