package com.google;

import java.util.ArrayList;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {
	
	private String name;
	private List<Video> videos = new ArrayList<>();;
	
	VideoPlaylist(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addVideo(Video video, String inputName) {
		if (videos.contains(video)) {
			System.out.println("Cannot add video to " + inputName + ": Video already added");
		} else {
			videos.add(video);
			System.out.println("Added video to "  + inputName + ": " + video.getTitle());
		}
	}
	
	public List<Video> getVideos() {
		return videos;
	}

}
