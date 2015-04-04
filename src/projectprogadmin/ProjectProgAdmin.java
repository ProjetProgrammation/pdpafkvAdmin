/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectprogadmin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */
public class ProjectProgAdmin {

    /**
     *
     *
     * @param args The command line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AdminDatabase.createDb();
        switch (args[0]) {
            case "add":
                switch (args[1]) {
                    case "video": {
                        try {
                            AdminDatabase.adminAddMedias(args[2], "Video");
                        } catch (IOException ex) {
                            Logger.getLogger(ProjectProgAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    case "audio": {
                        try {
                            AdminDatabase.adminAddMedias(args[2], "Audio");
                        } catch (IOException ex) {
                            Logger.getLogger(ProjectProgAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    case "question": {
                        try {
                            AdminDatabase.adminAddMedias(args[2], "Question");
                        } catch (IOException ex) {
                            Logger.getLogger(ProjectProgAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    default:
                        System.out.println("Wrong argument : " + args[1] + "\nUse audio or video instead.");
                }
                break;
            case "rm":
                switch (args[1]) {
                    case "video":
                        AdminDatabase.adminRmMedias(args[2], "Video");
                        break;
                    case "audio":
                        AdminDatabase.adminRmMedias(args[2], "Audio");
                        break;
                    case "question":
                        AdminDatabase.adminRmMedias(args[2], "Question");
                    default:
                        System.out.println("Wrong argument : " + args[1] + "\nUse audio or video instead.");
                }
                break;
            case "ls":
                switch (args[1]) {
                    case "video":
                        AdminDatabase.adminShowVideos();
                        break;
                    case "audio":
                        AdminDatabase.adminShowAudios();
                        break;
                    case "question":
                        AdminDatabase.adminShowQuestions();
                        break;
                    default:
                        System.out.println("Wrong argument : " + args[1] + "\nUse 'question', 'audio' or 'video' instead.");
                }
                break;
            default:
                System.out.println("Wrong argument : " + args[0] + "\nUse 'add', 'rm' or 'ls' instead.");
        }
    }

}
