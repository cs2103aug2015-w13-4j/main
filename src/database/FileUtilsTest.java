package database;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testFileNameValidFileNames() throws IOException {
		assertTrue(FileUtils.isFilenameValid("valid.txt"));
		assertTrue(FileUtils.isFilenameValid("valid valid.txt"));
		
		assertFalse(FileUtils.isFilenameValid("inva|id.t*t"));
		File f = new File("inva|id.t*t");
		PrintWriter w = new PrintWriter(new FileWriter(f));
		w.write(52);
	}
	
	@Test
	public void testFileNameValidPathNames() {
		assertTrue(FileUtils.isFilenameValid("/Users/zhongwei-z/Desktop"));
		assertTrue(FileUtils.isFilenameValid("/"));
		assertTrue(FileUtils.isFilenameValid("/usr/"));
	}

}
