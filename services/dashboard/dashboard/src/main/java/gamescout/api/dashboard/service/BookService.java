package gamescout.api.dashboard.service;

import gamescout.api.dashboard.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> processBookData(String message) throws Exception;

}
