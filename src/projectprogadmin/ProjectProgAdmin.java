/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectprogadmin;

/**
 *
 * @author alexandre
 */
public class ProjectProgAdmin {

    /**
     * 
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        switch(args[0]){
            case "add":
                switch(args[1]){
                    case "video":
                        AdminDatabase.adminAddMedias(args[2], "Video");
                        break;
                    case "audio":
                        AdminDatabase.adminAddMedias(args[2], "Audio");
                        break;
                    case "question":
                        AdminDatabase.adminAddMedias(args[2], "Question");
                        break;
                    default :
                        System.out.println("Wrong argument : " + args[1] + "\nUse audio or video instead.");
                }
            case "rm":
                switch(args[1]){
                    case "video":
                        AdminDatabase.adminRmMedias(args[2], "Video");
                        break;
                    case "audio":
                        AdminDatabase.adminRmMedias(args[2], "Audio");
                        break;
                    case "question":
                        AdminDatabase.adminRmMedias(args[2], "Question");
                    default :
                        System.out.println("Wrong argument : " + args[1] + "\nUse audio or video instead.");
                }
            case "ls":
                switch(args[1]){
                    case "video":
                        AdminDatabase.adminShowVideos();
                        break;
                    case "audio":
                        AdminDatabase.adminShowAudios();
                        break;
                    case "question":
                        AdminDatabase.adminShowQuestions();
                        break;
                    default :
                        System.out.println("Wrong argument : " + args[1] + "\nUse 'question', 'audio' or 'video' instead.");
                }
        }
    }
    
}
