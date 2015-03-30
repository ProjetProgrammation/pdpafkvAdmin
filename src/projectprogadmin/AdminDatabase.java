/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BDD.DataBase;
import BDD.Media;
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
     * Launches the main administration interface.
     */
    public static void administrate() {
        Scanner sc = new Scanner(System.in);
        int choice = 4;
        while (choice > 0 && choice < 5) {
            System.out.println("What do you want to do? (Enter 1, 2, 3 or 4) :\n"
                    + "\t1-Add medias\n"
                    + "\t2-Remove medias\n"
                    + "\t3-See what is in the database\n"
                    + "\t4-Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    adminAddMedias();
                    break;
                case 2:
                    adminRmMedias();
                    break;
                case 3:
                    adminShowMedias();
                    break;
                case 4:
                    System.out.println("Leaving administrator interface...");
                    break;
                default:
                    System.out.println("Please, enter 1, 2, 3 or 4.");
            }
        }
    }
    
    /**
     * Extracts an ArrayList of Media from the file containing all metadatas about those medias.
     * 
     * @param path File's path containing metadatas.
     * @return ArrayList\<Media\>
     */
    private ArrayList<Media> extractList(String path){
        
    }
    
    /**
     * Adds medias from a text file to the database.
     */
    private static void adminAddMedias(){
        Scanner sc = new Scanner(System.in);
        int choice = 4;
        while (choice > 0 && choice < 5) {
            System.out.println("What type of media do you want to add? (Enter 1, 2, 3 or 4) :\n"
                    + "\t1-Videos\n"
                    + "\t2-Audios\n"
                    + "\t3-Question\n"
                    + "\t4-Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the file containing the videos to add :");
                    break;
                case 2:
                    adminRmMedias();
                    break;
                case 3:
                    adminShowMedias();
                    break;
                case 4:
                    System.out.println("Leaving administrator interface...");
                    break;
                default:
                    System.out.println("Please, enter 1, 2, 3 or 4.");
            }
        }
    }
    
    /**
     * Removes medias from the database.
     */
    private static void adminRmMedias(){
        //TODO
    }
    
    /**
     * Shows the entire database.
     */
    private static void adminShowMedias(){
        //TODO
    }

}
