package managementsystem;

import java.util.ArrayList;
import java.util.HashMap;

public class QueueManager{

    //Patient manipulation methods

    static class Patient {
        private String name;
        private double priority;

        Patient(String name, double priority) {
            this.name = name;
            this.priority = priority;
        }

        //Alternate constructor for existing patient
        Patient(Patient existingPatient){
            this.name = existingPatient.name;
            this.priority = existingPatient.priority;
        }

        //basic getter/setter methods
        double getPriority() {
            return this.priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name + "-" + this.priority;
        }

        //Compares a Patient and any other Object
        public boolean equals (Object obj) {
            //Case: Object isn't a Patient object
            if(!(obj instanceof QueueManager.Patient)) return false;

            Patient objPatient = (Patient) obj;

            //Will only return true if object matches name and priority
            return this.name.equals(objPatient.name) && this.priority == objPatient.priority;
        }
    }
}
