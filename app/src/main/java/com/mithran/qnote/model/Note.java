package com.mithran.qnote.model;

/**
 * Note is a model class which has noteId and noteData
 * @author Rohith S
 *
 */
public class Note {
    /**
     * noteId is the primary key for uniquely identifying a Note
     */
    private String noteId;
    /**
     * noteData has the original contents of a Note
     */
    private String noteData;

    public Note(String noteId, String noteData) {
        this.noteId = noteId;
        this.noteData = noteData;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }
}
