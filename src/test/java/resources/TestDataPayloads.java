package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataPayloads {

	
public static String readTextFileUsingFilesClass(String filePath) throws IOException {
		
		return new String (Files.readAllBytes(Paths.get(filePath)));
	}
	
}
