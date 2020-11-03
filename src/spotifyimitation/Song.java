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
public class Song {
    private String songName;
    private String artist;
    private String genre;
    private String durationInSec;
    private String albumName;

    public Song() {
    }
    


    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(String durationInSec) {
        this.durationInSec = durationInSec;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return this.songName + ", " +
               this.artist + ", " +
               this.genre + ", " +
               this.durationInSec + ", " +
               this.albumName;
    }
    
    
}
