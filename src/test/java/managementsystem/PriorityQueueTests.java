package managementsystem;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


//Unit tests for components of the PriorityQueue class
public class PriorityQueueTests {

    public static ArrayList<PriorityQueue.Patient> testPatients = new ArrayList<>();
    public static HashMap<String,Integer> testNameToIndex = new HashMap<>();

    PriorityQueue testPriorityQueue = new PriorityQueue();

    //Test helper methods

    public static <T> void  swapList(ArrayList<T> list, int i, int j){
        T temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }

    public static <K,V> void swapHashMapValues(HashMap<K,V> hashMap, K key1, K key2){
        V temp = hashMap.get(key1);
        hashMap.put(key1, hashMap.get(key2));
        hashMap.put(key2, temp);
    }

    public static ArrayList<PriorityQueue.Patient> makeDeepCopy(ArrayList<PriorityQueue.Patient> list) {
        ArrayList<PriorityQueue.Patient> copy = new ArrayList<>();
        for (PriorityQueue.Patient patient : list) {
            copy.add(new PriorityQueue.Patient(patient.getName(), patient.getPriority()));
        }
        return copy;
    }

    //Checks validity of minHeap structure
    public static boolean isValidHeap(ArrayList<PriorityQueue.Patient> list){
        for (int index = 0; index < list.size(); index++){
            if ( 2*index < list.size() && list.get(index).getPriority() > list.get(2*index).getPriority() ) {
                return false;
            }
            if ( 2*index+1 < list.size() && list.get(index).getPriority() > list.get(2*index+1).getPriority() ) {
                return false;
            }
        }
        return true;
    }

    //Checks validity of hashMap
    public static boolean isValidMapping(ArrayList<PriorityQueue.Patient> patients, HashMap<String,Integer> nameToIndex ){
        if ( patients.size()-1 != nameToIndex.size() ) return false;
        for ( Map.Entry<String,Integer> entry : nameToIndex.entrySet()){
            String name = entry.getKey();
            int index = entry.getValue();
            if (!patients.get(index).getName().equals(name)) return false;
        }
        return true;
    }

    //Initializes sample queue/hashmap to manipulate and compare during tests
    @BeforeAll
    public static void initialiseStructures() {
        testPatients.add(new PriorityQueue.Patient("dummy", 0.0));
        testPatients.add(new PriorityQueue.Patient("Arthur", 10.0));
        testPatients.add(new PriorityQueue.Patient("Boris", 20.0));
        testPatients.add(new PriorityQueue.Patient("Charles", 30.0));
        testPatients.add(new PriorityQueue.Patient("Dave", 40.0));
        testPatients.add(new PriorityQueue.Patient("Edwin", 50.0));
        testPatients.add(new PriorityQueue.Patient("Frank", 60.0));
        testPatients.add(new PriorityQueue.Patient("Greta", 70.0));

        //Sample minHeap structure visualisation:
        //             Arthur
        //           /        \
        //       Boris      Charles
        //      /     \     /     \
        //   Dave   Edwin  Frank  Greta
        //   /
        // (next)

        for(int i = 1; i <= 7; i++){
            testNameToIndex.put(testPatients.get(i).getName(), i);
        }
    }

    //Re-initializes priority queue before every test
    @BeforeEach
    public void resetPriorityQueue(){
        testPriorityQueue.patients = new ArrayList<>();
        // Deep copying the patients list so that we can freely mutate our test Priority queue
        for (PriorityQueue.Patient patient : testPatients){
            testPriorityQueue.patients.add(new PriorityQueue.Patient(patient));
        }
        testPriorityQueue.nameToIndex = new HashMap<>(testNameToIndex);
    }

    //parent() unit test
    @Test
    @DisplayName("Parent Test")
    void findParentIndex() {
        assertEquals(5, testPriorityQueue.parent(10));
        assertEquals(8, testPriorityQueue.parent(17));
    }

    //leftChild() unit test
    @Test
    @DisplayName("Left Child Test")
    void findLeftChildIndex() {
        assertEquals(24, testPriorityQueue.leftChild(12));
    }

    //rightChild() unit test
    @Test
    @DisplayName("Right Child Test")
    void findRightChildIndex() {
        assertEquals(15, testPriorityQueue.rightChild(7));
        assertNotEquals(20, testPriorityQueue.rightChild(10));
    }


    @Test
    @DisplayName("Swap Test - Single Pair of Patients")
    void swapPatients() {

    }

    @Test
    //@Tag("Swap Test - Multiple Pairs of Patients")
    void swapMultiplePatients(){

    }

    //Other swap tests

