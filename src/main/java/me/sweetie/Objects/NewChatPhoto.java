package me.sweetie.Objects;

import com.google.gson.annotations.SerializedName;

public class NewChatPhoto {
    @SerializedName("file_unique_id")
    private String fileUniqueId;
    @SerializedName("file_id")
    private String fileId;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("file_size")
    private int fileSize;

    public String getFileUniqueId() {
        return fileUniqueId;
    }

    public String getFileId() {
        return fileId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFileSize() {
        return fileSize;
    }
}
