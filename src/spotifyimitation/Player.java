/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyimitation;

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
        playlist.clear();
        
        if( task.loadDataFromInputFile(playlist) == true){
            System.out.println("\n[SUCCESS] The default playlist is imported! [SUCCESS]\n");
        }
    }
    
    
    public void importCustomPlaylist(){
        IOTasks task = new IOTasks();
        System.out.print("Give the name of the playlist: ");
        String playlistToImport = task.inputFromKeyboard();
        playlist.clear();
        
        if( task.loadDataFromInputFile(playlist, playlistToImport) == true){;
            System.out.println("\n[SUCCESS] The " + playlistToImport + " playlist is imported! [SUCCESS]\n");
        }
        else System.out.println("\n[!!!] Try again! [!!!]\n");
    }
    
    
    //A kiiratasok alapja
    public void songLister(ArrayList<?> playlistParam){
        if( playlistParam.size() > 0){
            System.out.println("\t\t\t\tSong name\t\t\t\tArtist\t\t\tGenre\t\tDuration(sec)\t\tAlbum");
            System.out.println("--------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------"
                + "-------------------------------");
            for (int i = 0; i < playlistParam.size(); i++) {
                System.out.printf("%s %3d. %-30s\n", ("LEJATSZVA"), (i+1), (playlistParam.get(i).toString()));
            }
        }else System.out.println("\n[ERROR] Playlist is empty. [ERROR]");
    }
    
    
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
        if( sortedList.size() > 0){
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("    List songs from " + artist);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            this.songLister(sortedList);
        }else System.out.println("\n[ERROR] Artist not found in the playlist. [ERROR]");
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
        
        if( artists.size() > 0){
            for (int i = 0; i < artists.size(); i++) {
                System.out.printf("%3d. %s\n", (i+1),  artists.get(i));
            }
        }else System.out.println("\n[ERROR] The playlist is empty. [ERROR]");
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
        
        if( genres.size() > 0){
            for (int i = 0; i < genres.size(); i++) {
            System.out.println(genres.get(i));
            }
        }else System.out.println("\n[ERROR] The playlist is empty. [ERROR]");
    }
    
   
    public void createNewPlaylist(){
        IOTasks task = new IOTasks();
        String filename;
        String songToAdd;
        boolean isSongExists;
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~    Create new playlist    ~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("[INFO] To add song, write the name of the song. "
                           + "To exit, hit ENTER. [INFO]\n");
        ArrayList<Song> newPlaylist = new ArrayList<>();
        do{
            isSongExists = false;
            System.out.print("Name of the song: ");
            songToAdd = task.inputFromKeyboard();
            
            for (int i = 0; i < this.playlist.size(); i++) {
                if(this.playlist.get(i).getSongName().equals(songToAdd)){
                    if( !newPlaylist.contains(this.playlist.get(i))){
                        newPlaylist.add(this.playlist.get(i));
                        isSongExists = true;
                    }
                }
            } 
            if(!songToAdd.equals("")){
                if(isSongExists == true){
                    System.out.println("  [ADDED TO PLAYLIST!]");
                } else System.out.println("  [ERROR] Invalid song name! [ERROR]");
            }
            
   
        }while(!songToAdd.equals(""));
            
        System.out.print("Set name to the playlist: ");
        filename = task.inputFromKeyboard();
        
        if( task.savePlaylistToFile(newPlaylist, filename) == true){;
            System.out.println("\n-[SUCCESS] The playlist is saved to file " + filename + ".txt [SUCCESS]\n");
            playlist.clear();
            task.loadDataFromInputFile(playlist, filename);
        }
        else System.out.println("\n[!!!] Try again! [!!!]\n");
    }
}
