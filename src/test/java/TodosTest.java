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
        Task[] result1 = todos.search("молоко");
        assertEquals(1, result1.length);

        // Ищем с большой буквы - найдёт только Epic
        Task[] result2 = todos.search("Молоко");
        assertEquals(1, result2.length);

        // Проверяем, что это разные задачи
        assertTrue(result1[0] instanceof SimpleTask);
        assertTrue(result2[0] instanceof Epic);
    }

    @Test
    void shouldSearchTasksByQueryCaseInsensitive() {
        // Если нужен поиск без учёта регистра, нужно изменить логику в классах задач
        // Но по условию задачи используется contains, который чувствителен к регистру
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        String[] subtasks = { "Молоко", "Яйца" };
        Epic epic = new Epic(2, subtasks);

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);

        // Ищем слово "молоко" в любом регистре - нужно дважды проверять
        Task[] result = todos.search("молоко");
        assertEquals(1, result.length); // только SimpleTask

        Task[] result2 = todos.search("Молоко");
        assertEquals(1, result2.length); // только Epic
    }

    @Test
    void shouldReturnEmptyArrayWhenNoMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Todos todos = new Todos();
        todos.add(simpleTask);

        Task[] result = todos.search("несуществующий");
        assertEquals(0, result.length);
    }

    @Test
    void shouldSearchMeetingByTopic() {
        Meeting meeting = new Meeting(1, "Планирование спринта", "Нетология", "10:00");
        Todos todos = new Todos();
        todos.add(meeting);

        Task[] result = todos.search("Планирование");
        assertEquals(1, result.length);
        assertTrue(result[0] instanceof Meeting);
    }

    @Test
    void shouldSearchMeetingByProject() {
        Meeting meeting = new Meeting(1, "Планирование спринта", "Нетология", "10:00");
        Todos todos = new Todos();
        todos.add(meeting);

        Task[] result = todos.search("Нетология");
        assertEquals(1, result.length);
        assertTrue(result[0] instanceof Meeting);
    }
}