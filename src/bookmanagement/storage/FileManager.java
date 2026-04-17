package bookmanagement.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bookmanagement.model.Book;
import bookmanagement.model.Novel;
import bookmanagement.model.TextBook;

public class FileManager<T extends Book> {

    public void saveToFile(List<T> books, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (T b : books) {
            writer.write(b.toFileString());
            writer.newLine();
        }

        writer.close();
    }

    public List<Book> loadFromFile(String fileName) throws IOException {
        List<Book> books = new ArrayList<Book>();

        File file = new File(fileName);

        if (!file.exists()) {
            return books;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length == 6) {
                String type = data[0];
                String id = data[1];
                String title = data[2];
                String author = data[3];
                String category = data[4];
                boolean borrowed = Boolean.parseBoolean(data[5]);

                if (type.equalsIgnoreCase("Novel")) {
                    books.add(new Novel(id, title, author, category, borrowed));
                } else if (type.equalsIgnoreCase("TextBook")) {
                    books.add(new TextBook(id, title, author, category, borrowed));
                }
            }
        }

        reader.close();
        return books;
    }
}