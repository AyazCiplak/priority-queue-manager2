package managementsystem;

import java.util.ArrayList;
import java.util.HashMap;

public class PriorityQueue{

    //Note: A lower priority value indicates a higher priority. A patient with a priority value of 0.0 has the highest possible priority.

    ArrayList<Patient> patients;
    HashMap<String, Integer> nameToIndex;

    PriorityQueue() {

        patients = new ArrayList<Patient>();
        nameToIndex = new HashMap<String, Integer>();

        //Dummy node is created to start indexing at 1
        patients.add(new Patient("dummy", 0.0));

    }

    //Heap element methods
    private int parent(int i){
        return i/2;
    }

    private int leftChild(int i){
        return 2*i;
    }

    private int rightChild(int i){
        return 2*i + 1;
    }

    //swaps two elements in the ArrayList and HashMap
    void swap(int i, int j){
        Patient tmp = patients.get(i);
        patients.set(i, patients.get(j));
        patients.set(j, tmp);

        nameToIndex.remove(i);
        nameToIndex.remove(j);
        nameToIndex.put(patients.get(j).name, j);
        nameToIndex.put(patients.get(i).name, i);
    }

    void upHeap(int i){
        //upHeaps recursively as long as the patient isn't at the top of the minHeap
        if(i > 1){
            int parentIndex = parent(i);

            if(patients.get(i).priority < patients.get(parentIndex).priority) {
                swap(i, parentIndex);
                upHeap(parentIndex);
            }
        }
    }

    void downHeap(int i){
        int minIndex = i;
        int rcIndex = rightChild(i);
        int lcIndex = leftChild(i);

        double priority = patients.get(i).priority;
        double lcPriority = Double.POSITIVE_INFINITY;
        double rcPriority = Double.POSITIVE_INFINITY;

        if (rcIndex < patients.size()) {rcPriority = patients.get(rcIndex).priority;}
        if(lcIndex < patients.size()) {lcPriority = patients.get(lcIndex).priority;}

    }



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
            if(!(obj instanceof PriorityQueue.Patient)) return false;

            Patient objPatient = (Patient) obj;

            //Will only return true if object matches name and priority
            return this.name.equals(objPatient.name) && this.priority == objPatient.priority;
        }
    }
}
