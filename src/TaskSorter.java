import java.util.Arrays;

public class TaskSorter {

    //Properties
    private Task[] taskList;
    private int n;

    //Constructor
    public TaskSorter(Task[] taskList) {
        //Copies the taskList for sorting
        this.taskList = Arrays.copyOf(taskList, taskList.length);
        this.n = taskList.length;
    }

    //Methods

    public void sortByEndTime() {
        // Bubble sort to sort the tasks by End Time
        for (int i = 0; i < this.n - 1; i++) {
            for (int j = 0; j < this.n - i - 1; j++) {
                if (this.taskList[j].getEndTime().isAfter(this.taskList[j + 1].getEndTime())) {
                    // swap tasks[j] and tasks[j+1]
                    Task temp = this.taskList[j];
                    this.taskList[j] = this.taskList[j + 1];
                    this.taskList[j + 1] = temp;
                }
            }
        }
    }

    public int greedyAlgorithm(Task[] tasks){

        //Sort the tasks by end time.
        sortByEndTime();

        //Select the first task in the sorted list. Add this to the task plan.

        tasks[0] = this.taskList[0];
        Time previousEndTime = this.taskList[0].getEndTime();

        /*Look for the next task that starts soonest after the selected
        task. Select this task. Add this to the task plan.

        This part does not involve sorting, it just hand picks specific
        tasks depending on startTime

        It skips the tasks that overlaps
        */

        int index = 1; //Track index of the next position to change

        //Loop through array
        for(int i = 1; i < this.n; i++){
            Time currentStartTime = taskList[i].getStartTime();

            if(currentStartTime.isAfterOrEqual(previousEndTime)){
                tasks[index] = taskList[i];
                previousEndTime = taskList[i].getEndTime();
                index++;
            }
        }
        return index; // how many tasks were selected
    }

    public Task[] getSortedTasks() {
        return taskList;
    }

}
