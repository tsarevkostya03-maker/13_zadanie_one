package org.example;

import java.util.Arrays;

public class Epic extends Task {
    protected String[] subtasks;

    public Epic(int id, String[] subtasks) {
        super(id);
        this.subtasks = Arrays.copyOf(subtasks, subtasks.length);
    }

    public String[] getSubtasks() {
        return Arrays.copyOf(subtasks, subtasks.length);
    }

    @Override
    public boolean matches(String query) {
        for (String subtask : subtasks) {
            if (subtask.contains(query)) {
                return true;
            }
        }
        return false;
    }
}
