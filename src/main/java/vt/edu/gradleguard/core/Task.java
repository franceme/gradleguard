package vt.edu.gradleguard.core;

import java.util.Arrays;

public enum Task {
    testTask("testTask")
    ;

    private String taskName;

    Task(String name){
        this.taskName = name;
    }

    public static Task retrieveTask(String name) {
        return Arrays.stream(Task.values())
                .filter(task -> task.getTaskName().equals(name))
                .findFirst().orElse(null);
    }

    public String getTaskName() {
        return taskName;
    }
}
