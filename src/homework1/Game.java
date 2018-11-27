package homework1;

import java.io.Serializable;

public class Game implements Serializable {
    private String name;
    private int price;
    private String developer;
    private Genre genre;

    public Game(String name, int price, String developer, Genre genre) {
        this.name = name;
        this.price = price;
        this.developer = developer;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", developer='" + developer + '\'' +
                ", genre=" + genre +
                '}';
    }
}
