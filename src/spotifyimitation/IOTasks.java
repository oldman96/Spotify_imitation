/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyimitation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author oldman96
 */
public class IOTasks {
    private final String DEFAULT_INPUT_FILENAME = "defaultInput";
    
    public String inputFromKeyboard(){
        String input = null;
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in));
        try{
            input = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        //System.out.println(input);
        return input;
    }
    
    public void savePlaylistToFile(ArrayList<Song> playlistToSave, String filename){
        //System.out.println("----Adatok mentese fajlba----");
        try {
            FileWriter save = new FileWriter(filename + ".txt");
            for(int i = 0; i < playlistToSave.size(); i++){
                save.write(playlistToSave.get(i).toStringWithSeparator());
                save.write("\n");
            }
            save.close();
            //System.out.println("----Adatok sikeresen mentve----");


        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
    
        public void loadDataFromInputFile(ArrayList<Song> playlist, String filename){
        BufferedReader reader; 
        try{
            reader = new BufferedReader(new FileReader(filename + ".txt"));
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
    
    //overrideolt loadDataFromInputFile metodus
    public void loadDataFromInputFile(ArrayList<Song> playlist){
        loadDataFromInputFile(playlist, DEFAULT_INPUT_FILENAME);
    }
    
    public void taskSelector(Player player){
        int task;
        do{
            task = -1;

            System.out.print("\n~~~~~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~~~~~~~");
            System.out.print("\n0 - EXIT"
                           + "\n1 - Play songs in order"
                           + "\n2 - Play songs in shuffle mode"
                           + "\n3 - Play songs from selected artist"
                           + "\n4 - List artists"
                           + "\n5 - List genres"
                           + "\n6 - Create custom playlist"
                           + "\n7 - Import custom playlist"
                           + "\n8 - Import default playlist"
                           + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                           + "\nGive the number of selected task: ");

            task = Integer.parseInt(inputFromKeyboard());
            System.out.println("\n");
            switch (task) {
                case 1:
                    player.listSongsInOrder();
                    break;
                case 2:
                    player.listSongsInShuffledOrder();
                    break;
                case 3:
                    player.listSongsFromArtist();
                    break;
                case 4:
                    player.listArtists();
                    break;
                case 5:
                    player.listGenres();
                    break;
                case 6:
                    player.createNewPlaylist();
                    break;
                case 7:
                    player.importCustomPlaylist();
                    break;
                case 8:
                    player.importDefaultPlaylist();
                    break;
                case 0:
                    System.out.println("----EXIT----");
                    break;
                default:
                    System.out.println("[ERROR] Invalid task! [ERROR]");
                    break;
            }
        }while(task!=0);
    }
    
}
