package com.book.dao;

import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_BOOK_SQL="INSERT INTO book_info VALUES(NULL ,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_BOOK_SQL="delete from book_info where book_id = ?  ";
    private final static String EDIT_BOOK_SQL="update book_info set name= ? ,author= ? , translator= ?,publish_id= ?,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,vip_price= ?,pubdate= ? ,class_id= ? ,pressmark= ? ,state= ?  where book_id= ? ;";
    private final static String QUERY_ALL_BOOKS_SQL="SELECT * FROM book_info ";
    private final static String QUERY_BOOK_SQL="SELECT * FROM book_info WHERE book_id like  ?  or name like ?   ";
    //查询匹配图书的个数
    private final static String MATCH_BOOK_SQL="SELECT count(*) FROM book_info WHERE book_id like ?  or name like ?  ";
    //根据书号查询图书
    private final static String GET_BOOK_SQL="SELECT * FROM book_info where book_id = ? ";

    public int matchBook(String searchWord){
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL,new Object[]{swcx,swcx},Integer.class);
    }

    public ArrayList<Book> queryBook(String sw){
        String swcx="%"+sw+"%";
        final ArrayList<Book> books=new ArrayList<Book>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println("vip_price:"+(resultSet.getBigDecimal("vip_price")));
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Book book =new Book();
                    book.setAuthor(resultSet.getString("author"));
                    book.setTranslator(resultSet.getString("translator"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setLanguage(resultSet.getString("language"));
                    book.setName(resultSet.getString("name"));
                    book.setPressmark(resultSet.getInt("pressmark"));
                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setVipPrice(resultSet.getBigDecimal("vip_price"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublishId(resultSet.getInt("publish_id"));
                    books.add(book);
                }

            }
        });
        return books;
    }

    public ArrayList<Book> getAllBooks(){
        final ArrayList<Book> books=new ArrayList<Book>();

        jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                    while (resultSet.next()){
                        Book book =new Book();
                        book.setPrice(resultSet.getBigDecimal("price"));
                        book.setVipPrice(resultSet.getBigDecimal("vip_price"));
                        book.setState(resultSet.getInt("state"));
                        book.setPublishId(resultSet.getInt("publish_id"));
                        book.setPubdate(resultSet.getDate("pubdate"));
                        book.setName(resultSet.getString("name"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setClassId(resultSet.getInt("class_id"));
                        book.setBookId(resultSet.getLong("book_id"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setTranslator(resultSet.getString("translator"));
                        book.setIntroduction(resultSet.getString("introduction"));
                        book.setPressmark(resultSet.getInt("pressmark"));
                        book.setLanguage(resultSet.getString("language"));
                        books.add(book);
                    }
            }
        });
        return books;

    }

    public int deleteBook(long bookId){

        return jdbcTemplate.update(DELETE_BOOK_SQL,bookId);
    }

    public int addBook(Book book){
        String name=book.getName();
        String author=book.getAuthor();
        String translator=book.getTranslator();
        int publishId=book.getPublishId();
        String isbn=book.getIsbn();
        String introduction=book.getIntroduction();
        String language=book.getLanguage();
        BigDecimal price=book.getPrice();
        BigDecimal vipPrice=book.getVipPrice();
        Date pubdate=book.getPubdate();
        int classId=book.getClassId();
        int pressmark=book.getPressmark();
        int state=book.getState();

        return jdbcTemplate.update(ADD_BOOK_SQL,new Object[]{name,author,translator,publishId,isbn,introduction,language,price,vipPrice,pubdate,classId,pressmark,state});
    }

    public Book getBook(Long bookId){
        final Book book =new Book();
        jdbcTemplate.query(GET_BOOK_SQL, new Object[]{bookId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                book.setBookId(resultSet.getLong("book_id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setTranslator(resultSet.getString("translator"));
                book.setPublishId(resultSet.getInt("publish_id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setIntroduction(resultSet.getString("introduction"));
                book.setLanguage(resultSet.getString("language"));
                book.setPrice(resultSet.getBigDecimal("price"));
                book.setVipPrice(resultSet.getBigDecimal("vip_price"));
                book.setClassId(resultSet.getInt("class_id"));
                book.setPubdate(resultSet.getDate("pubdate"));
                book.setPressmark(resultSet.getInt("pressmark"));
                book.setState(resultSet.getInt("state"));
            }

        });
        return book;
    }
    public int editBook(Book book){
        Long bookId=book.getBookId();
        String name=book.getName();
        String author=book.getAuthor();
        String translator=book.getTranslator();
        int publishId=book.getPublishId();
        String isbn=book.getIsbn();
        String introduction=book.getIntroduction();
        String language=book.getLanguage();
        BigDecimal price=book.getPrice();
        BigDecimal vipPrice=book.getVipPrice();
        Date pubdate=book.getPubdate();
        int classId=book.getClassId();
        int pressmark=book.getPressmark();
        int state=book.getState();

//        System.out.println(bookId+" "+name+" "+author+" "+translator+" "+publish+" "+publishId+" "+isbn+" "+introduction+" "+language+" "+price+" "+vipPrice+" "+pubdate+" "+classId+" "+pressmark+" "+state);
        int count = jdbcTemplate.update(EDIT_BOOK_SQL,new Object[]{name,author,translator,publishId,isbn,introduction,language,price,vipPrice,pubdate,classId,pressmark,state,bookId});
//        System.out.println(count);
        return count;
    }

}
