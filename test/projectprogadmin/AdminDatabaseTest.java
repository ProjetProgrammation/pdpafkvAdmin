/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectprogadmin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guillaume
 */
public class AdminDatabaseTest {
    
    private AdminDatabase adminDatabase;
    
    public AdminDatabaseTest() {
    }
    
    @Before
    public void setUp() {
        adminDatabase = new AdminDatabase();
    }
    
    @After
    public void tearDown() {
        adminDatabase = null;
    }

    /**
     * Test of createDb method, of class AdminDatabase.
     */
   
    public void testCreateDb() {
        System.out.println("createDb");
        AdminDatabase.createDb();
    }

    /**
     * Test of adminAddMedias method, of class AdminDatabase.
     * @throws java.lang.Exception
     */
    
    public void testAdminAddMedias() throws Exception {
        System.out.println("adminAddMedias");
        String path;
        String mediaType;
        
        
        path = "vid";
        mediaType = "Video";
        AdminDatabase.adminAddMedias(path, mediaType);
        
        path = "aud";
        mediaType = "Audio";
        AdminDatabase.adminAddMedias(path, mediaType);
        
        path = "quest";
        mediaType = "Question";
        AdminDatabase.adminAddMedias(path, mediaType);
        
      /*  path = "other";
        mediaType = "Question";
        AdminDatabase.adminAddMedias(path, mediaType);*/
        
        path = "aud";
        mediaType = "other";
        AdminDatabase.adminAddMedias(path, mediaType);
    }

    /**
     * Test of adminRmMedias method, of class AdminDatabase.
     * @throws java.lang.Exception
     */
    
    public void testAdminRmMedias() throws Exception {
        System.out.println("adminRmMedias");
        String path;
        String mediaType ;
        
        
        path = "vid";
        mediaType = "Video";
        AdminDatabase.adminRmMedias(path, mediaType);
        
        path = "aud";
        mediaType = "Audio";
        AdminDatabase.adminRmMedias(path, mediaType);
        
        path = "quest";
        mediaType = "Question";
        AdminDatabase.adminRmMedias(path, mediaType);
        
       /* path = "other";
        mediaType = "Question";
        AdminDatabase.adminAddMedias(path, mediaType);*/
        
        path = "aud";
        mediaType = "other";
        AdminDatabase.adminRmMedias(path, mediaType);
    }

    /**
     * Test of adminShowVideos method, of class AdminDatabase.
     */
    
    public void testAdminShowVideos() {
        System.out.println("adminShowVideos");
        AdminDatabase.adminShowVideos();
       
    }

    /**
     * Test of adminShowAudios method, of class AdminDatabase.
     */
    
    public void testAdminShowAudios() {
        System.out.println("adminShowAudios");
        AdminDatabase.adminShowAudios();
       
    }

    /**
     * Test of adminShowQuestions method, of class AdminDatabase.
     */
    
    public void testAdminShowQuestions() {
        System.out.println("adminShowQuestions");
        AdminDatabase.adminShowQuestions();
        
    }

    /**
     * Test of getIdConvertedLanguageName method, of class AdminDatabase.
     */
    public void testBlackBoxGetIdConvertedLanguageName() {
        System.out.println("getIdConvertedLanguageName");
        int result = 0;
        int tabLanguage[] = new int[5];
        String language;
        
        language = "fr";
        result= AdminDatabase.getIdConvertedLanguageName(language);
        assertNotNull("French", result);
        tabLanguage[0] = result;
        
        language = "en";
        result = AdminDatabase.getIdConvertedLanguageName(language);
        assertNotNull("English", result);
        tabLanguage[1] = result;
        
        language = "pt";
        result = AdminDatabase.getIdConvertedLanguageName(language);
        assertNotNull("Portuguese", result);
        tabLanguage[2] = result;
        
        language = "jp";
        result = AdminDatabase.getIdConvertedLanguageName(language);
        assertNotNull("Japonese", result);
        tabLanguage[3] = result;
        
        language = "us";
        result = AdminDatabase.getIdConvertedLanguageName(language);
        assertNotNull("American", result);
        tabLanguage[4] = result;
        int i,j;
        for ( i = 0; i < tabLanguage.length - 1;i++){
            for (j = i + 1 ; j <= tabLanguage.length - 1; j++){
                assertNotSame(i, j);
            }
        }
        
        language = "American";
        result = AdminDatabase.getIdConvertedLanguageName(language);
        assertEquals(0, result);
        
    }
  
    @Test
    public void test() throws Exception{
        testCreateDb();
        testAdminAddMedias();
        testAdminRmMedias();
        testAdminShowAudios();
        testAdminShowQuestions();
        testAdminShowVideos();
        testBlackBoxGetIdConvertedLanguageName();
    }
    
}
