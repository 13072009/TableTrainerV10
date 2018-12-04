package com.hfad.tabletrainer;

import java.util.ArrayList;
import java.util.Random;

public class Table {

    public Table(int table) {
        this.table = table;
    }

    private ArrayList<Integer> answers = new ArrayList<>();


    ArrayList<String> choose = new ArrayList<String>();
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

    public ArrayList<String> getChoose() {
        return choose;
    }


    public void createTask(int taskNum)
    {
        task="";
        taskAnsw="";
        if(table==11 || mix)
        {
            setTable(rand.nextInt(10)+1);
            createAnswers();
            mix = true;
        }
        switch (taskNum)
        {
            case 1:
                createTask1();
                break;
            case 2:
                createTask2();
                break;
            case 3:
                createTask3();
            case 4:
                createTask3();
        }

    }

    public void createTask1()
    {
        int a,b,c;

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
    public void createTask2()
    {
        int a,b,c,d, position;

        a =  rand.nextInt(10)+1;

        task = table+"x"+a;
        taskAnsw=""+(table*a);

        b = (rand.nextInt(10)+1)*table+2;
        c = b+2;
        d = c+5;
        position = rand.nextInt(3)+1;

        choose.clear();
        choose.add(""+b);
        choose.add(""+c);
        choose.add(""+d);
        choose.add(position,taskAnsw);
    }

    public void createTask3()
    {
        int a;
        a = rand.nextInt(10) + 1;

        task = table + "x" + a;
        taskAnsw = "" + (table * a);
    }

    public void createAnswers()
    {
        answers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {

            answers.add(table*i);
        }
    }




}
