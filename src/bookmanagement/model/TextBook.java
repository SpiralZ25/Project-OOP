package bookmanagement.model;

public class TextBook extends Book {

    public TextBook(String id, String title, String author, String category, boolean borrowed) {
        super(id, title, author, category, borrowed);
    }

    @Override
    public String getType() {
        return "TextBook";
    }
}