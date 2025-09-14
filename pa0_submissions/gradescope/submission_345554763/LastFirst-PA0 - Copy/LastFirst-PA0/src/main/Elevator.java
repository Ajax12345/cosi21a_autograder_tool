/**
 * COSI 21A – PA0
 * Elevator
 *
 * A single elevator that services jobs in a first-come-first-served manner.
 * It gets a job queue, where the elevater will batch up riders
 * and move them to their desired floor.
 * It batches riders up to {maxOccupants}, moves one floor at a time,
 * and prints its position after every one-floor move.
 *
 * @author Will Zhang
 * @version Fall 2025
 */
public class Elevator {
    public static int maxOccupants = 3;

    private final Building building;
    private int currentFloor; 

    private Job[] queue;
    private int head;     
    private int tail;     
    private int count;    

    public Elevator(Building building) {
        if (building == null) throw new IllegalArgumentException();
        this.building = building;
        this.currentFloor = 0;
        this.queue = new Job[8];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public void createLobbyCall() {
        enqueue(Job.lobbyCall());
    }

    public void createJob(Person person, int floor) {
        if (person == null) throw new IllegalArgumentException("person cannot be null for ride job");
        if (floor < 1 || floor > building.getNumFloors()) throw new IllegalArgumentException("Invalid floor");
        enqueue(Job.ride(person, floor));
    }

    public void processAllJobs() {
        while (count > 0) {
            Job first = peek();
            if (first.person == null) {
                processJob(dequeue());
                Job[] trip = new Job[maxOccupants];
                int tripCount = 0;
                while (tripCount < maxOccupants && count > 0) {
                    Job j = peek();
                    if (j.person == null) break; 
                    trip[tripCount++] = dequeue();
                }
                for (int i = 0; i < tripCount; i++) {
                    processJob(trip[i]);
                }
            } else {
				    processJob(dequeue());
            }
        }
    }

    public void processJob(Job job) {
        printPosition();

        if (job.person == null) {
            moveTo(0);
            return;
        }
        moveTo(job.targetFloor);
        exit(job.person, job.targetFloor);
    }

    private void moveTo(int target) {
        if (target == currentFloor) return;
        if (target > currentFloor) {
            for (int f = currentFloor + 1; f <= target; f++) {
                currentFloor = f;
                printPosition();
            }
        } else {
            for (int f = currentFloor - 1; f >= target; f--) {
                currentFloor = f;
                printPosition(); 
            }
        }
    }

    public void exit(Person person, int floor) {
        building.enterFloor(person, floor);
        person.markArrived(floor);
    }

    private void enqueue(Job j) {
        if (count == queue.length) grow();
        queue[tail] = j;
        tail = (tail + 1) % queue.length;
        count++;
    }

    private Job dequeue() {
        if (count == 0) return null;
        Job j = queue[head];
        queue[head] = null;
        head = (head + 1) % queue.length;
        count--;
        return j;
    }

    private Job peek() {
        if (count == 0) return null;
        return queue[head];
    }

    private void grow() {
        Job[] next = new Job[queue.length * 2];
        for (int i = 0; i < count; i++) {
            next[i] = queue[(head + i) % queue.length];
        }
        queue = next;
        head = 0;
        tail = count;
    }

    private void printPosition() {
        if (currentFloor == 0) {
            System.out.println("Elevator at Lobby");
        } else {
            System.out.println("Elevator at floor " + currentFloor);
        }
    }

    @Override
    public String toString() {
        return "Elevator{floor=" + (currentFloor == 0 ? "Lobby" : ("" + currentFloor)) +
               ", queuedJobs=" + count + "}";
    }
}
