package com.hfad.tabletrainer;

import java.util.ArrayList;

public class Table {

    public Table(int table) {
        this.table = table;
    }

    private ArrayList<Integer> answers = new ArrayList<>();
    private int table;

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

    public void createAnswers()
    {
        answers = new ArrayList<>();
        for (int i = 1; i < 10; i++) {

            answers.add(table*i);
        }
    }


}
