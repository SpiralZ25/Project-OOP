package bookmanagement.model;

public class Novel extends Book {

    public Novel(String id, String title, String author, String category, boolean borrowed) {
        super(id, title, author, category, borrowed);
    }

    @Override
    public String getType() {
        return "Novel";
    }
}