package homework1;

import com.google.gson.Gson;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class GameModel extends AbstractTableModel implements Serializable {

    private ArrayList<Game> games = new ArrayList<>();

    GameModel(){
        readFile();
    }


    public void addGame(String name, int price, String dev, Genre genre){
        games.add(new Game(name, price, dev, genre));
        fireTableDataChanged();
    }

    public void deleteGameByIndex(int row){
        if(row >=0 && row < games.size()) {
            games.remove(row);
            fireTableDataChanged();
        }
    }

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game cur = games.get(rowIndex);
        switch (columnIndex){
            case 0: return cur.getName();
            case 1: return cur.getGenre();
            case 2: return cur.getDeveloper();
            case 3: return cur.getPrice();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Game name";
            case 1:
                return "Genre";
            case 2:
                return "Developer";
            case 3:
                return "Price";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return Genre.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
        }
        return Object.class;
    }

    void saveToDefaultFile() {
        try (Writer writer = new FileWriter("data.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(games, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void readFile(){
        try(Reader reader = new FileReader("data.json")){
            Gson gson = new Gson();
            games = gson.fromJson(reader, new TypeToken<ArrayList<Game>>(){}.getType());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void change(String data, int row, int column) {
        if(row != -1) {
            switch (column) {
                case 0:
                    games.get(row).setName(data);
                    fireTableDataChanged();
                    break;
                case 1:
                    games.get(row).setGenre(Genre.valueOf(data));
                    fireTableDataChanged();
                    break;
                case 2:
                    games.get(row).setDeveloper(data);
                    fireTableDataChanged();
                    break;
                case 3:
                    games.get(row).setPrice(Integer.parseInt(data));
                    fireTableDataChanged();
                    break;
                default:
                    break;
            }
        }
    }
}
