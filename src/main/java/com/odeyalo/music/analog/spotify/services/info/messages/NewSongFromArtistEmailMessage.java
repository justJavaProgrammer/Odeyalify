package com.odeyalo.music.analog.spotify.services.info.messages;

public class NewSongFromArtistEmailMessage {
    private String artistName;
    private String artistLink;
    private String songName;
    private String songAvatarUrl;

    public NewSongFromArtistEmailMessage(String artistName, String artistLink, String songName, String songAvatarUrl) {
        this.artistName = artistName;
        this.artistLink = artistLink;
        this.songName = songName;
        this.songAvatarUrl = songAvatarUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistLink() {
        return artistLink;
    }

    public void setArtistLink(String artistLink) {
        this.artistLink = artistLink;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongAvatarUrl() {
        return songAvatarUrl;
    }

    public void setSongAvatarUrl(String songAvatarUrl) {
        this.songAvatarUrl = songAvatarUrl;
    }

    public static final class NewSongFromArtistEmailMessageBuilder {
        protected String messageId;
        private String artistName;
        private String artistLink;
        private String songName;
        private String songAvatarUrl;

        private NewSongFromArtistEmailMessageBuilder() {
        }

        public static NewSongFromArtistEmailMessageBuilder aNewSongFromArtistEmailMessage() {
            return new NewSongFromArtistEmailMessageBuilder();
        }

        public NewSongFromArtistEmailMessageBuilder setMessageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public NewSongFromArtistEmailMessageBuilder setArtistName(String artistName) {
            this.artistName = artistName;
            return this;
        }

        public NewSongFromArtistEmailMessageBuilder setArtistLink(String artistLink) {
            this.artistLink = artistLink;
            return this;
        }

        public NewSongFromArtistEmailMessageBuilder setSongName(String songName) {
            this.songName = songName;
            return this;
        }

        public NewSongFromArtistEmailMessageBuilder setSongAvatarUrl(String songAvatarUrl) {
            this.songAvatarUrl = songAvatarUrl;
            return this;
        }

        public NewSongFromArtistEmailMessage build() {
            NewSongFromArtistEmailMessage newSongFromArtistEmailMessage = new NewSongFromArtistEmailMessage(artistName, artistLink, songName, songAvatarUrl);
            return newSongFromArtistEmailMessage;
        }
    }
}
