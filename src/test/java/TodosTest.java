import org.example.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTasksByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        String[] subtasks = { "Молоко", "Яйца" };
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Встреча с командой", "Нетология", "15:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Ищем с маленькой буквы - найдёт только SimpleTask
        Task[] expected1 = { simpleTask };
        Task[] actual1 = todos.search("молоко");
        assertArrayEquals(expected1, actual1);
        
        // Ищем с большой буквы - найдёт только Epic
        Task[] expected2 = { epic };
        Task[] actual2 = todos.search("Молоко");
        assertArrayEquals(expected2, actual2);
    }
    
    @Test
    void shouldSearchMeetingByTopic() {
        Meeting meeting = new Meeting(1, "Планирование спринта", "Нетология", "10:00");
        Todos todos = new Todos();
        todos.add(meeting);
        
        Task[] expected = { meeting };
        Task[] actual = todos.search("Планирование");
        assertArrayEquals(expected, actual);
    }
    
    @Test
    void shouldSearchMeetingByProject() {
        Meeting meeting = new Meeting(1, "Планирование спринта", "Нетология", "10:00");
        Todos todos = new Todos();
        todos.add(meeting);
        
        Task[] expected = { meeting };
        Task[] actual = todos.search("Нетология");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyArrayWhenNoMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Todos todos = new Todos();
        todos.add(simpleTask);

        Task[] expected = new Task[0];
        Task[] actual = todos.search("несуществующий");
        assertArrayEquals(expected, actual);
    }
}
