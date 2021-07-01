package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean playingVid = false;
  private boolean pausedVid = false;
  private Video currentVid = null;
  
  private HashMap<String, VideoPlaylist> playlists = new HashMap<>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  private String getVideoDetails(Video v) {
	  String tagList = "";
	  for (String s: v.getTags()) {
		  tagList += " " + s;
	  }
	  if (tagList.length() != 0)
		  tagList = tagList.substring(1);
	  String details = v.getTitle() + " (" + v.getVideoId() + ") [" + tagList + "]";
	  
	  return details;
  }
  public void showAllVideos() {
//    System.out.println("showAllVideos needs implementation");
	  System.out.println("Here's a list of all available videos:");
	  List<String> videoEntries = new ArrayList<String>();
	  for (Video v: videoLibrary.getVideos()) {
		  String tagList = "";
		  for (String s: v.getTags()) {
			  tagList += " " + s;
		  }
		  if (tagList.length() != 0)
			  tagList = tagList.substring(1);
		  String entry = getVideoDetails(v);
		  videoEntries.add(entry);
	  }
	  Collections.sort(videoEntries);
	  for (String s: videoEntries) {
		  System.out.println(s);
	  }
  }

  public void playVideo(String videoId) {
//    System.out.println("playVideo needs implementation");
	  if (videoLibrary.getVideo(videoId) == null) {
		  System.out.println("Cannot play video: Video does not exist");
	  } else {
		  if (playingVid) {
			  System.out.println("Stopping video: " + currentVid.getTitle());  
		  }
		  currentVid = videoLibrary.getVideo(videoId);
		  System.out.println("Playing video: " + currentVid.getTitle());
		  playingVid = true;
		  pausedVid = false;
	  }
  }

  public void stopVideo() {
//    System.out.println("stopVideo needs implementation");
	  if (playingVid) {
		  System.out.println("Stopping video: " + currentVid.getTitle());
	  } else {
		  System.out.println("Cannot stop video: No video is currently playing");
	  }
	  playingVid = false;
	  currentVid = null;
  }

  public void playRandomVideo() {
//    System.out.println("playRandomVideo needs implementation");
	  int numVids = videoLibrary.getVideos().size();
	  if (numVids == 0) {
		  System.out.println("No videos are available");
	  } else {
		  Random rand = new Random();
		  int randInt = rand.nextInt(numVids);
		  playVideo(videoLibrary.getVideos().get(randInt).getVideoId());
	  }
  }

  public void pauseVideo() {
//    System.out.println("pauseVideo needs implementation");
	  if (pausedVid) {
		  System.out.println("Video already paused: " + currentVid.getTitle());
	  } else if (playingVid) {
		  System.out.println("Pausing video: " + currentVid.getTitle());
		  pausedVid = true;
	  } else {
		  System.out.println("Cannot pause video: No video is currently playing");
	  }
  }

  public void continueVideo() {
//    System.out.println("continueVideo needs implementation");
	  if (pausedVid) {
		  System.out.println("Continuing video: " + currentVid.getTitle());
	  } else if (playingVid) {
		  System.out.println("Cannot continue video: Video is not paused");  
	  } else {
		  System.out.println("Cannot continue video: No video is currently playing");
	  }
	  pausedVid = false;
  }

  public void showPlaying() {
//    System.out.println("showPlaying needs implementation");
	  if (pausedVid) {
		  System.out.println("Currently playing: " + getVideoDetails(currentVid) + " - PAUSED");
	  } else if (playingVid) {
		  System.out.println("Currently playing: " + getVideoDetails(currentVid));  
	  } else {
		  System.out.println("No video is currently playing");
	  }
  }

  public void createPlaylist(String playlistName) {
//    System.out.println("createPlaylist needs implementation");
	  if (playlists.containsKey(playlistName.toLowerCase())) {
		  System.out.println("Cannot create playlist: A playlist with the same name already exists");
	  } else {
		  // create the playlist with the original case
		  VideoPlaylist playlist = new VideoPlaylist(playlistName);
		  //add the playlist and its key (lowercase name) to the hashmap
		  playlists.put(playlistName.toLowerCase(), playlist);
		  System.out.println("Successfully created new playlist: " + playlistName);
	  }
	  
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
//    System.out.println("addVideoToPlaylist needs implementation");
	  VideoPlaylist playlist = playlists.get(playlistName.toLowerCase());
	  Video video = videoLibrary.getVideo(videoId);
	  if (playlist == null) {
		  System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
	  } else if (video == null) {
		  System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
	  } else {
		  playlist.addVideo(video, playlistName);
	  }
  }

  public void showAllPlaylists() {
//    System.out.println("showAllPlaylists needs implementation");
	  if (playlists.isEmpty()) {
		  System.out.println("No playlists exist yet");
	  } else {
		  // create list of all playlist names
		  ArrayList<String> sortedPlaylists = new ArrayList<>(playlists.keySet());
		  // sort the list of names
		  Collections.sort(sortedPlaylists);
		  System.out.println("Showing all playlists:");
		  // iterate through list and print playlist names
		  for (String playlistName: sortedPlaylists) {
			  System.out.println(playlists.get(playlistName).getName());
		  }
	  }
  }

  public void showPlaylist(String playlistName) {
//    System.out.println("showPlaylist needs implementation");
	  VideoPlaylist playlist = playlists.get(playlistName.toLowerCase());
	  if (playlist == null) {
		  System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
	  } else {
		  System.out.println("Showing playlist: " + playlistName);
		  List<Video> videos = playlist.getVideos();
		  if (videos.isEmpty()) {
			  System.out.println("No videos here yet");
		  } else {
			  for (Video v : videos) {
				  System.out.println(getVideoDetails(v));
			  }
		  }
	  }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}