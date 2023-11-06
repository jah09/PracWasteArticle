package com.example.dailyagenda;

public class Task {
    String taskID;
    String userID;
    String taskTitle;
    String taskDescription;
    Boolean isTaskDone;

    public Task() {
    }

    public Task(String taskID, String userID, String taskTitle, String taskDescription,Boolean isTaskDone) {
        this.taskID = taskID;
        this.userID = userID;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.isTaskDone=isTaskDone;
    }

    public Boolean getTaskDone() {
        return isTaskDone;
    }

    public void setTaskDone(Boolean taskDone) {
        isTaskDone = taskDone;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
