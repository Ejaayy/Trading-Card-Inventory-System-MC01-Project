import java.util.Scanner;

public class TaskPlan {

    // Properties / Attributes
    public Task[] taskList;

    // Methods
    public void run(TaskPlan taskplan) {
        Scanner scanner = new Scanner(System.in);
        taskList = new Task[7];

        //Initialize 7 new empty tasks
        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = new Task();
        }

        //Menu
        System.out.println("Welcome to your Task Scheduler!");
        System.out.println("Please input your tasks below: ");
        System.out.println("---------------------------------");

        //Loop for input
        for (int i = 0; i < taskList.length; i++) {

            //Ask for task name
            System.out.printf("Task %d Name: ", i + 1);
            taskList[i].setShortName(scanner.nextLine());

            //Ask for task description
            System.out.printf("Task %d Description: ", i + 1);
            taskList[i].setDescription(scanner.nextLine());

            boolean status; //for format validation

            do {
                System.out.print("Input Starting Time [00:00 - 23:59]: ");
                String startingTime = scanner.nextLine();

                //Checks and inputs data if correct format
                status = taskList[i].getStartTime().initializeTime(startingTime);

            } while (!taskList[i].getStartTime().validate() || !status); //while not valid

            do {
                System.out.print("Input Ending Time [00:00 - 23:59]: ");
                String endingTime = scanner.nextLine();

                //Checks and inputs data if correct format
                status = taskList[i].getEndTime().initializeTime(endingTime);

            } while ( !taskList[i].getEndTime().validate() || !taskList[i].isValid() || !status ); //while not valid

            System.out.println("---------------------------------");
        }

        // Apply sort and greedy Algorithm
        TaskSorter sort = new TaskSorter(taskList);
        int index = sort.greedyAlgorithm(taskList);  // get only chosen tasks
        taskplan.displayTasks(index);

        scanner.close();
    }

    public void displayTasks(int index){

        //Format for displaying tasks
        System.out.println("Here is your schedule for today!");
        for(int i=0; i< index; i++){
            System.out.println(taskList[i].taskFormat());
        }
    }
}
