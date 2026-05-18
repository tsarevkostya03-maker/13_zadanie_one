import org.example.Epic;
import org.example.Meeting;
import org.example.SimpleTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TasksTest {

    @Test
    void shouldMatchSimpleTaskWhenTitleContainsQuery() {
        SimpleTask task = new SimpleTask(1, "Купить продукты");
        assertTrue(task.matches("продукты"));
        assertFalse(task.matches("молоко"));
    }

    @Test
    void shouldMatchEpicWhenAnySubtaskContainsQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        assertTrue(epic.matches("Молоко"));
        assertTrue(epic.matches("Хлеб"));
        assertFalse(epic.matches("Сахар"));
    }

    @Test
    void shouldMatchMeetingWhenTopicContainsQuery() {
        Meeting meeting = new Meeting(3, "Планирование спринта", "Нетология", "10:00");
        assertTrue(meeting.matches("Планирование"));
        assertFalse(meeting.matches("Отчет"));
    }

    @Test
    void shouldMatchMeetingWhenProjectContainsQuery() {
        Meeting meeting = new Meeting(3, "Планирование спринта", "Нетология", "10:00");
        assertTrue(meeting.matches("Нетология"));
        assertFalse(meeting.matches("Яндекс"));
    }
}
