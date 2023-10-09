package exception;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(int id) {
        super("Could not found the exam with id "+ id);
    }
}
