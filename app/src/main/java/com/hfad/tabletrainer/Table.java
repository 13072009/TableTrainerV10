package com.hfad.tabletrainer;

import java.util.ArrayList;
import java.util.Random;

public class Table {

    public Table(int table) {
        this.table = table;
    }

    private ArrayList<Integer> answers = new ArrayList<>();
    private int table;
    private String task;
    private String taskAnsw;
    private int point;
    private boolean mix=false;
    Random rand = new Random();

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskAnsw() {
        return taskAnsw;
    }

    public void setTaskAnsw(String taskAnsw) {
        this.taskAnsw = taskAnsw;
    }



    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    public void createTask()
    {
        int a,b,c;
        task="";
        taskAnsw="";
        if(table==11 || mix)
        {
            setTable(rand.nextInt(10)+1);
            createAnswers();
            mix = true;
        }
        a =  rand.nextInt(5)+1;
        b = a+2;
        c = b+2;
        for (int i = 0; i < 10; i++)
        {
            if(i!=a && i!=b && i!=c)
            {
                if(task=="") {
                    task = ""+answers.get(i);
                }
                else {
                    task = task+"; "+answers.get(i);
                }
            }
            else
            {
                if(taskAnsw=="") {
                    taskAnsw = ""+answers.get(i);
                }
                else {
                    taskAnsw = taskAnsw+"; "+answers.get(i);
                }
                task = task+";...";

            }
        }

    }

    public void createAnswers()
    {
        answers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {

            answers.add(table*i);
        }
    }



}
