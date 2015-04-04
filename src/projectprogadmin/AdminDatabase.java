package projectprogadmin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BDD.Audio;
import BDD.DataBase;
import BDD.Question;
import BDD.Video;
import Thumbnail.CreationThumbnails;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Use this class to manage the database.
 * <b>Note that everything happening here is not shown in the user interface but
 * in the command line.</b>
 * Notice that media files's name need to be formatted like :
 * </p>
 * <ul>
 * <li>Video :
 * [0-9]*_[0-9]*_[0-9]*_[a-zA-Z0-9]*_[a-z]{2}_[a-zA-Z0-9]*_[a-zA-Z0-9]{9}\\.[a-zA-Z0-9]*
 * for example 2013_3_20_S33_fr_L1_SINC_B_ok.mp4</li>
 * <li>Audio :
 * [0-9]*_[0-9]*_[0-9]*_[a-zA-Z0-9]*_[a-z]{2}_[a-zA-Z0-9]*_[a-zA-Z0-9]{4}\\.[a-zA-Z0-9]*
 * for example 2013_3_20_S33_fr_L1_SINC.wav</li>
 * </ul>
 *
 * @author akervadec
 */
public final class AdminDatabase {

    private static DataBase db;

    public static void createDb(){
        db = new DataBase();
    }
    
    /**
     * Extracts an ArrayList of Video from the file containing all metadatas
     * about those medias.
     *
     * @param path File's path containing metadatas.
     * @return ArrayList\<Video\>
     */
    private static ArrayList<Video> extractListVideo(String path) throws IOException {
        ArrayList<Video> result;
        result = new ArrayList<>();
        BufferedReader reader = null;
        String line;
        //Initializing the patterns to extract datas
        Pattern pLanguage = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]?[^_]?");
        Pattern pName = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[a-zA-Z_0-9]*\\.");
        Pattern pFormat = Pattern.compile("\\.[^_]*$");
        Matcher m;
        String language = "", name = "", format = "", filePath;

        try {
            reader = new BufferedReader(new FileReader(path));
            //Reading the file by line
            System.out.println("[extractListVideo]Begining of the file reading");
            while ((line = reader.readLine()) != null) {
                filePath = "/Resources/Video/" + line;
                m = pLanguage.matcher(line);
                while (m.find()) {
                    language = m.group().substring(m.group().length() - 2);
                }
                m = pName.matcher(line);
                while (m.find()) {
                    name = m.group().substring(0, m.group().length() - 1);
                }
                m = pFormat.matcher(line);
                while (m.find()) {
                    format = m.group().substring(1);
                }
                result.add(new Video(0, name, filePath, format, getIdConvertedLanguageName(language)));
            }
            reader.close();
            System.out.println("[extractListVideo]File read");
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
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
        BufferedReader reader = null;
        String line;
        //Initializing the patterns to extract datas
        Pattern pLanguage = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]?[^_]?");
        Pattern pName = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[a-zA-Z_0-9]*\\.");
        Pattern pFormat = Pattern.compile("\\.[^_]*$");
        Matcher m;
        String language = "", name = "", format = "", filePath;

        try {
            reader = new BufferedReader(new FileReader(path));
            //Reading the file by line
            System.out.println("[extractListAudio]Begining of the file reading");
            while ((line = reader.readLine()) != null) {
                filePath = "/Resources/Audio/" + line;
                m = pLanguage.matcher(line);
                while (m.find()) {
                    language = m.group().substring(m.group().length() - 2);
                }
                m = pName.matcher(line);
                while (m.find()) {
                    name = m.group().substring(0, m.group().length() - 1);
                }
                m = pFormat.matcher(line);
                while (m.find()) {
                    format = m.group().substring(1);
                }
                result.add(new Audio(0, name, filePath, format, getIdConvertedLanguageName(language)));
            }
            reader.close();
            System.out.println("[extractListAudio]File read");
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }

    /**
     * Extracts an ArrayList of Question from the file containing all metadatas
     * about those medias. In the file, data must be formatted like :
     * <ul>
     *  <li>\<Content of the question\></il>
     *  <li>\<Video file name (with its extension)\></li>
     *  <li>\<Audio file name (with its extension)\></li>
     *  <li>\<Content of the question\></il>
     *  <li>\<Video file name (with its extension)\></li>
     *  <li>\<Audio file name (with its extension)\></li>
     *  <li>\<Content of the question\></il>
     *  <li>\<Video file name (with its extension)\></li>
     *  <li>\<Audio file name (with its extension)\></li>
     *  <li>...</li>
     * </ul>
     *
     * @param path File's path containing metadatas.
     * @return ArrayList\<Question\>
     */
    private static ArrayList<Question> extractListQuestion(String path) {
        ArrayList<Question> result;
        result = new ArrayList<>();
        BufferedReader reader = null;
        String line;
        //Initializing the patterns to extract datas
        Pattern pLanguage = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]?[^_]?");
        Pattern pName = Pattern.compile("^[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[^_]*_[a-zA-Z_0-9]{4}\\.");
        Pattern pFormat = Pattern.compile("\\.[^_]*$");
        Matcher m;
        String language = "",
                nameVideo = "",
                formatVideo = "",
                nameAudio = "",
                formatAudio = "",
                content = "";
        int idVideo = 0, idAudio = 0;

