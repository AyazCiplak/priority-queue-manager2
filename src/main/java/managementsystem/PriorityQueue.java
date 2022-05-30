package managementsystem;

import java.util.ArrayList;
import java.util.HashMap;

class PriorityQueue{

    //Note: A lower priority value indicates a higher priority. A patient with a priority value of 0.0 has the highest possible priority.

    public ArrayList<Patient> patients;
    public HashMap<String, Integer> nameToIndex;

    public PriorityQueue() {

        patients = new ArrayList<>();
        nameToIndex = new HashMap<>();

        //Dummy node is created to start indexing at 1
        patients.add(new Patient("dummy", 0.0));

    }


    //Heap element methods
    int parent(int i){
        return i/2;
    }

    int leftChild(int i){
        return 2*i;
    }

    int rightChild(int i){
        return 2*i + 1;
    }

    //Swaps two elements in the ArrayList and HashMap
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

        //if right child more urgent than parent
        if(rcIndex < patients.size() && rcPriority < priority) {
            if(lcIndex < patients.size() && lcPriority < rcPriority){
                minIndex = lcIndex;
            } else {
                minIndex = rcIndex;
            }
        }

        //if left child more urgent than parent
        if(lcIndex < patients.size() && lcPriority < priority) {
            if(rcIndex < patients.size() && rcPriority < lcPriority) {
                minIndex = rcIndex;
            } else {
                minIndex = lcIndex;
            }
        }

        if (i != minIndex){
            swap(i, minIndex);
            downHeap(minIndex);
        }
    }

    boolean contains(String name){
        return nameToIndex.containsKey(name);
    }

    double getPriority(String name) {
        if(!contains(name)) {
            return -1;
        } else {
            return patients.get(nameToIndex.get(name)).priority;
        }
    }

    double getMinPriority() {
        if(patients.isEmpty() || patients.size() == 1) {
            return -1;
        } else {
            return patients.get(1).priority;
        }
    }

    void removePatient(int initialIndex, String name) {
        if(initialIndex == (patients.size()-1)) {
            patients.remove(initialIndex);
            nameToIndex.remove(name);

        } else {
            swap(initialIndex, (patients.size() - 1)); //swaps index (that will be removed) with the lowest element in tree, hashMap indices are also swapped

            patients.remove((patients.size() - 1)); //removes name from patients list
            downHeap(initialIndex);
            nameToIndex.remove(name); // removes patient from hashMap

        }
    }

    String removeMin() {
        if(patients.isEmpty() || patients.size() == 1) {
            return null;
        } else {
            int initialIndex = 1;
            String name = patients.get(1).name;
            removePatient(initialIndex, name);
            return name;
        }
    }

    String peekMin() {
        if(patients.isEmpty() || patients.size() ==1){
            return null;
        } else {
            return patients.get(1).name;
        }
    }

    boolean add(String name, double priority){
        if(contains(name)) {
            return false;
        } else {
            Patient newPatient = new Patient(name, priority);
            patients.add(newPatient);
            nameToIndex.put(name, (patients.size() -1));
            upHeap((patients.size()-1));
            return true;
        }
    }

   boolean add(String name){
        if(contains(name)){
            return false;
        } else {
            Patient newPatient = new Patient(name, Double.POSITIVE_INFINITY);
            patients.add(newPatient);
            nameToIndex.put(name, (patients.size()-1));
            return true;
        }
    }

    boolean remove(String name){
        if (patients.isEmpty() || patients.size() == 1 || !contains(name)) {
            return false;
        } else {

            int initialIndex = nameToIndex.get(name); //gets index of name
            removePatient(initialIndex, name);
            return true;
        }
    }

    boolean changePriority(String name, double priority) {
        if (patients.isEmpty() || patients.size() == 1 || !contains(name)) {
            return false;
        } else if (patients.get(nameToIndex.get(name)).priority == priority){
            return true;
        } else {
            int patientIndex = nameToIndex.get(name);
            patients.get(patientIndex).priority = priority;
            downHeap(patientIndex);
            upHeap(patientIndex);
            return true;

        }
    }

    ArrayList<Patient> removeMoreUrgent(boolean indicator, double threshold){
        if (patients.isEmpty() || patients.size() == 1){
            return null;
        } else {
            ArrayList<Patient> removedPatients = new ArrayList<>();
            ArrayList<Patient> keptPatients = new ArrayList<>();

            for (int i = 1; i < patients.size(); i++){
                double patientPriority = patients.get(i).priority;
                if(indicator) {
                    if (patientPriority <= threshold) { //patient has more urgent priority than threshold
                        removedPatients.add(patients.get(i));
                    } else {
                        keptPatients.add(patients.get(i));
                    }
                } else {
                    if (patientPriority >= threshold) { //patient has more urgent priority than threshold
                        removedPatients.add(patients.get(i));
                    } else {
                        keptPatients.add(patients.get(i));
                    }
                }
            }

            for (Patient patient : removedPatients) {
                String removedPatientName = patient.name;

                remove(removedPatientName);

            }
            return removedPatients;


        }
    }

    public ArrayList<Patient> removeUrgentPatients(double threshold){ //add if else trivial case
        return removeMoreUrgent(true, threshold);
    }


    public ArrayList<Patient> removeNonUrgentPatients(double threshold){ //add if else trivial case
        return removeMoreUrgent(false, threshold);
    }

    static class Patient {
        private final String name;
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
            if(!(obj instanceof Patient objPatient)) return false;

            //Will only return true if object matches name and priority
            return this.name.equals(objPatient.name) && this.priority == objPatient.priority;
        }
    }
}
