package unit.history;

import history.HistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.command.FakeCommand;

import static org.junit.jupiter.api.Assertions.*;

// Fait avec l'assistance de l'IA.
public class HistoryManagerTest {

    private HistoryManager history;

    @BeforeEach
    void setup() {
        history = HistoryManager.getInstance();
        history.clear(); // réinitialise undo/redo avant chaque test
    }

    @Test
    void push_shouldStoreCommandInUndoStackOnly() {
        FakeCommand cmd = new FakeCommand();
        history.push(cmd);

        assertEquals(1, history.size());       // undo pile
        assertEquals(0, history.redoSize());   // redo pile doit être vide
    }

    @Test
    void undo_shouldMoveCommandFromUndoToRedoStack() {
        FakeCommand cmd = new FakeCommand();
        history.push(cmd);

        history.undo();

        assertTrue(cmd.undone);
        assertEquals(0, history.size());        // undo vide
        assertEquals(1, history.redoSize());    // redo contient 1
    }

    @Test
    void redo_shouldMoveCommandBackToUndoStackAndExecute() {
        FakeCommand cmd = new FakeCommand();
        history.push(cmd);

        history.undo();  // move to redo
        history.redo();  // re-execute

        assertTrue(cmd.executed);
        assertEquals(1, history.size());       // réinsérée dans undo
        assertEquals(0, history.redoSize());   // redo vide
    }

    @Test
    void push_afterUndo_shouldClearRedoStack() {
        FakeCommand first = new FakeCommand();
        FakeCommand second = new FakeCommand();

        history.push(first);
        history.undo();        // redo ← first
        assertEquals(1, history.redoSize());

        history.push(second);  // nouvelle branche -> redo doit être vidé
        assertEquals(0, history.redoSize());
    }

    @Test
    void history_shouldNotExceedMaxLimit() {
        int max = 30;

        for (int i = 0; i < max + 10; i++) {
            history.push(new FakeCommand());
        }

        assertEquals(max, history.size());       // pile undo limitée
        assertEquals(0, history.redoSize());     // redo reste vide
    }
}