        try {
            reader = new BufferedReader(new FileReader(path));
            //Reading the file by line
            System.out.println("[extractListQuestion]Begining of the file reading");
            while ((line = reader.readLine()) != null) {
                
                //Extracting data from the first line : the question content line
                content = line;
                
                //Extracting datas from the second line : the video line
                line = reader.readLine();
                m = pName.matcher(line);
                while (m.find()) {
                    nameVideo = m.group().substring(0, m.group().length() - 1);
                }
                m = pFormat.matcher(line);
                while (m.find()) {
                    formatVideo = m.group().substring(1);
                }
                idVideo = db.searchVideoByNameFormat(nameVideo, formatVideo).getId();
                
                //Extracting datas from the third line : the audio line
                line = reader.readLine();
                m = pName.matcher(line);
                while (m.find()) {
                    nameAudio = m.group().substring(0, m.group().length() - 1);
                }
                m = pFormat.matcher(line);
                while (m.find()) {
                    formatAudio = m.group().substring(1);
                }
                idAudio = db.searchAudioByNameFormat(nameAudio, formatAudio).getId();
                //Language extracting from the audio
                m = pLanguage.matcher(line);
                while (m.find()) {
                    language = m.group().substring(m.group().length() - 2);
                }
                result.add(new Question(0, content, idVideo, idAudio, getIdConvertedLanguageName(language)));
            }
            reader.close();
            System.out.println("[extractListQuestion]File read");
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }

    /**
     * Adds medias from a text file to the database.
     *
     * @param path
     * @param mediaType
     */
    protected static void adminAddMedias(String path, String mediaType) throws IOException {
        switch (mediaType) {
            case "Video":
                ArrayList<Video> tmpVideoList;
                tmpVideoList = new ArrayList<>(extractListVideo(path));
                for (int i = 0; i < tmpVideoList.size(); i++) {
                    CreationThumbnails cThumb = new CreationThumbnails(tmpVideoList.get(i).getFilePath(), tmpVideoList.get(i).getName());
                    db.addVideo(tmpVideoList.get(i).getName(),
                            tmpVideoList.get(i).getFilePath(),
                            tmpVideoList.get(i).getFormat(),
                            db.getLanguageById(tmpVideoList.get(i).getIdLanguage()),
                            cThumb.getPathFilePicture(),
                            cThumb.getPathFileGif());
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
    protected static void adminRmMedias(String path, String mediaType) throws IOException {
        switch (mediaType) {
            case "Video":
                ArrayList<Video> tmpVideoList;
                tmpVideoList = new ArrayList<>(extractListVideo(path));
                for (int i = 0; i < tmpVideoList.size(); i++) {
                    db.rmVideo(tmpVideoList.get(i).getName(),
                            tmpVideoList.get(i).getFormat());
                }
                break;
            case "Audio":
                ArrayList<Audio> tmpAudioList;
                tmpAudioList = new ArrayList<>(extractListAudio(path));
                for (int i = 0; i < tmpAudioList.size(); i++) {
                    db.rmAudio(tmpAudioList.get(i).getName(),
                            tmpAudioList.get(i).getFormat());
                }
                break;
            case "Question":
                ArrayList<Question> tmpQuestionList;
                tmpQuestionList = new ArrayList<>(extractListQuestion(path));
                for (int i = 0; i < tmpQuestionList.size(); i++) {
                    db.rmQuestion(tmpQuestionList.get(i).getContent());
                }
                break;
            default:
                System.out.println("Wrong media type : " + mediaType);
        }
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

    /**
     * <p>Returns the id of the language searched. The nomenclature is the following :</p>
     * <ul>
     *  <li>fr = French</li>
     *  <li>en = English</li>
     *  <li>pt = Portuguese</li>
     *  <li>jp = Japonese</li>
     *  <li>us = American</li>
     * </ul>
     *
     * @param language Language's name searched.
     * @return int
     */
    protected static int getIdConvertedLanguageName(String language) {
        switch (language) {
            case "fr":
                return (db.getLanguageByName("French"));
            case "en":
                return (db.getLanguageByName("English"));
            case "pt":
                return (db.getLanguageByName("Portuguese"));
            case "jp":
                return (db.getLanguageByName("Japonese"));
            case "us":
                return (db.getLanguageByName("American"));
            default:
                System.out.println("[getIdConvertedLanguageName]Cannot convert :" + language);
        }
        return 0;
    }

}
