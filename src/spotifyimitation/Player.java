/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyimitation;

/**
 *
 * @author oldman96
 */
public class Player {
    
    //formazott kiiratas, az az alapja a kiiratasoknak
    public void songLister(Playlist playlist){
        System.out.println("\t\t\t\tSong name\t\t\t\tArtist\t\t\tGenre\t\tDuration(sec)\t\tAlbum");
        System.out.println("--------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------"
                + "-------------------------------");
        
        for (int i = 0; i < playlist.getSize(); i++) {
            System.out.printf("%s %3d. %-30s\n", ("LEJATSZVA"), (i+1), (playlist.getSong(i).toString()));
        }
    }
    
    //Sorrendben torteno kiiratas
    public void listSongsInOrder(Playlist playlist){
        songLister(playlist);
    }
    
}
