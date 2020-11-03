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
public class SpotifyImitationApp {
    
    public static void main(String[] args) {
        Playlist playlist1 = new Playlist();
        Player player1 = new Player();
        
        playlist1.loadDataFromInputFile();
        player1.listSongsInOrder(playlist1);
    }
    
    
}
