package managementsystem;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


//Unit tests for components of the PriorityQueue class
public class PriorityQueueTests {

    public static ArrayList<PriorityQueue.Patient> testPatients = new ArrayList<>();
    public static HashMap<String,Integer> testNameToIndex = new HashMap<String,Integer>();

    PriorityQueue testPriorityQueue = new PriorityQueue();

    @BeforeEach
    public void resetPriorityQueue(){
        testPriorityQueue.patients = new ArrayList<>();
        // Deep copying the patients list so that we can freely mutate our test Priority queue
        for (PriorityQueue.Patient patient : testPatients){
            testPriorityQueue.patients.add(new PriorityQueue.Patient(patient));
        }
        testPriorityQueue.nameToIndex = new HashMap<>(testNameToIndex);
    }

    @Test
    @DisplayName("Parent Test")
    void findParentIndex() {
        assertEquals(5, testPriorityQueue.parent(10));
        assertEquals(8, testPriorityQueue.parent(17));
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
    //upHeap tests
    //downHeap tests
    //contains tests
    //getPriority tests
    //getMinPriority tests
    //removePatient tests
    //removeMin tests
    //peekMin tests
    //add tests (double parameter)


    @Test
    @DisplayName("Add Test 1 - Single Parameter")
    void addSinglePatient() {
        testPriorityQueue.add("Ayaz");
        assertTrue(testPriorityQueue.nameToIndex.containsKey("Ayaz"));
    }

    @Test
    @DisplayName("Add Test 2 - Single Parameter")
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
            () -> assertEquals(3,testPriorityQueue.nameToIndex.size())
        );
    }

    //remove tests
    //changePriority tests
    //removeMoreUrgent tests (?)
    //removeUrgentPatients tests
    //removeNonUrgentPatients tests




}
