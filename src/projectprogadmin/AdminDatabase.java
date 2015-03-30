package projectprogadmin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BDD.Audio;
import BDD.DataBase;
import BDD.Media;
import BDD.Question;
import BDD.Video;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * Use this class to manage the database.
 * <b>Note that everything happening here is not shown in the user interface but
 * in the command line.</b>
 * </p>
 *
 * @author akervadec
 */
public final class AdminDatabase {

    private static DataBase db;

    /**
     * Extracts an ArrayList of Video from the file containing all metadatas
     * about those medias.
     *
     * @param path File's path containing metadatas.
     * @return ArrayList\<Video\>
     */
    private static ArrayList<Video> extractListVideo(String path) {
        ArrayList<Video> result;
        result = new ArrayList<>();

        //TODO
        //Reading of the file
        return result;
    }

    /**
     * Extracts an ArrayList of Audio from the file containing all metadatas
     * about those medias.
     *
     * @param path File's path containing metadatas.
     * @return ArrayList\<Audio\>
     */
    private static ArrayList<Audio> extractListAudio(String path) {
        ArrayList<Audio> result;
        result = new ArrayList<>();

        //TODO
        //Reading of the file
        return result;
    }

    /**
     * Extracts an ArrayList of Question from the file containing all metadatas
     * about those medias.
     *
     * @param path File's path containing metadatas.
     * @return ArrayList\<Question\>
     */
    private static ArrayList<Question> extractListQuestion(String path) {
        ArrayList<Question> result;
        result = new ArrayList<>();

        //TODO
        //Reading of the file
        return result;
    }

    /**
     * Adds medias from a text file to the database.
     *
     * @param path
     * @param mediaType
     */
    protected static void adminAddMedias(String path, String mediaType) {
        switch (mediaType) {
            case "Video":
                ArrayList<Video> tmpVideoList;
                tmpVideoList = new ArrayList<>(extractListVideo(path));
                for (int i = 0; i < tmpVideoList.size(); i++) {
                    db.addVideo(tmpVideoList.get(i).getName(),
                            tmpVideoList.get(i).getFilePath(),
                            tmpVideoList.get(i).getFormat(),
                            db.getLanguageById(tmpVideoList.get(i).getIdLanguage()));
                }
                break;
            case "Audio":
                ArrayList<Audio> tmpAudioList;
                tmpAudioList = new ArrayList<>(extractListAudio(path));
                for (int i = 0; i < tmpAudioList.size(); i++) {
                    db.addAudio(tmpAudioList.get(i).getName(),
                            tmpAudioList.get(i).getFilePath(),
                            tmpAudioList.get(i).getFormat(),
                            db.getLanguageById(tmpAudioList.get(i).getIdLanguage()));
                }
                break;
            case "Question":
                ArrayList<Question> tmpQuestionList;
                tmpQuestionList = new ArrayList<>(extractListQuestion(path));
                for (int i = 0; i < tmpQuestionList.size(); i++) {
                    db.addQuestion(tmpQuestionList.get(i).getContent(),
                            db.searchVideoById(tmpQuestionList.get(i).getIdVideo()),
                            db.searchAudioById(tmpQuestionList.get(i).getIdAudio()),
                            db.getLanguageById(tmpQuestionList.get(i).getIdLanguage()));
                }
                break;
            default:
                System.out.println("Wrong media type : " + mediaType);
        }
    }

    /**
     * Removes medias from the database.
     *
     * @param path
     * @param mediaType
     */
    protected static void adminRmMedias(String path, String mediaType) {
        //TODO
    }

    /**
     * Shows the entire Video database.
     */
    protected static void adminShowVideos() {
        System.out.println(db.getAllVideos());
    }

    /**
     * Shows the entire Audio database.
     */
    protected static void adminShowAudios() {
        System.out.println(db.getAllAudios());
    }

    /**
     * Shows the entire Question database.
     */
    protected static void adminShowQuestions() {
        System.out.println(db.getAllQuestions());
    }

}
