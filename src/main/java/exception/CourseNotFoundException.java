package exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(int id) {
        super("Could not found the course with id "+ id);
    }
}