    // ---------- upHeap() unit tests ----------
    @Test
    @DisplayName("UpHeap Test 1 - UpHeaping Top Patient")
    void upHeapTopPatient() {
        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        // UpHeaping first patient (Arthur)
        testPriorityQueue.upHeap(1);

        //Original list and heap should be equal to modified one - upHeaping top patient should do nothing
        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);
    }

    @Test
    @DisplayName("UpHeap Test 2 - Modifying and Reverting Structures")
    void modifyAndUpHeap() {
        //Modifies top patient's priority
        testPriorityQueue.patients.get(1).setPriority(100.0);

        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        testPriorityQueue.upHeap(1);

        //UpHeaping modified patient should lead to structures being identical to sample ones
        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);

    }

    @Test
    @DisplayName("UpHeap Test 3 - Modifying Test and Original Structures")
    void modifyBothAndUpHeap() {

        //Adds 8th patient with minimum priority to heap and hashmap
        PriorityQueue.Patient Henry = new PriorityQueue.Patient("Henry", 2.0);
        testPriorityQueue.patients.add(Henry);
        testPriorityQueue.nameToIndex.put(Henry.getName(), 8);

        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        //Simulates upHeap trajectory on test structures
        swapList(testPatients, 4, 8);
        swapHashMapValues(testNameToIndex, "Henry", "Dave" );
        swapList(testPatients, 2, 4);
        swapHashMapValues(testNameToIndex, "Henry", "Boris");
        swapList(testPatients,1,2);
        swapHashMapValues(testNameToIndex, "Henry", "Arthur");

        testPriorityQueue.upHeap(8);

        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);

    }

    // ---------- downHeap() unit tests ----------
    @Test
    @DisplayName("DownHeap Test 1 - DownHeaping Bottom Patient")
    void downHeapBottomPatient() {
        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        // DownHeaping on bottom patient (Greta)
        testPriorityQueue.downHeap(7);

        //Since the bottom patient is downHeaped, structures should be identical
        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);
    }

    @Test
    @DisplayName("DownHeap Test 2 - Modifying Test and Original Structures")
    void modifyBothAndDownHeap() {

        testPriorityQueue.patients.get(1).setPriority(45.0);

        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        //Simulates downHeap on test structures
        swapList(testPatients, 1, 2);
        swapHashMapValues(testNameToIndex, "Arthur", "Boris");
        swapList(testPatients,2, 4);
        swapHashMapValues(testNameToIndex, "Arthur", "Dave");

        //DownHeaps in second set of test structures
        testPriorityQueue.downHeap(1);

        //Simulated and actual downHeap should be identical
        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);

    }

    //contains() unit test
    @Test
    @DisplayName("Contains test")
    void checkIfContains() {
        testPriorityQueue.add("Jordi");
        testPriorityQueue.add("Summer");

        assertTrue(testPriorityQueue.contains("Jordi"));
        assertTrue(testPriorityQueue.contains("Summer"));
        assertFalse(testPriorityQueue.contains("Ethan"));
    }

    // ---------- getPriority() unit tests ----------
    @Test
    @DisplayName("GetPriority Test 1 - Existing Sample List")
    void getExistingPriority() {
        assertEquals(50.0, testPriorityQueue.getPriority("Edwin"));
        assertEquals(70.0, testPriorityQueue.getPriority("Greta"));
        assertEquals(-1, testPriorityQueue.getPriority("Sarah"));
    }

    @Test
    @DisplayName("GetPriority Test 2 - New Patient Priority")
    void getNewPriority() {
        PriorityQueue.Patient Julia = new PriorityQueue.Patient("Julia", 23.0);
        testPriorityQueue.patients.add(Julia);
        testPriorityQueue.nameToIndex.put(Julia.getName(), 8);

        assertEquals(23.0, testPriorityQueue.getPriority("Julia"));

    }

    //getMinPriority() unit test
    @Test
    @DisplayName("GetMinPriority Test 1 - Bottom Patient Priority")
    void getLowestPriority(){
        assertEquals(10.0, testPriorityQueue.getMinPriority());
    }



    // ---------- add() unit tests (two parameters) ----------
    @Test
    @DisplayName("Add test 1 (Two Parameters) - Adding identical patient to structure")
    void addExistingPatient() {
        PriorityQueue.Patient Arthur = testPriorityQueue.patients.get(1);

        ArrayList<PriorityQueue.Patient> testPatients = makeDeepCopy(testPriorityQueue.patients);
        HashMap<String,Integer> testNameToIndex = new HashMap<>(testPriorityQueue.nameToIndex);

        //Add method should not add an existing patient
        assertFalse(testPriorityQueue.add(Arthur.getName(), Arthur.getPriority()));

        //Both structures should remain identical
        assertEquals(testPatients, testPriorityQueue.patients);
        assertEquals(testNameToIndex, testPriorityQueue.nameToIndex);
    }

    @Test
    @DisplayName("Add Test 2 (Two Parameters) - Adding new patient + priority to structure")
    void addNewPatientAndPriority() {

    }

    // ---------- add() unit tests (single parameter) ----------
    @Test
    @DisplayName("Add Test 1 (Single Parameter) - Adding One Patient")
    void addSinglePatient() {
        testPriorityQueue.add("Ayaz");
        assertTrue(testPriorityQueue.nameToIndex.containsKey("Ayaz"));
    }

    @Test
    @DisplayName("Add Test 2 (Single Parameter) - Adding Multiple Patients")
    void addMultiplePatients() {
        testPriorityQueue.add("John");
        testPriorityQueue.add("Costas");
        testPriorityQueue.add("Emanuele");

        assertAll(
            () -> assertTrue(testPriorityQueue.nameToIndex.containsKey("John")),
            () -> assertTrue(testPriorityQueue.nameToIndex.containsKey("Costas")),
            () -> assertTrue(testPriorityQueue.nameToIndex.containsKey("Emanuele")),
            () -> assertFalse(testPriorityQueue.nameToIndex.containsKey("Ayaz")),
            () -> assertFalse(testPriorityQueue.add("Costas")),
            () -> assertEquals(10,testPriorityQueue.nameToIndex.size())
        );
    }

    //removeMin tests
    //remove tests
    //peekMin tests
    //changePriority tests
    //removeMoreUrgent tests (?)
    //removeUrgentPatients tests
    //removeNonUrgentPatients tests




}
