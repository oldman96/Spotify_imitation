/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyimitation;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author oldman96
 */
public class Playlist extends Song{
    private ArrayList<Song> playlist;

    public Playlist() {
        playlist = new ArrayList<Song>();
    }
    
    public int getSize(){
        return playlist.size();
    }
    
    public Song getSong(int number){
        return playlist.get(number);
    }
    
    public void loadDataFromInputFile(){
        BufferedReader reader; 
        try{
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            while(line != null){
                String [] lineArray = line.split(";");
                Song song = new Song();
                
                song.setSongName(lineArray[0]);
                song.setArtist(lineArray[1]);
                song.setGenre(lineArray[2]);
                song.setDurationInSec(lineArray[3]);
                song.setAlbumName(lineArray[4]);
                
                playlist.add(song);
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void listSongs(){
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(i+1 + ". " + playlist.get(i).toString());
        }
    }

}
