/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyimitation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author oldman96
 */
public class Player extends Song{
    private ArrayList<Song> playlist;

    public Player() {
        playlist = new ArrayList<>();
        importDefaultPlaylist();
    }
    
    public void importDefaultPlaylist(){
        IOTasks task= new IOTasks();
        task.loadDataFromInputFile(playlist);
        System.out.println("\n----The default playlist is imported!----\n");
    }
    
    public void importCustomPlaylist(){
        IOTasks task = new IOTasks();
        System.out.print("Give the name of the playlist: ");
        String playlistToImport = task.inputFromKeyboard();
        playlist.clear();
        task.loadDataFromInputFile(playlist, playlistToImport);
        System.out.println("\n----The " + playlistToImport + " playlist is imported!----\n");
    }
//    public void loadDataFromInputFile(String filename){
//        BufferedReader reader; 
//        try{
//            reader = new BufferedReader(new FileReader(filename + ".txt"));
//            String line = reader.readLine();
//            while(line != null){
//                String [] lineArray = line.split(";");
//                Song song = new Song();
//
//                song.setSongName(lineArray[0]);
//                song.setArtist(lineArray[1]);
//                song.setGenre(lineArray[2]);
//                song.setDurationInSec(lineArray[3]);
//                song.setAlbumName(lineArray[4]);
//
//                playlist.add(song);
//                line = reader.readLine();
//            }
//            reader.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    
//    //overrideolt loadDataFromInputFile metodus
//    public void loadDataFromInputFile(){
//        this.loadDataFromInputFile(DEFAULT_INPUT_FILENAME);
//    }
    
    //formazott kiiratas, az az alapja a kiiratasoknak
    public void songLister(){
        System.out.println("\t\t\t\tSong name\t\t\t\tArtist\t\t\tGenre\t\tDuration(sec)\t\tAlbum");
        System.out.println("--------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------"
                + "-------------------------------");
        
        for (int i = 0; i < this.playlist.size(); i++) {
            System.out.printf("%s %3d. %-30s\n", ("LEJATSZVA"), (i+1), (this.playlist.get(i).toString()));
        }
    }
    
    //formazott kiiratas, parameterrel
    public void songLister(ArrayList<?> playlistParam){
        System.out.println("\t\t\t\tSong name\t\t\t\tArtist\t\t\tGenre\t\tDuration(sec)\t\tAlbum");
        System.out.println("--------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------"
                + "-------------------------------");
        
        for (int i = 0; i < playlistParam.size(); i++) {
            System.out.printf("%s %3d. %-30s\n", ("LEJATSZVA"), (i+1), (playlistParam.get(i).toString()));
        }
    }
    
    //Sorrendben torteno kiiratas
    public void listSongsInOrder(){
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    List songs in order    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.songLister(playlist);
    }
    
    public void listSongsInShuffledOrder(){
        ArrayList<Song> playlistShuffled = playlist;
        Collections.shuffle(playlistShuffled);
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    List songs in shuffled order    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.songLister(playlistShuffled);
        
    }
    
    public void listSongsFromArtist(){
        IOTasks task = new IOTasks();
        System.out.print("\nGive an artist: ");
        String artist = task.inputFromKeyboard();
        ArrayList<Song> sortedList = new ArrayList<>();
        
        for (int i = 0; i < this.playlist.size(); i++) {
            if( this.playlist.get(i).getArtist().equals(artist)){
                sortedList.add(this.playlist.get(i));
            }
        }
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    List songs from " + artist +"    ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.songLister(sortedList);
    } 
    
    public void listArtists(){
        ArrayList<String> artists = new ArrayList<>();
        for (int i = 0; i < this.playlist.size(); i++) {
            if( !artists.contains(this.playlist.get(i).getArtist()) ){
                artists.add(this.playlist.get(i).getArtist());
            }
        }
        Collections.sort(artists);
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    List artists in ascending order    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < artists.size(); i++) {
            System.out.printf("%3d. %s\n", (i+1),  artists.get(i));
        }
        
    }
    
    public void listGenres(){
        ArrayList<String> genres = new ArrayList<>();
        for (int i = 0; i < this.playlist.size(); i++) {
            if( !genres.contains(this.playlist.get(i).getGenre()) ){
                genres.add(this.playlist.get(i).getGenre());
            }
        }
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    List genres    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < genres.size(); i++) {
            System.out.println(genres.get(i));
        }
    }
    
    public void createNewPlaylist(){
        IOTasks task = new IOTasks();
        String filename;
        String songToAdd;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    Create new playlist    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Set name to the new playlist: ");
        filename = task.inputFromKeyboard();
        
        ArrayList<Song> newPlaylist = new ArrayList<>();
        task.savePlaylistToFile(newPlaylist, filename);
        System.out.println("\n----The playlist is saved to " + filename + ".txt----\n");
        System.out.println("[INFO] To add song, write the name of the song. "
                           + "To exit, hit ENTER. [INFO]\n");
        do{
            System.out.print("Name of the song: ");
            songToAdd = task.inputFromKeyboard();
            for (int i = 0; i < this.playlist.size(); i++) {
                if(this.playlist.get(i).getSongName().equals(songToAdd)){
                    if( !newPlaylist.contains(this.playlist.get(i))){
                        newPlaylist.add(this.playlist.get(i));
                    }
                }
            }     
        }while(!songToAdd.equals(""));
        
        task.savePlaylistToFile(newPlaylist, filename);
        System.out.println("\n----The playlist is saved to file " + filename + ".txt----\n");
        playlist.clear();
        task.loadDataFromInputFile(playlist, filename);
    }
}
